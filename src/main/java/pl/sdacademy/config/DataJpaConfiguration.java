
package pl.sdacademy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("pl.sdacademy")
@PropertySource("classpath:jpa.properties")
public class DataJpaConfiguration {
    private String propHbm2Ddl;
    private String propHibernateDialect;
    private String propJdbcDriver;
    private String propJdbcDatasourceUrl;
    private String propJdbcDatasourceUsername;
    private String propJdbcDatasourcePassword;
    private String propEntityPackage;

    public DataJpaConfiguration(
            @Value("${hibernate.hbm2ddl.auto}") String propHbm2Ddl,
            @Value("${hibernate.dialect}") String propHibernateDialect,
            @Value("${jdbc.driver}") String propJdbcDriver,
            @Value("${jdbc.datasource.url}") String propJdbcDatasourceUrl,
            @Value("${jdbc.datasource.username}") String propJdbcDatasourceUsername,
            @Value("${jdbc.datasource.password}") String propJdbcDatasourcePassword,
            @Value("${jpa.entity-package}") String propEntityPackage) {
        this.propHbm2Ddl = propHbm2Ddl;
        this.propHibernateDialect = propHibernateDialect;
        this.propJdbcDriver = propJdbcDriver;
        this.propJdbcDatasourceUrl = propJdbcDatasourceUrl;
        this.propJdbcDatasourceUsername = propJdbcDatasourceUsername;
        this.propJdbcDatasourcePassword = propJdbcDatasourcePassword;
        this.propEntityPackage = propEntityPackage;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(propEntityPackage);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", propHbm2Ddl);
        properties.setProperty("hibernate.dialect", propHibernateDialect);
        entityManagerFactoryBean.setJpaProperties(properties);
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propJdbcDriver);
        dataSource.setUrl(propJdbcDatasourceUrl);
        dataSource.setUsername(propJdbcDatasourceUsername);
        dataSource.setPassword(propJdbcDatasourcePassword);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

