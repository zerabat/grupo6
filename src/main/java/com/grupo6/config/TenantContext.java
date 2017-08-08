package com.grupo6.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TenantContext {

	private static ThreadLocal<Object> currentTenant = new ThreadLocal<>();

	public static Map<String, String> tenantsMap;

	@Value("${tenantsMap}")
	private String stringTenantsMap;

	@PostConstruct
	public void init() {

		tenantsMap = new HashMap<String, String>();
		tenantsMap.put("default", "default");
		File file = Paths.get(stringTenantsMap).toFile();
		Properties tenantProperties = new Properties();
		try {
			tenantProperties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tenantNames = tenantProperties.getProperty("tenantName");
		String databaseNames = tenantProperties.getProperty("databaseName");
		String arrayTenantNames[] = tenantNames.split(",");
		String arrayDatabaseNames[] = databaseNames.split(",");

		for (int numeroTenant = 0; numeroTenant < arrayDatabaseNames.length; numeroTenant++) {
			tenantsMap.put(arrayTenantNames[numeroTenant], arrayDatabaseNames[numeroTenant]);
		}
	}

	public static void setCurrentTenant(Object tenant) {
		String s = tenantsMap.get(tenant);
		currentTenant.set(s);
	}

	
	public static Object getCurrentTenant() {
		
		return currentTenant.get();
	}
}
