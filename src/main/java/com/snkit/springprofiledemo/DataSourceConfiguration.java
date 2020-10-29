package com.snkit.springprofiledemo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/microservice?useSSL=false&&verifyServerCertificate=false&allowPublicKeyRetrieval=true");
	    dataSource.setUsername( "demouser" );
	    dataSource.setPassword( "Sairam123$" );
	    return dataSource;
	}
	
}
