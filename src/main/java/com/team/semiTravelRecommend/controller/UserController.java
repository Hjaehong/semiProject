package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.UserResponse;
import com.team.semiTravelRecommend.model.dto.UserVO;
import com.team.semiTravelRecommend.model.dto.requset.DeleteUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("NATIONAL PARK", "국공립공원");
        regions.put("MOUNTAIN", "산");
        regions.put("FOREST", "휴양림");
        regions.put("WATERFALL/VALLEY", "폭포/계곡");
        regions.put("SEA", "바다");
        regions.put("ISLAND", "섬");
        regions.put("RIVER/LAKE", "강/호수");
        regions.put("OLD PALACE", "고궁");
        regions.put("HISTORIC SITES", "유적지");
        regions.put("AMUSEMENT PARK/THEME PARK ", "유원지/테마공원");
        regions.put("SPA", "온천/스파");
        regions.put("UNIQUE EXPERIENCE", "이색체험");
        regions.put("MONUMENT/OBSERVATORY", "기념비/전망대");
        regions.put("FAMOUS BUILDING", "유명건물");
        regions.put("MUSEUM", "박물관");
        regions.put("ART GALLERY/EXHIBITION ", "미술관/전시관");
        regions.put("BOOK TOUR", "북투어");
        regions.put("BIKE", "자전거");
        regions.put("GOLF", "골프");
        regions.put("CAMPING", "캠핑");
        regions.put("TRACKING", "트래킹");
        regions.put("WATER SPORTS", "수상레포츠");
        regions.put("FISHING", "낚시");
        regions.put("AIR LEISURE SPORTS", "항공레포츠");
        regions.put("LOCAL MARKET", "지역시장");
        regions.put("CRAFTS/WORKSHOP", "공예/공방");
        regions.put("EXOTIC FOOD", "이색음식");
        regions.put("VEGETARIAN", "채식");
        regions.put("GUESTHOUSE", "게스트하우스");
        return regions;
    }

    /*
        회원 가입
     */
    @GetMapping("/user-signup")
    public String signUpForm(@ModelAttribute("saveUserRequest") SaveUserRequest saveUserRequest) {
        return "user/signup";
    }

    @PostMapping("/user-signup")
    public String signUp(@ModelAttribute("saveUserRequest") @Valid SaveUserRequest saveUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/signup";
        }
        log.info("홈 화면 데이터 = {}", saveUserRequest);
        userService.save(saveUserRequest);

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

        UserResponse userResponse = new UserResponse(loginUser);




        log.info("로그인 성공 ! = {}", loginUser);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, userResponse);

        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
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
