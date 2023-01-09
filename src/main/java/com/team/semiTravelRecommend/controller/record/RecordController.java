package com.team.semiTravelRecommend.controller.record;

import com.team.semiTravelRecommend.model.dto.record.*;
import com.team.semiTravelRecommend.paging.Pagenation;
import com.team.semiTravelRecommend.paging.SelectCriteria;
import com.team.semiTravelRecommend.service.RecordService;
import net.bytebuddy.description.type.RecordComponentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public Model recordList(Model model, HttpServletRequest request){

        String currentPage = request.getParameter("currentPage");

        int pageNo = 1;

        if(currentPage != null && !"".equals(currentPage)){
            pageNo = Integer.parseInt(currentPage);
        }

        if(pageNo <=0){
            pageNo = 1;
        }

        int totalCount = recordService.findAllCnt();
        int limit = 8;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        List<RecordDTO> recordList = recordService.recordListPaging(selectCriteria);

        model.addAttribute("selectCriteria", selectCriteria);
        model.addAttribute("RecordList", recordList);

        return model;
    }

    @GetMapping("recordDetail/{recordNo}")
    public ModelAndView recordOne(ModelAndView mv, @PathVariable("recordNo") int recordNo){

        RecordDTO record = recordService.recordOne(recordNo);

        mv.addObject("RecordOne", record);
        mv.setViewName("record/recordDetail");

        return mv;
    }

    @GetMapping(value="travelRecordWrite", produces = "application/json; charset=UTF-8")
    public ModelAndView readTagAndLocation(ModelAndView mv){

        List<LocationDTO> locationList = readLocation();

        List<TagDTO> tagList = readTag();

        mv.addObject("Location", locationList);
        mv.addObject("Tag", tagList);

        mv.setViewName("record/travelRecordWrite");

        return mv;

    }

    public List<LocationDTO> readLocation(){
        List<LocationDTO> locationList = recordService.readLocation();

        return locationList;
    }

    public List<TagDTO> readTag(){
        List<TagDTO> tagList = recordService.readTag();

        return tagList;
    }

    @RequestMapping(value="city", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CityDTO> readCity(String locCode){

        return recordService.readCity(locCode);
    }

    @PostMapping("travelRecordWrite")
    public ModelAndView writeRecord(ModelAndView mv, RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file, RedirectAttributes rttr){

        System.out.println(record.getCityCode());

        if ((!file.getOriginalFilename().equals(""))){
            int fileNo = saveFile(file);

            record.setImgFileNo(fileNo);
        }

        recordService.insertRecord(record);

        mv.setViewName("redirect:/record/recordList");
        rttr.addFlashAttribute("successMessage", "작성 완료!");

        return mv;

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

            System.out.println("에러 지점 확인용 출력");
            int fileNo = recordService.returnFileNo(changeName);

            return fileNo;

        } catch (Exception e) {
            System.out.println("Exception" + e);
            throw new RuntimeException(e);
        }

    }


    @PostMapping("editRecord")
    public ModelAndView readRecord(ModelAndView mv, int recordNo, @RequestParam(name="file", required = false) MultipartFile file){

        RecordDTO record = recordService.recordOne(recordNo);

        List<LocationDTO> locationList = readLocation();

        List<TagDTO> tagList = readTag();

        mv.addObject("Location", locationList);
        mv.addObject("Tag", tagList);
        mv.addObject("RecordOne", record);
        mv.setViewName("record/travelRecordEdit");

        return mv;
    }


    @PostMapping("travelRecordEdit")
    public ModelAndView editRecord(ModelAndView mv, RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file
                                    ,RedirectAttributes rttr) throws Exception {
        /*
        * 여행기록 게시물을 작성할 때 이미지 파일을 필수적으로 올려야하기 때문에 아래 두개의 경우만 고려함
        * 1. 기존의 첨부파일 O, 새로 첨부된 파일 X
		* 	  --> originName : 기존첨부파일원본명, changeName : 기존첨부파일수정명
		*
		* 2. 기존의 첨부파일 O, 새로 첨부된 파일 O
		* 	  --> 서버에 업로드 후
		* 	  --> originName : 새로첨부된파일원본명, changeName : 새로첨부된파일수정명
        */

        System.out.println("값이 잘 넘어오는지 확인 : " + record.getRecordNo());
        System.out.println("첨부파일 정보 확인 : " + record.getImgFileNo());
        System.out.println("원본 첨부파일 정보 확인 : " + record.getFileDTO().getChangeName());

        String orgChangeName = record.getFileDTO().getChangeName();

        // 첨부파일이 수정된 경우 아래 코드 실행 ( 새로운 파일을 저장하고 fileNo을 DTO에 set해준 뒤, 기존의 파일을 삭제)
        if(!file.getOriginalFilename().equals("")){
            int fileNo = saveFile(file);
            record.setImgFileNo(fileNo);

            // 기존파일 삭제 메소드 호출
            deleteFile(orgChangeName);
        }

        recordService.editRecord(record);

        mv.addObject("recordNo", record.getRecordNo());
        mv.setViewName("redirect:/record/recordDetail/{recordNo}");
        rttr.addFlashAttribute("successMessage", "수정 완료!");

        return mv;
    }

    private void deleteFile(String orgChangeName) throws Exception {

        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/uploadImgs";

        File deleteFile = new File(projectPath + orgChangeName);

        deleteFile.delete();

        int result = recordService.deleteImgFile(orgChangeName);
        System.out.println(result);
    }

    @PostMapping("deleteRecord")
    public ModelAndView deleteRecord(ModelAndView mv, int recordNo, RedirectAttributes rttr){

        recordService.deleteRecord(recordNo);

        mv.setViewName("redirect:/record/recordList");
        rttr.addFlashAttribute("successMessage", "삭제 완료!");

        return  mv;

    }


}
