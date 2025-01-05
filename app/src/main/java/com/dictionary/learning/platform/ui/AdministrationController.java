package com.dictionary.learning.platform.ui;

import com.dictionary.learning.platform.lesson.LessonDto;
import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.user.UserDto;
import com.dictionary.learning.platform.user.UserService;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import com.dictionary.learning.platform.word.WordDto;
import com.dictionary.learning.platform.word.WordService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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
    private final LessonService lessonService;
    private final UserService userService;
    private String forUser;
    private long lessonId;

    public AdministrationController(WordService wordService, LessonService lessonService, UserService userService) {
        this.wordService = wordService;
        this.lessonService = lessonService;
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
    public String setUpUser(String forUser) {
        this.forUser = forUser;

        return "redirect:/manage-lessons?forUser=%s".formatted(forUser);
    }

    @GetMapping("/manage-lessons")
    public String manageLessons(
            @RequestParam(defaultValue = "0") int page,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        Page<LessonDto> lessons = lessonService.findAllByUserNamePaginated(page, forUser);
        DictionaryUtils.PageData<LessonDto> pageData = DictionaryUtils.providePageData(lessons);
        model.addAttribute("lessons", pageData.content());
        model.addAttribute("pageNumbers", pageData.pageNumbers());

        return "pages/lesson-administration";
    }

    @GetMapping("/manage-words")
    public String words(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam long lessonId,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        this.lessonId = lessonId;
        LessonDto lesson = lessonService.findByLessonId(lessonId);
        Page<WordDto> words = wordService.findAllLessonIdPaginated(page, lessonId);
        DictionaryUtils.PageData<WordDto> pageData = DictionaryUtils.providePageData(words);
        model.addAttribute("words", pageData.content());
        model.addAttribute("pageNumbers", pageData.pageNumbers());
        model.addAttribute("forUser", forUser);
        model.addAttribute("grade", lesson.grade());
        model.addAttribute("lesson", lesson.title());
        model.addAttribute("lessonId", lessonId);

        return "pages/words";
    }

    @PostMapping("/new-word")
    public String addNewWord(
            int grade,
            String lesson,
            String forUser,
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

    @GetMapping("/existing-word")
    public String updateExistingWord(
            @RequestParam long wordId, Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        WordDto wordDto = wordService.findByWordId(wordId);
        model.addAttribute("wordId", wordId);
        model.addAttribute("english", wordDto.en());
        model.addAttribute("slovak", wordDto.sk());

        LessonDto lessonDto = lessonService.findByLessonId(wordDto.lesson().getId());
        model.addAttribute("grade", lessonDto.grade());
        model.addAttribute("lesson", lessonDto.title());
        model.addAttribute("forUser", forUser);

        return "pages/word-updating";
    }

    @PostMapping("/add-word")
    public String addWord(
            String english, String slovak, Authentication authentication, HttpServletRequest request, Model model) {
        wordService.addWord(lessonId, english, slovak);

        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        return "redirect:/manage-words?lessonId=%s".formatted(lessonId);
    }

    @PostMapping("/update-word")
    public String updateWord(
            long wordId,
            String english,
            String slovak,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        wordService.updateWord(wordId, english, slovak);

        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        return "redirect:/manage-words?lessonId=%s".formatted(lessonId);
    }
}
