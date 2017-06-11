package com.grupo6.util.QueryParser;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.grupo6.util.reflection.ReflectionUtils;



public class ProyectoSpecification<T> implements Specification<T> {

	private SearchCriteria searchCriteria;

	public ProyectoSpecification(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (this.searchCriteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(root.<String>get(this.searchCriteria.getKey()),
					this.searchCriteria.getValue().toString());

		} else if (this.searchCriteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(root.<String>get(this.searchCriteria.getKey()),
					this.searchCriteria.getValue().toString());

		} else if (this.searchCriteria.getOperation().equalsIgnoreCase(":")) {

			StringTokenizer tokenizer = new StringTokenizer(this.searchCriteria.getKey(), ".");
			int count = tokenizer.countTokens();

			switch (count) {
			case 1:
				if (root.get(this.searchCriteria.getKey()).getJavaType() == String.class) {
					return builder.like(root.<String>get(this.searchCriteria.getKey()), // like
							"%" + this.searchCriteria.getValue() + "%");
				} else {
					Object objSearchCriteriaValue = this
							.getCriteriaKeyObject(root.get(this.searchCriteria.getKey()).getJavaType());
					if (objSearchCriteriaValue != null) {
						return builder.equal(root.get(this.searchCriteria.getKey()), objSearchCriteriaValue); // equal
					}
				}
				break;
			case 2:
				String st01 = tokenizer.nextToken();
				String st02 = tokenizer.nextToken();

				Class<?> collType = null;
				try {
					List<Field> fs = ReflectionUtils.getAllFields(root.getJavaType());
					Field field = null;
					for (Field field1 : fs) {
						if (field1.getName().equals(st01)) {
							field = field1;
							break;
						}
					}
					collType = ReflectionUtils.getListParameterizedType(field);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (collType != null) {
					Join join = root.join(st01,JoinType.LEFT);

					if (join.get(st02).getJavaType() == String.class) {
						return builder.like(join.get(st02), // like
								"%" + this.searchCriteria.getValue() + "%");
					} else {
						Class<?> s = root.join(st01,JoinType.LEFT).get(st02).getJavaType();

						Object objSearchCriteriaValue = this.getCriteriaKeyObject(s);
						if (objSearchCriteriaValue != null) {

							 if (s.isEnum()) {
								 return builder.equal(join.get(st02), objSearchCriteriaValue); // equal
							 }

							
							
						} 
					}
				}

				if (root.join(st01,JoinType.LEFT).get(st02).getJavaType() == String.class) {
					return builder.like(root.join(st01,JoinType.LEFT).get(st02), // like
							"%" + this.searchCriteria.getValue() + "%");
				} else {
					Object objSearchCriteriaValue = this.getCriteriaKeyObject(root.get(st01).get(st02).getJavaType());
					if (objSearchCriteriaValue != null) {
						return builder.equal(root.get(st01).get(st02), objSearchCriteriaValue); // equal
					} 
				}
				break;
			case 3:
				st01 = tokenizer.nextToken();
				st02 = tokenizer.nextToken();
				String st03 = tokenizer.nextToken();

				collType = null;

				try {
					List<Field> fs = ReflectionUtils.getAllFields(root.getJavaType());
					Field field = null;
					for (Field field1 : fs) {
						if (field1.getName().equals(st01)) {
							field = field1;
							break;
						}
					}
					collType = ReflectionUtils.getListParameterizedType(field);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (collType != null) {
					Join join = root.join(st01,JoinType.LEFT);
					// firstPart=join.get(st02);

					if (join.get(st02).getJavaType() == String.class) {
						return builder.like(join.get(st02), // like
								"%" + this.searchCriteria.getValue() + "%");
					} else {

						Field field = null;
						try {
							field = root.getJavaType().getDeclaredField(st01);
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						try {
							collType = ReflectionUtils.getListParameterizedType(field);
						} catch (Exception e) {
							e.printStackTrace();
						}

						Class<?> s = root.join(st01).get(st02).get(st03).getJavaType();
						Object objSearchCriteriaValue = this.getCriteriaKeyObject(s);
						if (objSearchCriteriaValue != null) {
							return builder.equal(root.get(st01).get(st02).get(st03), objSearchCriteriaValue); // equal
						} 
					}
				}

				if (root.join(st01).get(st02).get(st03).getJavaType() == String.class) {
					return builder.like(root.join(st01).get(st02).get(st03), // like
							"%" + this.searchCriteria.getValue() + "%");
				} else {
					Class<?> s = root.join(st01).get(st02).get(st03).getJavaType();
					Object objSearchCriteriaValue = this.getCriteriaKeyObject(s);
					if (objSearchCriteriaValue != null) {
						return builder.equal(root.get(st01).get(st02).get(st03), objSearchCriteriaValue); // equal

					} 
				}
				break;

			default:
				break;
			}

		}
		return null;
	}

	/**
	 * Casting to different data types, add new Enums if needed
	 *
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object getCriteriaKeyObject(Class clazz) {
		try {
			if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
				return new Boolean(this.searchCriteria.getValue().toString());

			} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
				return new Integer(this.searchCriteria.getValue().toString());

			} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
				return new Long(this.searchCriteria.getValue().toString());

			} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
				return new Double(this.searchCriteria.getValue().toString());

			} else if (clazz.equals(BigDecimal.class)) {
				return new BigDecimal(this.searchCriteria.getValue().toString());
			} else if (clazz.equals(Date.class)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				return format.parse(this.searchCriteria.getValue().toString());
			} 
//				else if (clazz.equals(PriorityName.class)) {
//				return PriorityName.Primary;
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
