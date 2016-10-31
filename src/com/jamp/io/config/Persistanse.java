package com.jamp.io.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.dao.UserDaoJPA;
import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.model.pojo.User;
import com.jamp.io.service.UserService;
import com.jamp.io.service.UserServiceImpl;

/**
 * All configuration related to persistence layer and database related
 */
@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan (basePackages="com.jamp.io.model")
public class Persistanse {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

//	private Map<String, Object> jpaProperties() {
//		final Map<String, Object> map = new HashMap<>();
//		map.put("eclipselink.weaving", "false");
// 
//		return map;
//	}
//	
//	@Bean(initMethod = "migrate")
//	Flyway flyway() {
//		Flyway flyway = new Flyway();
//		flyway.setDataSource(dataSource());
//		flyway.baseline();
//		flyway.setLocations("com.jamp.io.model.dbmigration");
//		return flyway;
//	}
	
    @Bean
//	@DependsOn(value = { "flyway" })
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.jamp.io.model");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
  
       return transactionManager;
    }
 
    @Bean
    public UserDao<User> userDao() {
    	return new UserDaoJPA<User>(User.class);
    }

    @Bean
    public UserDao<Mentor> mentorDao() {
    	return new UserDaoJPA<Mentor>(Mentor.class);
    }

    @Bean
    public UserDao<Participant> participantDao() {
    	return new UserDaoJPA<Participant>(Participant.class);
    }
    
    @Bean
    @DependsOn("entityManagerFactory")
    public UserService userService() {
    	return new UserServiceImpl();
    }
    
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
    }
}
