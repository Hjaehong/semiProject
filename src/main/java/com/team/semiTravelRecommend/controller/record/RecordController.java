package com.team.semiTravelRecommend.controller.record;

import com.team.semiTravelRecommend.model.dto.record.CityDTO;
import com.team.semiTravelRecommend.model.dto.record.LocationDTO;
import com.team.semiTravelRecommend.model.dto.record.RecordDTO;
import com.team.semiTravelRecommend.model.dto.record.TagDTO;
import com.team.semiTravelRecommend.service.RecordService;
import net.bytebuddy.description.type.RecordComponentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
    * Version : 1.0
    * 클래스 : RecordController
    * 작성일자 : 2023/01/02
    * 작성자 : jihee
    * 설명 : 여행 기록물을 작성하고, 조회하는 컨트롤러
    * 수정일자 :
    * 수정자 :
    * 수정내역 :
*/
@Controller
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {

        this.recordService = recordService;
    }

    @GetMapping("RecordList")
    public ModelAndView recordList(ModelAndView mv){

        List<RecordDTO> recordList = recordService.recordList();

        mv.addObject("RecordList", recordList);
        mv.setViewName("record/RecordList");


        return mv;
    }

    @GetMapping("RecordDetail")
    public ModelAndView readRecordOne(ModelAndView mv){

        // Detail을 보고자하는 게시물의 no을 받아와야함

        RecordDTO record = recordService.readRecordOne();

        mv.addObject("RecordOne", record);
        mv.setViewName("record/RecordDetail");

        return mv;
    }

    @GetMapping(value="travelRecordWrite", produces = "application/json; charset=UTF-8")
//    @ResponseBody // return 값이 view페이지가 아닌 반환값 그대로 return 가능
    public ModelAndView readCityAndLocation(ModelAndView mv){

        List<LocationDTO> locationList = recordService.readLocation();

        List<TagDTO> tagList = recordService.readTag();

        mv.addObject("Location", locationList);
        mv.addObject("Tag", tagList);

        mv.setViewName("record/travelRecordWrite");

        return mv;
    }

//    @GetMapping(value="city", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<CityDTO> readCity(@PathVariable String locCode){
//
//        return recordService.readCity(locCode);
//
//    }

    @PostMapping("travelRecordWrite")
    public void writeRecord(RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file){

        if (!file.)

    }

}
