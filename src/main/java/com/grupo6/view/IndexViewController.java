package com.grupo6.view;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{tenantId}")
public class IndexViewController {

    @RequestMapping(path = "/index")
    public String index(@PathVariable String tenantId, Map<String, Object> model) {
   	 return "index";
    }
    
    @RequestMapping(path = "/indexAdmin")
    public String indexAdmin(@PathVariable String tenantId, Map<String, Object> model) {
   	 return "indexAdmin";
    }
    
    @RequestMapping(path = "/login")
    public String login(Map<String, Object> model) {
   	 return "login";
    }
    
    @RequestMapping(path = "/loginAdmin")
	public String loginAdmin(Map<String, Object> model) {
    	return "loginAdmin";
	}
    
    @RequestMapping(path = "/register")
    public String register(@PathVariable String tenantId, Map<String, Object> model) {
   	 return "register";
    }
    
    @RequestMapping(path = "/infoEspectaculo")
    public String infoEspectaculo(@PathVariable String tenantId, Map<String, Object> model) {
   	 return "infoEspectaculo";
    }
}


