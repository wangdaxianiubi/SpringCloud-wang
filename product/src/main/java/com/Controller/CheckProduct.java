package com.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.Product;
import com.Mapper.ProductMapper;

import ch.qos.logback.classic.Logger;

@RestController
public class CheckProduct {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	DiscoveryClient discoveryClient;
	
	@Autowired
	private ProductMapper productMapper;

	@ResponseBody
	@RequestMapping(value="/checkProduct",method=RequestMethod.POST)
	public void checkProduct(@RequestBody Product productIn) throws Exception {
		String productId = productIn.getProductId();
		logger.info("productId:"+productId);
		Product product = productMapper.findByProductId(productId);
		if(product.getStock()==0) {
			throw new Exception("产品："+productId+"库存不足");
		}
	}

}
