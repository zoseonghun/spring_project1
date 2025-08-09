package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.security.core.Authentication;

@Controller
// @Component 스프링빈으로 등록하기위한 라벨링 작업
public class PageController {

    @Autowired
    private UserService userService;

    // / -> localhost:8080
    // 페이지를 조회 및 이동할때 @GetMapping()을 써서 이동합니다.
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // / -> localhost:8080/register
    @GetMapping("/registerPage")
    public String registerPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        return "register/index";
    }

    @GetMapping("/loginPage")
    public String loginPage(HttpServletRequest request, Model model) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        return "login/index";
    }

    @GetMapping("/noticeAddPage")
    public String noticeAddPage () {
        return "noticeAdd/index";
    }
}
