package com.liyuan.demo.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 12:02 2018/2/8
 * @Modified By:
 */

@Configuration
public class SessionFactoryConfiguration {
    @Value("${mybatis_config_file}")//mybatis-config配置文件路径
    private String mybatisConfigPath;
    @Value("${mapper_path}")//mybatis mapper文件所在路径
    private String mapperPath;
    @Value("${entity_package}")//实体类所在package
    private String entityPackage;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //[1]设置mybatis全局配置文件路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigPath));

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;

        //[2]设置mapper扫描包路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packSearchPath));

        //[3]设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);

        //[补充]无法实现批量扫描的bug
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        //[4]设置实体类所在包路径
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}
