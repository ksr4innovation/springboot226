package com.snkit.springprofiledemo.entity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification implements Specification<CustomUserEntity> {

	public CustAddresVO custAddressVO;

	public CustomerSpecification(CustAddresVO custAddresVO) {
		this.custAddressVO = custAddresVO;
	}

	@Override
	public Predicate toPredicate(Root<CustomUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.conjunction();

		if (custAddressVO.getEmail() != null) {
			System.out.println("    getCustBySpecification  filter.getEmail() != null  ============================ ");

			predicate.getExpressions().add(criteriaBuilder.equal(root.get("email"), custAddressVO.getEmail()));
		}

		if (custAddressVO.getName() != null) {
			predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + custAddressVO.getName() + "%"));
		}



		return predicate;
	}

}

