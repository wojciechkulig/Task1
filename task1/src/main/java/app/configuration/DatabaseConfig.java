package app.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages={"app.data.repository.springDataImpl"})
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Bean
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;INIT=runscript from 'src/main/resources/create.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
	}
	
	private Properties additionalProperties() {
		 Properties properties = new Properties();
	     properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	     properties.setProperty("hibernate.show_sql", "false");
	     //properties.setProperty("hibernate.id.new_generator_mappings","false");
	     return properties;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("app.data.entities");
	    factory.setDataSource(dataSource());
	    factory.setJpaProperties(additionalProperties());
	    return factory;
	}
	
	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(emf);
	    return txManager;
	  }
	
}
