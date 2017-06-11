package com.grupo6.util.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public abstract class PageUtils {
	
	protected static final int PAGE_SIZE_DEFAULT = 20;
	
	public static Pageable getPageRequest(int start, int end, String sortField, String sortOrder){
		
		Sort sort = PageUtils.getSort(sortField, sortOrder);
		
		int limit = end-start + 1 ;
		if (limit < 0){
			limit = PageUtils.PAGE_SIZE_DEFAULT;
		}
		int number = (end / limit) - 1;
		if (number < 0){
			number = 0;
		}
		Pageable pageable = null;
		if (sort !=null){
			pageable = new PageRequest(number,limit,sort);
		}else {
			pageable = new PageRequest(number,limit);
		}
		return pageable;
	}

	public static Sort getSort(String sortField, String sortOrder){
		Order order = null;
		Sort sort = null;
		
		if (StringUtils.isNotBlank(sortField)){
			if (StringUtils.equalsIgnoreCase(sortOrder, Sort.Direction.DESC.toString())){
				order = new Order (Sort.Direction.DESC, sortField);
				
					
			}else {
				order = new Order(Sort.Direction.ASC, sortField);
			}
			sort = new Sort(order);
		}
		return sort;
	}
}
