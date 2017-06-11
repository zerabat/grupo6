package com.grupo6.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
	
	@SuppressWarnings("rawtypes")
	public static List<Field> getAllFields(Class clazz){
		List <Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		Class superClazz = clazz.getSuperclass();
		if (superClazz != null){
			fields.addAll(ReflectionUtils.getAllFields(superClazz));
		}
		return fields;
		
	}

	public static Class<?> getListParameterizedType(Field field) throws ClassNotFoundException{
		boolean collection = false;
		for (Class<?> interface_ : field.getType().getInterfaces()){
			if (interface_.equals(java.util.Collection.class)){
				collection = true ;
				break;
			}
		}
		if (collection){
			ParameterizedType pType = ((ParameterizedType) field.getGenericType());
			Type argType = pType.getActualTypeArguments()[0];
			return Class.forName(argType.getTypeName());
			
		}
		return null;
	}

}
