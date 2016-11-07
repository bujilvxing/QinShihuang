package com.bjlx.QinShihuang.model.account;

import org.mongodb.morphia.annotations.Transient;

public abstract class IdProof {
	
	@Transient
	public final static String fd_idType = "idType";

	String idType;

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	
}
