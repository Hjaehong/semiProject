package com.team.semiTravelRecommend.controller.record;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.comment.CommentDTO;
import com.team.semiTravelRecommend.model.dto.record.*;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.paging.Pagenation;
import com.team.semiTravelRecommend.paging.SelectCriteria;
import com.team.semiTravelRecommend.service.CommentService;
import com.team.semiTravelRecommend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    // 댓글
    private final CommentService commentService;

    @Autowired
    public RecordController(RecordService recordService, CommentService commentService) {

        this.recordService = recordService;
        this.commentService = commentService;
    }



    @GetMapping("recordList")
    public Model recordList(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                            Model model, HttpServletRequest request){

        if (loginMember != null) { // 로그인 유무만 확인하면 되기 때문에 따로 userNo을 받지 않음
            model.addAttribute("loginMember", 1);
        } else { // 로그인 정보가 null인 경우
            model.addAttribute("loginMember", 0);
        }

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

//        System.out.println("recordList입니다 = " + recordList);

        model.addAttribute("selectCriteria", selectCriteria);
        model.addAttribute("RecordList", recordList);

        return model;
    }

    @GetMapping("recordDetail/{recordNo}")
    public ModelAndView recordOne(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                                  ModelAndView mv, @PathVariable("recordNo") int recordNo){

        RecordDTO record = recordService.recordOne(recordNo);

        // 게시글을 작성한 유저의 No
        int writerNo = record.getUserDTO().getUserNo();

        // 로그인 정보에 따라 userNo 값이 달라짐
        int userNo;


        if (loginMember != null) { // 로그인 정보가 null이 아닌경우 userNo을 받아옴
            userNo = loginMember.getUserNo().intValue();
            mv.addObject("loginMember", 1);
        } else { // 로그인 정보가 null인 경우
            userNo = 0;
            mv.addObject("loginMember", 0);
        }

        /* 좋아요 기능 구현을 위한 코드 */
        if (userNo == 0){ // 로그인 정보가 없는 경우
            mv.addObject("heartCheck", 3);
        } else if (writerNo != userNo) { // 로그인은 되어있지만, 작성자와 로그인한 유저가 다른 경우 (좋아요 기능 활성화)
            // 로그인한 유저가 해당 게시물에 좋아요를 눌렀는지 확인
            int heartCheck = recordService.heartCheck(recordNo, userNo);

            if (heartCheck == 1) { // 이미 눌려있다면 1을 반환
                mv.addObject("heartCheck", 1);
            } else { // 눌려있지 않다면 0을 반환
                mv.addObject("heartCheck", 0);
            }
        } else { // 작성자와 로그인한 유저가 같은 경우 (좋아요 기능 비활성화, 수정 삭제 버튼 활성화)
            mv.addObject("heartCheck", 2);
            /* 수정, 삭제를 위한 코드 */
            mv.addObject("samePerson", 0);
        }

        /* ajax로 좋아요 기능을 구현하는데 그때 userNo이 필요하기 때문에 add해줌 */
        mv.addObject("userNo", userNo);
        mv.addObject("RecordOne", record);
        mv.setViewName("record/recordDetail");

        return mv;
    }

    @RequestMapping(value="clickHeart", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int clickHeart(int recordNo, int userNo /*, int heartCheck */){

        int heartCheck = recordService.heartCheck(recordNo, userNo);

        if (heartCheck == 1) { // 이미 하트가 눌려있는 상태이기 때문에 delete 실행
            // 정상작동하면 1을 반환
            return recordService.deleteHeart(recordNo, userNo);
        } else { // 하트가 눌려있지 않기 때문에 insert 실행
            // 정상작동하면 2를 반환
            return recordService.insertHeart(recordNo, userNo);
        }

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
        return recordService.readLocation();
    }

    public List<TagDTO> readTag(){
        return recordService.readTag();
    }

    @RequestMapping(value="city", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CityDTO> readCity(String locCode){

        return recordService.readCity(locCode);
    }

    @PostMapping("travelRecordWrite")
    public ModelAndView writeRecord(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginUserResponse loginMember,
                                    ModelAndView mv, RecordDTO record, @RequestParam(name="file", required = false) MultipartFile file, RedirectAttributes rttr){

        // 파일에 대한 처리를 하는 메소드 호출 후 fileNo을 DTO에 set해줌
        if ((!file.getOriginalFilename().equals(""))){
            int fileNo = saveFile(file);

            record.setImgFileNo(fileNo);
        }

        // session 에서 로그인한 user의 정보를 찾아서 DTO에 set해줌
        int userNo = loginMember.getUserNo().intValue();
        record.setUserNo(userNo);

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

        System.out.println(file.isEmpty());
        System.out.println(file.getOriginalFilename());

        try {
            file.transferTo(saveFile);

            FileDTO imgFile = new FileDTO();
            imgFile.setFileSize(file.getSize());
            imgFile.setOriginName(file.getOriginalFilename());
            imgFile.setChangeName(changeName);
            imgFile.setImgPath("/uploadImgs/" +changeName);

            recordService.saveFile(imgFile);

            System.out.println("에러 지점 확인용 출력");

            return recordService.returnFileNo(changeName);

        } catch (Exception e) {
            System.out.println("Exception" + e);
            throw new RuntimeException(e);
        }

    }


    @PostMapping("editRecord")
    public ModelAndView readRecord(ModelAndView mv, int recordNo){

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
    // 댓글입력
    @ResponseBody
    @RequestMapping("insertComment")
    public String insertComment(CommentDTO comment){
        System.out.println(comment);
        int result = commentService.registComment(comment);
        System.out.println("result = " + result);
        return String.valueOf(result);
    }
    //댓글 리스트 출력
    @ResponseBody
    @RequestMapping(value = "listComment", produces = "application/json; charset=utf-8")
    public List<CommentDTO> listComment(int recordNo) {
        System.out.println("여기까지 넘어오나? = " + recordNo);
        return commentService.showComment(recordNo);
    }


}
