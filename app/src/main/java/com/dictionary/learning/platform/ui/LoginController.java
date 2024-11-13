package com.dictionary.learning.platform.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Invalid username or password");
        }

        return "pages/login";
    }

    @GetMapping("/")
    public String home(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            model.addAttribute("username", userDetails.getUsername());

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            if (authorities != null
                    && authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(r -> r.equals("admin"))) {
                return "pages/home";
            }
        }

        return "pages/learning";
    }
}
