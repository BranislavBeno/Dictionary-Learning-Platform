package com.dictionary.learning.platform.ui;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LearningController {

    @GetMapping("/learning")
    public String learning(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);

        return "pages/learning";
    }

    @PostMapping("/choose-lesson")
    public String chooseLesson() {
        System.out.println();

        return "redirect:/home";
    }
}
