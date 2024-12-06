package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.ui.ControllerUtils;
import com.dictionary.learning.platform.user.UserDto;
import com.dictionary.learning.platform.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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
    private final UserService userService;

    public AdministrationController(WordService wordService, UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/administration")
    public String administration(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);

        List<String> users =
                userService.findAllUsers().stream().map(UserDto::username).toList();
        model.addAttribute("users", users);

        return "pages/administration";
    }

    @PostMapping("/set-up-lesson")
    public String setUpLesson(
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
