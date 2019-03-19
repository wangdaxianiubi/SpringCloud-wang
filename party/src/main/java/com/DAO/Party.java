package com.DAO;

import java.io.Serializable;

public class Party implements Serializable{
	private static final long serialVersionUID = -1L;
	
	private String partyId;
    private String name;
    private Integer age;
    
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
