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

    @GetMapping("RecordDetail/{recordNo}")
    public ModelAndView readRecordOne(ModelAndView mv, @PathVariable("recordNo") int recordNo){

        RecordDTO record = recordService.readRecordOne(recordNo);

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
    public void writeRecord(RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file, HttpServletRequest request){

        if (!file.getOriginalFilename().equals("")){
            int fileNo = saveFile(file, request);
        }

    }

    private int saveFile(MultipartFile file, HttpServletRequest request){

        String resources = request.getSession().getServletContext().getRealPath("resources");
        String savePath = resources + "/upload_file";

        String originName = file.getOriginalFilename();
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String onlyName = originName.substring(originName.lastIndexOf("."));

        String changeName = currentTime + onlyName;

        try {
            file.transferTo(new File(savePath+changeName));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        FileDTO uploadFile = new FileDTO();
        uploadFile.setFileSize(file.getSize());
        uploadFile.setChangeName(changeName);
        uploadFile.setOriginName(originName);
        uploadFile.setImgPath(savePath+changeName);

//        recordService.uploadFile(uploadFile);


        return 1;
    }

}
