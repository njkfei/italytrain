package com.sanhao.bdimage.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author lanyonm
 */
@Configuration
@MapperScan("com.sanhao.bdimage.dao")
@ComponentScan(basePackages ={  "com.sanhao.bdimage.service"})
@PropertySource(value = { "classpath:conf/jdbc.properties", "classpath:conf/log4j.properties","classpath:conf/app.properties" })
public class MybatisDataConfig {
	private static Logger logger = Logger.getLogger(MybatisDataConfig.class);

	@Value("${jdbc.driverClassName:com.mysql.jdbc.Driver}")
	private String driverClassName;

	@Value("${jdbc.url:jdbc:mysql://localhost:3306/test}")
	private String url;

	@Value("${jdbc.username:root}")
	private String username;

	@Value("${jdbc.password:root}")
	private String password;

	@Autowired
	private Environment environment;

    //You need this
	// 必须要有这一行，否则上面的＠VALUE无法注入
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		logger.info(driverClassName);
		logger.info(url);
		logger.info(username);
		logger.info(password);
		logger.info(environment==null);
		
		logger.info(environment.getRequiredProperty("jdbc.driverClassName"));
		logger.info(environment.getRequiredProperty("jdbc.url"));
		logger.info(environment.getRequiredProperty("jdbc.username"));
		logger.info(environment.getRequiredProperty("jdbc.password"));
		
		
/*		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sanhao_test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");*/
		
/*		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));*/
		
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.sanhao.bdimage.model");
		return sessionFactory;
	}
}
