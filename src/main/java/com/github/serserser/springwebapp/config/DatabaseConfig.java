package com.github.serserser.springwebapp.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = { "com.github.serserser.springwebapp.*" })
@EnableJpaRepositories(basePackages = "com.github.serserser.springwebapp.repositories")
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(OracleDataSource.class.getName());
        dataSource.setUrl("jdbc:oracle:thin:@192.168.56.110:1521:XE");
        dataSource.setUsername("spring");
        dataSource.setPassword("spring");

        return dataSource;
    }

    @DependsOn("flywayDBUpdater")
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("com.github.serserser.springwebapp.domain",
                "com.github.serserser.springwebapp.db");
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(jpaProperties());
        entityManagerFactory.setSharedCacheMode(SharedCacheMode.NONE);
        entityManagerFactory.setJtaDataSource(dataSource);

        return entityManagerFactory;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("eclipselink.session-event-listener", "com.github.serserser.springwebapp.db.SessionAuditListener");
        return properties;
    }

    @Bean
    public AbstractJpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();

        jpaVendorAdapter.setDatabase(Database.ORACLE);
        return jpaVendorAdapter;
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);

        return transactionManager;
    }
}
