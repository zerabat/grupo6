package com.grupo6.service;

import org.springframework.data.jpa.domain.Specification;

public interface ParserDeBusquedaService <T> {
	
	Specification<T> parseParamns(String param , String operador);
	void setClass(Class<T> s);

}
