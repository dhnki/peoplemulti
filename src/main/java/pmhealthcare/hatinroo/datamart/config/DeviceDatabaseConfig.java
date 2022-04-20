package pmhealthcare.hatinroo.datamart.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
//                entityManagerFactoryRef = "deviceInEntityManagerFactory",
//                basePackages = "pmhealthcare.hatinroo.datamart.repository.device",
//                transactionManagerRef = "deviceInDB_transactionManager"
//        )
//@EnableTransactionManagement
public class DeviceDatabaseConfig {

//    @Primary
//    @Bean(name = "deviceInDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.device")
//    public DataSource deviceInDataSource( ) throws SQLException {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//
//    @Primary
//    @Bean(name = "deviceInEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean deviceInEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("deviceInDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("pmhealthcare.hatinroo.datamart.model.device")
//                .persistenceUnit("device")
//                .build();
//    }
//
//
//    @Primary
//    @Bean("deviceInDB_transactionManager")
//    public PlatformTransactionManager devicePlatformTransactionManager(@Qualifier("deviceInEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }

}