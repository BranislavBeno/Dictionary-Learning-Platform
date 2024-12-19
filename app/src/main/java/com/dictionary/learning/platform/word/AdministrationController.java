package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.ui.ControllerUtils;
import com.dictionary.learning.platform.user.User;
import com.dictionary.learning.platform.user.UserDto;
import com.dictionary.learning.platform.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdministrationController {

    private final WordService wordService;
    private final UserService userService;

    public AdministrationController(WordService wordService, UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/administration")
    public String userAdministration(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        List<String> users =
                userService.findAllUsers().stream().map(UserDto::username).toList();
        model.addAttribute("users", users);

        return "pages/user-administration";
    }

    @PostMapping("/set-up-user")
    public String setUpUser(String forUser, Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        int grade = userService.findGradeByUsername(forUser);
        model.addAttribute("grade", grade);
        model.addAttribute("forUser", forUser);

        return "pages/lesson-administration";
    }

    @PostMapping("/set-up-lesson")
    public String setUpLesson(
            int grade,
            int lesson,
            String forUser,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        return "redirect:/manage-words?grade=%d&lesson=%d&forUser=%s".formatted(grade, lesson, forUser);
    }

    @GetMapping("/manage-words")
    public String words(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam int grade,
            @RequestParam int lesson,
            @RequestParam String forUser,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        Page<WordDto> words = wordService.findAllByUserNameByGradeByLessonPaginated(page, forUser, grade, lesson);
        PageData pageData = providePageData(words);
        model.addAttribute("words", pageData.content());
        model.addAttribute("pageNumbers", pageData.pageNumbers());
        model.addAttribute("forUser", forUser);
        model.addAttribute("grade", grade);
        model.addAttribute("lesson", lesson);

        return "pages/words";
    }

    @GetMapping("/new-word")
    public String newWord(
            @RequestParam int grade,
            @RequestParam int lesson,
            @RequestParam String forUser,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);
        model.addAttribute("forUser", forUser);
        model.addAttribute("grade", grade);
        model.addAttribute("lesson", lesson);

        return "pages/word-adding";
    }

    @PostMapping("/add-word")
    public String addWord(
            int grade,
            int lesson,
            String forUser,
            String english,
            String slovak,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        Word word = createWord(grade, lesson, english, slovak);
        addWord(forUser, word);

        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        return "redirect:/manage-words?grade=%d&lesson=%d&forUser=%s".formatted(grade, lesson, forUser);
    }

    private void addWord(String forUser, Word word) {
        User user = userService.findByUsername(forUser);
        word.setUser(user);
        wordService.saveWord(word);
    }

    private static Word createWord(int grade, int lesson, String english, String slovak) {
        Word word = new Word();
        word.setEn(english);
        word.setSk(slovak);
        word.setGrade(grade);
        word.setLesson(lesson);

        return word;
    }

    private List<Integer> providePageNumbers(int totalPages) {
        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages).boxed().toList();
        }

        return Collections.emptyList();
    }

    private PageData providePageData(Page<WordDto> page) {
        List<Integer> pageNumbers = providePageNumbers(page.getTotalPages());

        return new PageData(page.getContent(), pageNumbers);
    }

    record PageData(List<WordDto> content, List<Integer> pageNumbers) {}
}
