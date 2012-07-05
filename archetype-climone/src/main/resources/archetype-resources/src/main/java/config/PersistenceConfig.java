package ${package}.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    // private static final String DATASOURCE_JNDINAME = "datasource.jndiname";

    private static final String DATASOURCE_DRIVER = "datasource.driver";
    private static final String DATASOURCE_URL = "datasource.url";
    private static final String DATASOURCE_USERNAME = "datasource.username";
    private static final String DATASOURCE_PASSWORD = "datasource.password";
    private static final String DATASOURCE_INITIALSIZE = "datasource.initialSize";
    private static final String DATASOURCE_MAXACTIVE = "datasource.maxActive";
    private static final String DATASOURCE_MAXIDLE = "datasource.maxIdle";

    @Autowired
    private Environment env;

    // @Bean
    // public DataSource dataSourceJndi() {
    // JndiDataSourceLookup jndiDataSource = new JndiDataSourceLookup();
    // return jndiDataSource.getDataSource(env.getProperty(DATASOURCE_JNDINAME));
    // }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(DATASOURCE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(DATASOURCE_URL));
        dataSource.setUsername(env.getRequiredProperty(DATASOURCE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(DATASOURCE_PASSWORD));
        dataSource.setInitialSize(env.getRequiredProperty(DATASOURCE_INITIALSIZE, Integer.class));
        dataSource.setMaxActive(env.getRequiredProperty(DATASOURCE_MAXACTIVE, Integer.class));
        dataSource.setMaxIdle(env.getRequiredProperty(DATASOURCE_MAXIDLE, Integer.class));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "${package}.entities" });

        Properties props = new Properties();
        props.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty(org.hibernate.cfg.Environment.DIALECT));
        props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO));
        props.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty(org.hibernate.cfg.Environment.SHOW_SQL));
        props.put(org.hibernate.cfg.Environment.FORMAT_SQL, env.getProperty(org.hibernate.cfg.Environment.FORMAT_SQL));
        props.put(org.hibernate.cfg.Environment.USE_REFLECTION_OPTIMIZER, true);
        props.put(org.hibernate.cfg.Environment.CACHE_REGION_FACTORY, org.hibernate.cache.ehcache.EhCacheRegionFactory.class.getName());
        props.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, true);
        props.put(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE, true);

        sessionFactory.setHibernateProperties(props);
        sessionFactory.setNamingStrategy(new ImprovedNamingStrategy());

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
