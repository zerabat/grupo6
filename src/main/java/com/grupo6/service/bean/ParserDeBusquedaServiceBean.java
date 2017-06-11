package com.grupo6.service.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grupo6.service.ParserDeBusquedaService;
import com.grupo6.util.QueryParser.OperadorLogicoEnum;
import com.grupo6.util.QueryParser.QueryParserParamsServiceBean;
import com.grupo6.util.QueryParser.SpecificationBuilder;
import com.grupo6.util.reflection.ReflectionUtils;

@Service
public class ParserDeBusquedaServiceBean<T> implements ParserDeBusquedaService<T> {

	protected Class<T> clazz;

	@Autowired
	protected QueryParserParamsServiceBean serviceQ;

	protected String mapQueryString(String params) {
		return params;
	}

	@Override
	public Specification<T> parseParamns(String params, String operador) {
		SpecificationBuilder<T> builder = new SpecificationBuilder<>(OperadorLogicoEnum.fromString(operador));
		
		List<Field> listaDecampos = ReflectionUtils.getAllFields(this.clazz);
		List<String> listaNombreDeCampos = listaDecampos.stream().map(Field::getName).collect(Collectors.toList());
		
		serviceQ.matchParams(params, groups ->{
			StringTokenizer tokenizer = new StringTokenizer(groups[0], "." );
			if (this.isValidField(tokenizer, listaDecampos, listaNombreDeCampos)){
				builder.with(groups[0],groups[1],groups[2]);
			}
			return null;
		});
		
		return builder.build();
	}

	private boolean isValidField(StringTokenizer tokenizer, List<Field> listaDecampos,
			List<String> listaNombreDeCampos) {
		String token = tokenizer.nextToken();
		int idx = listaNombreDeCampos.indexOf(token);
		if (idx >= 0) {
			if (tokenizer.hasMoreTokens()) {
				Class<?> type = listaDecampos.get(idx).getType();

				try {
					Class<?> collectionType = ReflectionUtils.getListParameterizedType(listaDecampos.get(idx));
					if (collectionType != null) {
						type = collectionType;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				List<Field> listaDecamposB = ReflectionUtils.getAllFields(type);
				List<String> listaNombreDeCamposB = listaDecamposB.stream().map(Field::getName)
						.collect(Collectors.toList());
				return this.isValidField(tokenizer, listaDecamposB, listaNombreDeCamposB);
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public void setClass(Class<T> s) {
		this.clazz = s;

	}

}
