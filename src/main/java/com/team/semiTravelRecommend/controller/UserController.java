package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.UserResponse;
import com.team.semiTravelRecommend.model.dto.requset.DeleteUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("T1", "국공립공원");
        regions.put("T2", "산");
        regions.put("T3", "휴양림");
        regions.put("T4", "폭포/계곡");
        regions.put("T5", "바다");
        regions.put("T6", "섬");
        regions.put("T7", "강/호수");
        regions.put("T8", "고궁");
        regions.put("T9", "유적지");
        regions.put("T10", "유원지/테마공원");
        regions.put("T11", "온천/스파");
        regions.put("T12", "이색체험");
        regions.put("T13", "기념비/전망대");
        regions.put("T14", "유명건물");
        regions.put("T15", "박물관");
        regions.put("T16", "미술관/전시관");
        regions.put("T17", "북투어");
        regions.put("T18", "자전거");
        regions.put("T19", "골프");
        regions.put("T20", "캠핑");
        regions.put("T21", "트래킹");
        regions.put("T22", "수상레포츠");
        regions.put("T23", "낚시");
        regions.put("T24", "항공레포츠");
        regions.put("T25", "지역시장");
        regions.put("T26", "공예/공방");
        regions.put("T27", "이색음식");
        regions.put("T28", "채식");
        regions.put("T29", "게스트하우스");
        return regions;
    }



    /*
        회원 가입
     */
    @GetMapping("/user-signup")
    public String signUpForm(@ModelAttribute("saveUserRequest") SaveUserRequest saveUserRequest, Model model) {
        return "user/signup";
    }

    @PostMapping("/user-signup")
    public String signUp(@ModelAttribute("saveUserRequest") @Valid SaveUserRequest saveUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/signup";
        }
        log.info("홈 화면 데이터 = {}", saveUserRequest);
        userService.save(saveUserRequest); //User 테이블에 user 정보를 저장
        log.info("saveUserRequest.regions={}", saveUserRequest.getRegions());

        List<String> userChoiceTag = saveUserRequest.getRegions();
        System.out.println(userChoiceTag);

        int userNo = userService.findUserNo(saveUserRequest.getUserId());

        HashMap<String, Object> userTag = new HashMap<>();
        userTag.put("userNo", userNo);

        for (int i = 0; i < userChoiceTag.size(); i++){
            userTag.put("tagCode", userChoiceTag.get(i));
            userService.insertUserTag(userTag);
        }

        if (saveUserRequest == null) {
            bindingResult.reject("loginFail", "회원가입할 정보를 입력해주세요.");
            return "user/signup";
        }
        log.info("save 메서드 종료");
        return "redirect:/";
    }

    /*
        로그인
     */
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginUserRequest loginUserRequest) {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") @Valid LoginUserRequest loginUserRequest, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/loginForm";
        }
        LoginUserResponse loginUser = userService.login(loginUserRequest);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 확인해주세요");
            return "user/loginForm";
        }

//        UserResponse userResponse = new UserResponse(loginUser);




        log.info("로그인 성공 ! = {}", loginUser);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, RedirectAttributes rttr) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        rttr.addFlashAttribute("successMessage", "로그아웃이 완료되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(HttpServletRequest request, UpdateUserRequest updateUserRequest, Model model) {
        HttpSession session = request.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);
        UserResponse user = userService.getUser(attribute.getUserId());

        log.info("user = {}", user);

        model.addAttribute("updateUserRequest", user);

        return "user/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Valid UpdateUserRequest updateUserRequest,
                         BindingResult bindingResult,
                         HttpServletRequest servletRequest) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/update";
        }

        log.info(">>>>>>>>>>> request = {}", updateUserRequest);

        HttpSession session = servletRequest.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);

        log.info("before session={}", attribute);

        userService.update(updateUserRequest, attribute );

        UserResponse user = userService.getUser(updateUserRequest.getUserId());
        session.setAttribute(SessionConst.LOGIN_USER, user);

        UserResponse updatedSession = (UserResponse)session.getAttribute(SessionConst.LOGIN_USER);
        log.info("after session={}", updatedSession);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteForm(HttpServletRequest request, DeleteUserRequest deleteUserRequest, Model model) {
        HttpSession session = request.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);
        UserResponse user = userService.getUser(attribute.getUserId());

        log.info("user = {}", user);

        model.addAttribute("loginMember", attribute.getUserId());

        return "user/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute @Valid DeleteUserRequest deleteUserRequest,
                         BindingResult bindingResult,
                         HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/delete";
        }

        log.info(">>>>>>>>>>> request = {}", deleteUserRequest.getEmail());//여기 왜 안담길까

        HttpSession session = request.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);

        log.info("before session={}", attribute);

        userService.delete(attribute);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
