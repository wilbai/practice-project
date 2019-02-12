package com.wil.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Timestamp createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
