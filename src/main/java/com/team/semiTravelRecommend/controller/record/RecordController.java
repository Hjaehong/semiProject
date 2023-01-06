package com.team.semiTravelRecommend.controller.record;

import com.team.semiTravelRecommend.model.dto.record.*;
import com.team.semiTravelRecommend.service.RecordService;
import net.bytebuddy.description.type.RecordComponentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("recordList")
    public ModelAndView recordList(ModelAndView mv){

        List<RecordDTO> recordList = recordService.recordList();

        mv.addObject("RecordList", recordList);
        mv.setViewName("record/recordList");


        return mv;
    }

    @GetMapping("recordDetail/{recordNo}")
    public ModelAndView readRecordOne(ModelAndView mv, @PathVariable("recordNo") int recordNo){

        RecordDTO record = recordService.readRecordOne(recordNo);

        mv.addObject("RecordOne", record);
        mv.setViewName("record/recordDetail");

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
    public void writeRecord(RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file, HttpServletRequest request){

//        System.out.println(record.getRecordTag());
//        System.out.println(record.getRcTitle());
//        System.out.println(record.getRcDetail());
//        System.out.println(record.getTravelEndDate());
//        System.out.println(record.getTravelStartDate());

        if ((!file.getOriginalFilename().equals(""))){
            int fileNo = saveFile(file);

            record.setImgFileNo(fileNo);
            record.setCityCode("C24");
        }

        int result = recordService.insertRecord(record);

    }

    private int saveFile(MultipartFile file){

        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/uploadImgs";
        UUID uuid = UUID.randomUUID();
        String changeName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath, changeName);

        try {
            file.transferTo(saveFile);

            FileDTO imgFile = new FileDTO();
            imgFile.setFileSize(file.getSize());
            imgFile.setOriginName(file.getOriginalFilename());
            imgFile.setChangeName(changeName);
            imgFile.setImgPath("/uploadImgs/"+changeName);

            recordService.saveFile(imgFile);

            System.out.println("이미지저장성공");

            int fileNo = recordService.returnFileNo(changeName);
            System.out.println(fileNo);

            return fileNo;

        } catch (Exception e) {

            return 1;
        }


    }

}
