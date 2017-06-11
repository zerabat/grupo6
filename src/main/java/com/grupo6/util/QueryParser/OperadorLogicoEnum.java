package com.grupo6.util.QueryParser;

public enum
OperadorLogicoEnum {
	OP_AND, OP_OR;
	
	public static OperadorLogicoEnum fromString(String str){
		if (str !=null){
			str  = str.toUpperCase();
			if (str.equals("AND")){
				return OperadorLogicoEnum.OP_AND;
			}
		}
		return OperadorLogicoEnum.OP_OR;
		
	}

}
