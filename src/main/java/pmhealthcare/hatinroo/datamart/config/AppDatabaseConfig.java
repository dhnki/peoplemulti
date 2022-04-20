package pmhealthcare.hatinroo.datamart.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

//@Configuration
//@EnableJpaRepositories
//        (
//                entityManagerFactoryRef = "appInEntityManagerFactory",
//                basePackages = "pmhealthcare.hatinroo.datamart.repository.app",
//                transactionManagerRef = "appInDB_transactionManager"
//        )
//@EnableTransactionManagement
public class AppDatabaseConfig {

//    @Bean(name = "appInDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.app")
//    public DataSource appInDataSource( ) throws SQLException {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "appInEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean appInEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("appInDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("pmhealthcare.hatinroo.datamart.model.app")
//                .persistenceUnit("app")
//                .build();
//    }
//
//    @Bean("appInDB_transactionManager")
//    public PlatformTransactionManager appPlatformTransactionManager(@Qualifier("appInEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }

}