package com.team.semiTravelRecommend.controller;

import com.team.semiTravelRecommend.model.dto.SessionConst;
import com.team.semiTravelRecommend.model.dto.requset.LoginUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.SaveUserRequest;
import com.team.semiTravelRecommend.model.dto.requset.UpdateUserRequest;
import com.team.semiTravelRecommend.model.dto.response.LoginUserResponse;
import com.team.semiTravelRecommend.model.dto.response.UpdateUserResponse;
import com.team.semiTravelRecommend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
//@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
        회원 가입
     */
    @GetMapping("/user-signup")
    public String signUpForm(@ModelAttribute("saveUserRequest") SaveUserRequest saveUserRequest) {
        return "user/signup";
    }

    @PostMapping("/user-signup")
    public String signUp(@ModelAttribute @Valid SaveUserRequest saveUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/signup";
        }
        log.info("홈 화면 데이터 = {}", saveUserRequest);
        userService.save(saveUserRequest);
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
    public String login(@Valid @ModelAttribute LoginUserRequest loginUserRequest, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getFieldError().getDefaultMessage());
            return "user/loginForm";
        }
        LoginUserResponse loginUser = userService.login(loginUserRequest);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/loginForm";
        }

        log.info("로그인 성공 ! = {}", loginUser);

        // 세션에 로그인 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

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
    public String updateForm() {
        return "user/update";
    }

    @PostMapping("/update")
    public String update(Long userNo, UpdateUserRequest updateUserRequest) {
        userService.update(userNo, updateUserRequest);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteForm() {
        return "user/delete";
    }

    @PostMapping("/delete")
    public String delete(Long userNo) {
        userService.delete(userNo);
        return "redirect:/";
    }
}
