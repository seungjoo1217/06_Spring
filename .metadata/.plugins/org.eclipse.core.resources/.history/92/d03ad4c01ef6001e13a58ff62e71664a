package edu.kh.project.common.config;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/* @Configuration
 * - 설정용 클래스임 명시
 * + 객체로 생성해서 내부 코드를 서버 실행시 모두 수행
 * 
 * @PropertySource("경로")
 * - 지정된 경로의 properties 파일 내용을 읽어와 사용
 * - 사용할 properties 파일이 다수일 경우
 * 해당 어노테이션을 연속해서 작성하면 됨
 * 
 * @ConfigurationProperties(prefix="spring.datasource.hikari")
 * - @PropertySource 로 읽어온 properties 파일의 내용 중
 *   접두사 (앞부분, prefix)가 일치하는 값만 읽어옴
 *   
 * @Bean
 * - 개발자가 수동으로 생성한 객체의 관리를
 *   스프링에게 넘기는 어노테이션
 *   (Bean 등록)
 *   
 *   
 * DataSource : Connection 생성 + Connection Pool 지원하는 객체를
 * 				참조하기 위한 Java 인터페이스
 * 				(Drivermanager 대안, Java JNDI 기술 이용)
 * 
 * 
 * @Autowired
 * - 등록된 Bean 중에서
 * 	 타입이 일치하거나, 상속관계에 있는 Bean을
 *   지정된 필드에 주입
 *   == 의존성 주입(DI, Dependency Injection, IOC 관련 기술)
 * */



@Configuration
@PropertySource("classpath:/config.properties")
public class DBConfig {
	
	// 필드
	
	// org.springframework.context.ApplicationContext;
	
	@Autowired // (DI, 의존성주입)
	private ApplicationContext applicationContext; //application scope 객체 : 즉, 현재 프로젝트
	
	/////////////// HikariCP 설정 //////////////////////////
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource(HikariConfig config) {
		// 매개변수 HikariConfig config
		// -> 등록된 Bean 중 HikariConfig 타입의 Bean 자동으로 주입
		
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	/////////////// Mybatis 설정 ////////////////////////
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		sessionFactoryBean.setDataSource(dataSource);
		
		
		
		// mapper.xml(SQL) 파일이 모이는 경로 지정
		// -> Mybatis 코드 수행 시 mapper.xml을 읽을 수 있음
		// sessionFactoryBean.setMapperLocations("현재프로젝트.자원.어떤파일");
		
		sessionFactoryBean.setMapperLocations(
					applicationContext.getResources("classpath:/mappers/**.xml"));
		
		// 해당 패키지 내 모든 클래스의 별칭을 등록
		// - Mybatis는 특정 클래스 지정 시 패키지명.클래스명을 모두 작성해야함
		// -> 너무 길다.. 긴 이름을 짧게 부를 별칭 설정할 수 있음
		
		// setTypeAliasePackage("패키지") 이용 시
		// 클래스 파일명이 별칭으로 등록
		
		// ex) edu.kh.todo.model.dto.Todo  -->  Todo (별칭 등록)
		sessionFactoryBean.setTypeAliasesPackage("edu.kh.project");
		
		// 마이바티스 설정 파일 경로 지정
		sessionFactoryBean.setConfigLocation(
					applicationContext.getResource("classpath:/mybatis-config.xml")
				);
		
		// 설정 내용이 모두 적용된 객체 반환
		return sessionFactoryBean.getObject();
		
	}
	
	
	// SqlSessionTemplate : Connection + DBCP + Mybatis + 트랜잭션 제어 처리
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
	return new SqlSessionTemplate(sessionFactory);
	}
	
	// DataSourceTransactionManager : 트랜잭션 매니저(제어 처리)
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
	return new DataSourceTransactionManager(dataSource);
	}
	
	
	
	
	
	
	
	
	
	
	
}
