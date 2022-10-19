package com.wigo.voc.config;

import javax.sql.DataSource;

import com.wigo.voc.sys.mapper.CrmLogMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * <pre>
 * com.wigo.crm.config - DatabaseConfig.java
 * </pre>
 *
 * @ClassName : DatabaseConfig
 * @Description : DB 설정
 * @author : 김성태
 * @date : 2021. 1. 5.
 * @Version : 1.0
 * @Company : Copyright ⓒ wigo.ai. All Right Reserved
 */

@Configuration
@MapperScan(basePackages = {"com.wigo.voc"}, value = "최상위 패키지 경로", annotationClass = CrmLogMapper.class, sqlSessionFactoryRef = "logSqlSessionFactory")
public class LogDatabaseConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Value("${spring.datasource.log-driver-class-name}")
	private String dbDriverClassName;

	@Value("${spring.datasource.log-url}")
	private String dbURL;

	@Value("${spring.datasource.log-username}")
	private String userName;

	@Value("${spring.datasource.log-password}")
	private String password;
	
	
	
	
	
	@Value("${spring.datasource.hikari.connection-timeout}")
	private int timeout;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int poolSize;
	@Bean(name = "logDataSource")
	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName(dbDriverClassName);
//		dataSource.setUrl(dbURL);
//		dataSource.setUsername(userName);
//		dataSource.setPassword(password);
//		return dataSource;
		
		HikariConfig hikariConfig = new HikariConfig();  // 3
		if(poolSize <= 0)
			poolSize = 100;
		if(timeout <=0)
			timeout = 60000;
		
		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(password);
		hikariConfig.setJdbcUrl(dbURL);
		hikariConfig.setConnectionTestQuery("SELECT 1 from dual");
		hikariConfig.setMaximumPoolSize(poolSize);  
		hikariConfig.setConnectionTimeout(timeout);
		hikariConfig.setLeakDetectionThreshold(30000);
		hikariConfig.setPoolName("wigo-voc-pool");
		return new HikariDataSource(hikariConfig);
	}

	@Bean(name = "logSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("logDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean
				.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis/mybatis-config-base.xml"));
		sqlSessionFactoryBean
				.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*_SqlMapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.wigo.**.model,com.wigo.**.entity");
		return sqlSessionFactoryBean.getObject();
	}

//
	@Bean(name = "logSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	

}
