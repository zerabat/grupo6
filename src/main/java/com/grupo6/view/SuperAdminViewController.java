package com.grupo6.view;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SuperAdminViewController {

    @RequestMapping(path = "/indexSuperAdmin")
    public String indexSuperAdmin(Map<String, Object> model) {
   	 return "indexSuperAdmin";
    }
    

    @RequestMapping(path = "/loginSuperAdmin")
	public String loginSuperAdmin(Map<String, Object> model) {
    	return "loginSuperAdmin";
	}
    
   
}


