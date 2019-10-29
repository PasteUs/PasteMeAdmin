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
@MapperScan(basePackages = {"cn.pasteme.admin.mapper"}, sqlSessionFactoryRef = "sqliteSessionFactory", sqlSessionTemplateRef = "sqliteSessionTemplate")
public class PasteMeAdminMapperConfiguration {

    private final DataSource dataSource;

    public PasteMeAdminMapperConfiguration(@Qualifier("sqlite") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory sqliteSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqliteSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqliteSessionFactory());
    }
}
