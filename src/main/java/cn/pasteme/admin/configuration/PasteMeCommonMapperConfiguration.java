package cn.pasteme.admin.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Configuration
@MapperScan(basePackages = {"cn.pasteme.common.mapper"}, sqlSessionFactoryRef = "mysqlSessionFactory", sqlSessionTemplateRef = "mysqlSessionTemplate")
public class PasteMeCommonMapperConfiguration {

    private final DataSource dataSource;

    public PasteMeCommonMapperConfiguration(@Qualifier("mysql") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory mysqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate mysqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSessionFactory());
    }
}
