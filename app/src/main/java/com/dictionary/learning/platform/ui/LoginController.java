package com.dictionary.learning.platform.ui;

import com.dictionary.learning.platform.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Invalid username or password");
        }

        return "pages/login";
    }

    /**
     * Handles the home page request.
     *
     * @param authentication the authentication object containing the user's details
     * @param request        the HTTP servlet request
     * @param model          the model to add attributes to
     * @return the name of the view to be rendered
     */
    @GetMapping("/")
    public String home(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        UserDetails details = ControllerUtils.addUserDetailsToModel(authentication, model);

        if (details != null) {
            boolean isAdmin = details.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch("ROLE_ADMIN"::equals);
            if (isAdmin) {
                int grade = userService.findGradeByUsername(details.getUsername());
                model.addAttribute("grade", grade);

                return "pages/home";
            }
        }

        return "redirect:/lesson-selection";
    }
}
