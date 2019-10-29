package cn.pasteme.admin.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Configuration
public class PasteMeAdminDataSourceConfiguration {

    @Bean(name = "mysql")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysql() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlite")
    @ConfigurationProperties(prefix = "spring.datasource.sqlite")
    public DataSource sqlite() {
        return DataSourceBuilder.create().build();
    }
}
