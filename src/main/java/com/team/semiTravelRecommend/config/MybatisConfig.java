package com.team.semiTravelRecommend.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@MapperScan(basePackages = {"com.team.semitravelrecomand"}, annotationClass = Mapper.class)
public class MybatisConfig {

}
