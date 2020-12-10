package com.bkf.busi.flowmanage.util.springboot;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 11月30号添加，解决在application.yml中配置mybatis.mapperLocations完全不起作用的问题
 * 下一步，准备把所有的项目都这样配置，确保稳定性
 * @author zhoudong
 */
@Configuration
@MapperScan(basePackages = {"com.bkf.busi.*.mapper"})
public class MyBatisConfig {

    /**
     * PageHelper 初始化设置
     * @return
     * @author wangxuefei
     * @date 2019/7/24 11:03
     */
    @Bean
    public PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

/*
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //就是这句代码，只能指定单个mapper.xml文件，加通配符的话找不到文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:sqlmapper/*.xml"));
        // 配置多个路径使用：sqlSessionFactoryBean.setMapperLocations(new Resource[]{resource1, resource2});
        return sqlSessionFactoryBean.getObject();
    }*/

}
