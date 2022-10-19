package com.wigo.voc.config;

import javax.sql.DataSource;

import com.wigo.voc.sys.mapper.CrmMapper;
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
import org.springframework.context.annotation.Primary;

import com.wigo.voc.common.log.LogDaoAspect;
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
@Primary
@Configuration("crmDatabaseConfig")
@MapperScan(basePackages = {"com.wigo.voc"}, value = "최상위 패키지 경로", annotationClass = CrmMapper.class, sqlSessionFactoryRef = "crmSqlSessionFactory")
public class CrmDatabaseConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriverClassName;

	@Value("${spring.datasource.url}")
	private String dbURL;

	@Value("${spring.datasource.username}")
	private String userName;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private int timeout;
	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int poolSize;
	
	@Primary
	@Bean(name = "crmDataSource")
	public DataSource dataSource() {
		if(poolSize <= 0)
			poolSize = 100;
		if(timeout <=0)
			timeout = 60000;
		HikariConfig hikariConfig = new HikariConfig();  // 3

		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(password);
		hikariConfig.setJdbcUrl(dbURL);
		hikariConfig.setMaximumPoolSize(poolSize);  
		hikariConfig.setConnectionTimeout(timeout);
		hikariConfig.setLeakDetectionThreshold(30000);
		hikariConfig.setPoolName("wigo-voc-pool");
		return new HikariDataSource(hikariConfig);
	}
	@Primary
	@Bean(name = "crmSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("crmDataSource") DataSource dataSource,@Qualifier("logDaoAspect") LogDaoAspect interceptor) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis/mybatis-config-base.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*_SqlMapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.wigo.**.model,com.wigo.**.entity");
		sqlSessionFactoryBean.setPlugins(interceptor);
		return sqlSessionFactoryBean.getObject();
	}

//
	@Primary
	@Bean(name = "crmSqlSessionFactory")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
