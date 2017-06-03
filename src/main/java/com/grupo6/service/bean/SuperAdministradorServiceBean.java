package com.grupo6.service.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.grupo6.service.SuperAdministradorService;

@Service
public class SuperAdministradorServiceBean implements SuperAdministradorService {


	@Value("${superAdminPass}")
	private String superAdminPass;
	
	
	@Override
	public boolean esSuperAdmin(String password) {
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		if (sha256hex.equals(superAdminPass)){
			return true;
		}else {
			return false;
		}
		
	}

	
}
