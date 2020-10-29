package com.snkit.springprofiledemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.snkit.springprofiledemo.entity.AddressEntity;
import com.snkit.springprofiledemo.entity.CustAddresVO;
import com.snkit.springprofiledemo.entity.CustAddressSpecification;
import com.snkit.springprofiledemo.entity.CustomUserEntity;
import com.snkit.springprofiledemo.entity.CustomerSpecification;
import com.snkit.springprofiledemo.repository.CustomUserRepository;

@SpringBootApplication
public class SpringprofiledemoApplication implements CommandLineRunner {

	@Autowired
	CustomUserRepository customUserRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("  From CommandLinerRunner method run ");

		custAddressSpec();
		/*
		 * Optional<CustomUserEntity> optinalUser = customUserRepository.findAll(spec)
		 * 
		 * if (optinalUser.isPresent()) { CustomUserEntity userEntity =
		 * optinalUser.get();
		 * 
		 * System.out.println(userEntity);
		 * 
		 * System.out.println(userEntity.getAddressList());
		 */

		/*
		 * Optional<UserEntity> optinalUser = userRepository.findById(new Long(452));
		 * 
		 * if (optinalUser.isPresent()) { UserEntity userEntity = optinalUser.get();
		 * 
		 * System.out.println(userEntity);
		 * 
		 * // System.out.println(userEntity.getAddressList()); }
		 */
		/*
		 * userList.forEach(user -> { System.out.println(user);
		 * 
		 * System.out.println(user.getAddressList()); });
		 */

	}

	private void custAddressSpec() {
		
		CustAddresVO custAddressVO = new CustAddresVO();
		custAddressVO.setCountry("Dubai");
		CustAddressSpecification specfication = new CustAddressSpecification(custAddressVO);
		
		  List<CustomUserEntity> custList = customUserRepository.findAll(specfication);
		  
		  custList.forEach(cust -> {
			  System.out.println(cust);
			  
			  System.out.println(cust.getAddressList());
			  
			  
		  });
		  
		  System.out.println("  After Specificaiton by Country ");
		  
		  CustAddresVO custAddressVO2 = new CustAddresVO();
		  custAddressVO2.setCountry("London");
		  custAddressVO2.setName("sssdemoUniDirectionalcompany");
			CustAddressSpecification specfication2 = new CustAddressSpecification(custAddressVO2);
			
			  List<CustomUserEntity> custList2 = customUserRepository.findAll(specfication2);
			  
			  custList2.forEach(cust -> {
				  System.out.println(cust);
				  
				  System.out.println(cust.getAddressList());
			  });
			  System.out.println("  After Specificaiton by Country  & name ");
			  
			  CustAddresVO custAddressVO3 = new CustAddresVO();
			  custAddressVO3.setName("sssdemoUniDirectionalcompany");
			  custAddressVO3.setEmail("sssUnidirectional@test.com");
				CustomerSpecification specfication3 = new CustomerSpecification(custAddressVO3);
				
				  List<CustomUserEntity> custList3 = customUserRepository.findAll(specfication3);
				  
				  custList3.forEach(cust -> {
					  System.out.println(cust);
					  
					  System.out.println(cust.getAddressList());
				  });
		 
	}

	private void joinfetchExample() {
		List<Long> ids = new ArrayList();

		/*
		 * ids.add(new Long(452)); ids.add(new Long(455)); ids.add(new Long(458));
		 */
		ids.add(new Long(486));
		List<CustomUserEntity> userList = customUserRepository.getUserDataActiveAdd(new Long(461)); //
		// if it is EAGER this level it will fetch all eager referance into memroy

		userList.forEach(userEntity -> {

			System.out.println(userEntity);

			System.out.println(userEntity.getAddressList());// if it lazy hibernate fire

			// fires query
		});

		System.out.println("with out join fetch ");
		System.out.println("with out join fetch ");
		System.out.println("with out join fetch ");
		System.out.println("with out join fetch ");
		System.out.println("with out join fetch ");

		List<CustomUserEntity> userJoinList = customUserRepository.getUserData(new Long(461)); //
		// if it is EAGER this level it will fetch all eager referance into memroy

		userJoinList.forEach(userEntity -> {

			System.out.println(userEntity);

			System.out.println(userEntity.getAddressList());// if it lazy hibernate fire

			// fires query
		});

	}

	private void saveUserAddress() {

		CustomUserEntity user = new CustomUserEntity();
		user.setEmail("sssUnidirectional@test.com");
		user.setName("sssdemoUniDirectionalcompany");

		AddressEntity ad = new AddressEntity();
		ad.setCountry("Dubai");
		ad.setStreet("BurDubai");
		ad.setStatus("Y");
		// ad.setUserEntity(user);

		AddressEntity ad2 = new AddressEntity();
		ad2.setCountry("India");
		ad2.setStreet("Hyd");
		ad2.setStatus("Y");
		// ad2.setUserEntity(user);

		user.getAddressList().add(ad);

		user.getAddressList().add(ad2);

		customUserRepository.save(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringprofiledemoApplication.class, args);
	}

}
