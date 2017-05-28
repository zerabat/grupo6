package com.grupo6.view;


import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{tenantId}")

public class TestViewController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	
	@RequestMapping(path = "/test")
	public String welcome(@PathVariable String tenantId, Map<String, Object> model) {
		System.out.println(tenantId);
		model.put("message", this.message);
		return "test";
	}

}
