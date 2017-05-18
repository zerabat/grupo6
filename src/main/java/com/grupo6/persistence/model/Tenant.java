//package com.grupo6.persistence.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "tenant")
//public class Tenant {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//	
//	@Column(length = 50, nullable = false)
//	private String tenantName;
//	
//	@Column(length = 50, nullable = false)
//	private String tenatDatabase;
//	
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getTenantName() {
//		return tenantName;
//	}
//
//	public void setTenantName(String tenantName) {
//		this.tenantName = tenantName;
//	}
//
//	public String getTenatDatabase() {
//		return tenatDatabase;
//	}
//
//	public void setTenatDatabase(String tenatDatabase) {
//		this.tenatDatabase = tenatDatabase;
//	}
//	
//
//}
