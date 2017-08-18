package com.xiangxun.epms.mobile.config.mybatis;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageHelper;

@Configuration  
@MapperScan(basePackages = "com.xiangxun.epms.mobile.business.mapdao", sqlSessionTemplateRef  = "mapSqlSessionTemplate")
public class MapMyBatisConfig {

	@Resource(name = "mapDataSource")
    DataSource mapDataSource;

    @Bean(name = "mapSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mapDataSource);
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        // 添加插件
        bean.setPlugins(new Interceptor[] { pageHelper });
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/xiangxun/epms/mobile/business/mapdao/*.xml"));
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "mapSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mapSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
}
