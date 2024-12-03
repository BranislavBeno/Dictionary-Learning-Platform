package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.ui.ControllerUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministrationController {

    private final WordService wordService;

    public AdministrationController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/administration")
    public String administration(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);

        return "pages/administration";
    }

    @PostMapping("/set-up-lesson")
    public String chooseLesson(
            int grade,
            int lesson,
            String forUser,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            ControllerUtils.addCsrfTokenToModel(request, model);
            model.addAttribute("username", username);

            Set<WordDto> words = wordService.findAllByUserNameByGradeByLesson(forUser, grade, lesson);
            model.addAttribute("words", words);
        }

        return "redirect:/";
    }
}
