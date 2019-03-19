package com.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.DAO.CreateOrderDAO;

import ch.qos.logback.classic.Logger;

@RestController
public class CreateOrder {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    
	@RequestMapping(value="/createOrder", method=RequestMethod.POST)
    public Map createOrder(@RequestBody CreateOrderDAO createOrderDAO) throws Exception {
		logger.info("entry into createOrder");
		
		//1.检验客户是否合法
    	String partyId = createOrderDAO.getPartyId();
        ServiceInstance serviceInstanceParty = loadBalancerClient.choose("party");
        String url = "http://" + serviceInstanceParty.getHost() + ":" 
        					+ serviceInstanceParty.getPort() + "/checkParty";
        logger.info("url:"+url);
        try {
//        	restTemplate.getForObject(url, String.class);
        	restTemplate.postForLocation(url, createOrderDAO);
        }catch(Exception e){
        	throw new Exception(e.getMessage());
        }
        
        //2.检验产品库存
        String productId = createOrderDAO.getProductId();
        ServiceInstance serviceInstanceProduct = loadBalancerClient.choose("product");
        String url1 = "http://" + serviceInstanceProduct.getHost() + ":" 
				+ serviceInstanceProduct.getPort() + "/checkProduct";
        logger.info("url1:"+url1);
        try {
//        	restTemplate.getForObject(url1, String.class);
        	restTemplate.postForLocation(url1, createOrderDAO);
        }catch(Exception e){
        	throw new Exception(e.getMessage());
        }
        
        Map result = new HashMap<>();
        result.put("massage", "订单创建成功");
        return result;

    }

}
