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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
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

        UserResponse userResponse = new UserResponse(loginUser);


        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/loginForm";
        }

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
            return "user/signup";
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

        log.info(">>>>>>>>>>> request = {}", deleteUserRequest);//여기 왜 안담길까

        HttpSession session = request.getSession();
        UserResponse attribute = (UserResponse) session.getAttribute(SessionConst.LOGIN_USER);

        log.info("before session={}", attribute);

        userService.delete(attribute);


        return "redirect:/";
    }
}
