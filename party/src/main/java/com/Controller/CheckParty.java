package com.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.Party;
import com.Mapper.PartyMapper;

import ch.qos.logback.classic.Logger;

@RestController
public class CheckParty {
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	DiscoveryClient discoveryClient;
	
	@Autowired
	private PartyMapper partyMapper;
	

	@ResponseBody
	@RequestMapping(value="/checkParty",method=RequestMethod.POST)
	public void checkParty(@RequestBody Party party) throws Exception {
		logger.info("partyId:"+party.getPartyId());
		Party partyOut = partyMapper.findByPartyId(party.getPartyId());
		if(partyOut==null) {
			throw new Exception("客户不存在");
		}
	}

}
