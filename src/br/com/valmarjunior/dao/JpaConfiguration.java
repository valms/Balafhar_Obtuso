package br.com.valmarjunior.dao;

/// http://websystique.com/spring-boot/spring-boot-angularjs-spring-data-jpa-crud-app-example/

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.valmarjunior.repositories", entityManagerFactoryRef = "ntityManagerFactory", transactionManagerRef = "transactionManager")
public class JpaConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Value("${datasource)")
	private int maxPoolSize;
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.balafharapp")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
		HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder
			                                                       .create( dataSourceProperties.getClassLoader() )
			                                                       .driverClassName( dataSourceProperties.getDriverClassName() )
			                                                       .url( dataSourceProperties.getUrl() )
			                                                       .username( dataSourceProperties.getUsername() )
			                                                       .password( dataSourceProperties.getPassword() )
			                                                       .type( HikariDataSource.class )
			                                                       .build();
		hikariDataSource.setMaximumPoolSize( maxPoolSize );
		return hikariDataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() throws NamingException {
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		localContainerEntityManagerFactoryBean.setDataSource( dataSource() );
		localContainerEntityManagerFactoryBean.setPackagesToScan( "br.com.valmarjunior.model" );
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter( jpaVendorAdapter() );
		localContainerEntityManagerFactoryBean.setJpaProperties( jpaProperties() );
		
		return localContainerEntityManagerFactoryBean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put( "hibernate.dialect", environment.getRequiredProperty( "datasource.balafharapp.hibernate.dialect" ) );
		properties.put( "hibernate.hbm2ddl.auto", environment.getRequiredProperty( "datasource.balafharapp.hibernate.hbm2ddl.method" ) );
		properties.put( "hibernate.show_sql", environment.getRequiredProperty( "datasource.balafharapp.hibernate.show_sql" ) );
		properties.put( "hibernate.format_sql", environment.getRequiredProperty( "datasource.balafharapp.hibernate.format_sql" ) );
		return properties;
	}
	
	
}
