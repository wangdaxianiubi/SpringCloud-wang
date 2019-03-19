package com.Mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.DAO.Party;

@CacheNamespace(implementation=com.common.redisUtil.MybatisRedisCache.class)
@Mapper
public interface PartyMapper {

	@Select("SELECT * FROM PARTY WHERE PARTY_ID = #{partyId}")
	Party findByPartyId(@Param("partyId") String partyId);

}
