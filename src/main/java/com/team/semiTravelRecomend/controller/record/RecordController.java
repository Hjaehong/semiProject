package com.team.semiTravelRecomend.controller.record;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
    * Version : 1.0
   * 클래스명: RecordController
   * 작성일자 : 2022/12/27
 * 작성자 : heojaehong
   * 설명 : 여행기록 컨트롤러
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
@Controller
@RequestMapping("/record")
public class RecordController {

    @GetMapping(value = "/travelRecord")
    public void recordForm(){

    }

//    @PostMapping(value="/result")
//    public Me
}
