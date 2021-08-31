package com.prepared.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@AutoConfigureAfter(SqlSessionFactory.class)
@MapperScan(basePackages = MybatisPlusConfig.mapperLocations, sqlSessionFactoryRef = "MySqlSessionFactory")
public class MybatisPlusConfig {

	private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

	final static String mapperLocations = "classpath:/mapper/*Mapper.xml";

	static Boolean refreshMapper = true;

	/**
	 * 数据库驱动
	 */
	@Value("${spring.datasource.driverClassName}")
	String driverClassName;

	@Autowired
	private PaginationInterceptor paginationInterceptor;

	/*
	 * 分页插件，自动识别数据库类型
	 * 多租户，请参考官网【插件扩展】
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}


	/**
	 * oracle数据库配置JdbcTypeForNull
	 * 参考：https://gitee.com/baomidou/mybatisplus-boot-starter/issues/IHS8X
	 * 不需要这样配置了，参考 yml:
	 * mybatis-plus:
	 * confuguration
	 * dbc-type-for-null: 'null'
	 *
	 * @return
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new MybatisPlusCustomizers();
	}

	/**
	 * mapper.xml 热加载
	 *
	 * @return
	 */
	@Bean
	public MybatisMapperRefresh mybatisMapperRefresh() {
		SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean("MySqlSessionFactory");
		Resource[] resources = new Resource[0];
		try {
			resources = resourceResolver.getResources(mapperLocations);
		} catch (IOException e) {
			e.printStackTrace();
		}
		MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(resources, sqlSessionFactory, 10, 5, refreshMapper);
		return mybatisMapperRefresh;

	}

	/**
	 * 逻辑删除
	 *
	 * @return
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	class MybatisPlusCustomizers implements ConfigurationCustomizer {

		@Override
		public void customize(MybatisConfiguration configuration) {
			configuration.setJdbcTypeForNull(JdbcType.NULL);
		}
	}

	/**
	 * 默认数据源
	 * 所有数据源中只能有一个是primary数据源
	 *
	 * @return
	 */
	@Bean(name = "applicationDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource defaultDataSource() {
		return new DruidDataSource();
	}


	/**
	 * 会话工厂
	 *
	 * @param defaultDataSource 数据源
	 * @return SqlSessionFactory
	 * @throws Exception
	 */
	@Bean(name = "MySqlSessionFactory")
	@Primary
	public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("applicationDataSource") DataSource defaultDataSource) throws Exception {
		final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(defaultDataSource);
		//        关键代码 设置 MyBatis-Plus 分页插件
		Interceptor[] plugins = {paginationInterceptor};
		sessionFactory.setPlugins(plugins);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MybatisPlusConfig.mapperLocations));
		MybatisPlusProperties properties = new MybatisPlusProperties();
		if(driverClassName.contains("oracle")){
			properties.getGlobalConfig().getDbConfig().setKeyGenerator(new OracleKeyGenerator());
			properties.getGlobalConfig().getDbConfig().setIdType(IdType.INPUT);
		}else{
			properties.getGlobalConfig().getDbConfig().setIdType(IdType.AUTO);
		}
		sessionFactory.setGlobalConfig(properties.getGlobalConfig());
		return sessionFactory.getObject();
	}
}