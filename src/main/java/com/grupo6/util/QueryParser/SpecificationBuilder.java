package com.grupo6.util.QueryParser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class SpecificationBuilder<T> {

	private final List<SearchCriteria> searchCriteriaList;
	private OperadorLogicoEnum operador;

	public SpecificationBuilder() {
		searchCriteriaList = new ArrayList<SearchCriteria>();
		operador = OperadorLogicoEnum.OP_OR;
	}

	public SpecificationBuilder(OperadorLogicoEnum operador) {
		searchCriteriaList = new ArrayList<SearchCriteria>();
		this.operador = operador;
	}

	public SpecificationBuilder<T> with(String key, String operator, Object value) {
		searchCriteriaList.add(new SearchCriteria(key, operator, value));
		return this;
	}

	public Specification<T> build(){
		Specification<T> result = null;
		
		if (searchCriteriaList.size() > 0){
			
			List<Specification<T>> specs = new ArrayList<Specification<T>>();
			for (SearchCriteria param : searchCriteriaList){
				specs.add(new ProyectoSpecification<T>(param));
			}
			
			result = specs.get(0);
			for (int i=1; i< specs.size() ; i++){
				if (operador==OperadorLogicoEnum.OP_AND){
					result = Specifications.where(result).and(specs.get(i));
				}else {
					result = Specifications.where(result).or(specs.get(i));
				}
			}
		}
		return result ;
	}
}
