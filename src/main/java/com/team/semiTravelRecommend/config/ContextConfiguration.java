package com.team.semiTravelRecommend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

 /**
    * Version : 1.0
   * 클래스명: ContextConfiguration
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 설정파일, 컴포넌트를 스캔할 패키지 지정
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Configuration
@ComponentScan(basePackages = {"com.team.semiTravelRecommend"})
public class ContextConfiguration {

}
