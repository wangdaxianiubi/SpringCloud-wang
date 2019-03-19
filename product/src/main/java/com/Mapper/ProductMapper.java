package com.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.DAO.Product;

@Mapper
public interface ProductMapper {

	@Select("SELECT * FROM Product WHERE PRODUCT_ID = #{productId}")
	Product findByProductId(@Param("productId") String productId);

//	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
//	int insert(@Param("name") String name, @Param("age") Integer age);

}
