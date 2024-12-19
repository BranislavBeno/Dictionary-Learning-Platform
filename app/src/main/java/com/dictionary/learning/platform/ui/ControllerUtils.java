package com.dictionary.learning.platform.ui;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;

public class ControllerUtils {

    private ControllerUtils() {}

    @Nullable
    public static UserDetails addUserDetailsToModel(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            model.addAttribute("username", username);

            return userDetails;
        }

        return null;
    }

    public static void addCsrfTokenToModel(HttpServletRequest request, Model model) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            model.addAttribute("csrf", csrf);
        }
    }
}
