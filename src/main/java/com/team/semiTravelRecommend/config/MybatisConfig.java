package com.team.semiTravelRecommend.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
    * Version : 1.0
   * 클래스명: MybatisConfig
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 마이바티스 설정 파일
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.team.semiTravelRecommend" , annotationClass = Mapper.class)
public class MybatisConfig {

}
