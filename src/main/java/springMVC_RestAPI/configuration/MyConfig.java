package springMVC_RestAPI.configuration;


// можно конфигурировать с использование xml, как было в SpringMVC_aop

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "springMVC_RestAPI")
@EnableWebMvc // analog  <mvc:annotation-driven/>
@EnableTransactionManagement // analog     <tx:annotation-driven transaction-manager="transactionManager" />
public class MyConfig {

    @Bean
    public DataSource dataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();


        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");

        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        return  dataSource;
    };

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("springMVC_RestAPI.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");

        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return  localSessionFactoryBean;

    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }




}
