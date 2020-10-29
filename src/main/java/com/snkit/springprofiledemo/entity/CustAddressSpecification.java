package com.snkit.springprofiledemo.entity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class CustAddressSpecification implements Specification<CustomUserEntity> {

	public CustAddresVO custAddressVO;

	public CustAddressSpecification(CustAddresVO custAddresVO) {
		this.custAddressVO = custAddresVO;
	}

	@Override
	public Predicate toPredicate(Root<CustomUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		Predicate predicate = criteriaBuilder.conjunction();

		Join<CustomUserEntity, AddressEntity> custAddressJoin = root.join("addressList", JoinType.LEFT);

		if (custAddressVO.getEmail() != null) {
			System.out.println("    getCustBySpecification  filter.getEmail() != null  ============================ ");

			predicate.getExpressions().add(criteriaBuilder.equal(root.get("email"), custAddressVO.getEmail()));
		}

		if (custAddressVO.getName() != null) {
			predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + custAddressVO.getName() + "%"));
		}

		if (custAddressVO.getCountry() != null) {

			System.out.println("    getCustBySpecification  filter.getCountry()!= null  ============================ ");
			predicate.getExpressions()
					.add(criteriaBuilder.equal(custAddressJoin.get("country"), custAddressVO.getCountry()));
			
		
		}

		
		
		query.distinct(true);

		return predicate;
	}

}
