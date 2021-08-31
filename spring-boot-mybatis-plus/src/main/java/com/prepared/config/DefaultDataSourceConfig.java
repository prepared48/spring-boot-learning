//package com.prepared.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author chenwei
// * @date 2020-03-06 17:40
// */
//@Configuration
//@MapperScan(basePackages = DefaultDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "defaultSqlSessionFactory")
//public class DefaultDataSourceConfig {
//
//    // 精确到 default 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.prepared.mapper";
//    static final String MAPPER_LOCATION = "classpath*:com.prepared.mapper/*Mapper.xml";
//
//    /**
//     * 默认数据源
//     * 所有数据源中智能有一个是primary数据源
//     *
//     * @return
//     */
//    @Bean(name = "defaultDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "datasource.default")
//    public DataSource defaultDataSource() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 事务管理器
//     *
//     * @return
//     */
//    @Bean(name = "defaultTransactionManager")
//    @Primary
//    public DataSourceTransactionManager defaultTransactionManager() {
//        return new DataSourceTransactionManager(defaultDataSource());
//    }
//
//    /**
//     * 会话工厂
//     *
//     * @param defaultDataSource
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "defaultSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("defaultDataSource") DataSource defaultDataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(defaultDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DefaultDataSourceConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }
//}