package com.grupo6.util.QueryParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class QueryParserParamsServiceBean {

	private static final String SEARCH_PATTERN = "([a-zA-Z0-9.]+?)(:|<|>)(((\\w|\\s|\\d|;)+?)|(\\d{4}--\\d{2}--\\d{2})+),";


	public List<String> matchParams(String params, Function<String[], String> fn){
		Pattern pattern = Pattern.compile(SEARCH_PATTERN);
		Matcher matcher = pattern.matcher(params + ",");
		
		List<String > result = new ArrayList<>();
		while(matcher.find()){
			String[] groups = new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
			result.add(fn.apply(groups));
		}
		return result;
		
	}
}

