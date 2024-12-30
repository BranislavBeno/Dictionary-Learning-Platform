package com.dictionary.learning.platform.ui;

import com.dictionary.learning.platform.lesson.LessonDto;
import com.dictionary.learning.platform.lesson.LessonService;
import com.dictionary.learning.platform.utils.DictionaryUtils;
import com.dictionary.learning.platform.word.WordDto;
import com.dictionary.learning.platform.word.WordService;
import com.dictionary.learning.platform.word.WordToCheck;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LearningController {

    private final LessonService lessonService;
    private final WordService wordService;
    private Iterator<WordToCheck> iterator;
    private WordToCheck wordToCheck;

    public LearningController(LessonService lessonService, WordService wordService) {
        this.lessonService = lessonService;
        this.wordService = wordService;
    }

    @GetMapping("/lesson-selection")
    public String selectLesson(
            @RequestParam(defaultValue = "0") int page,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        UserDetails details = ControllerUtils.addUserDetailsToModel(authentication, model);

        if (details != null) {
            Page<LessonDto> lessons = lessonService.findAllByUserNamePaginated(page, details.getUsername());
            DictionaryUtils.PageData<LessonDto> pageData = DictionaryUtils.providePageData(lessons);
            model.addAttribute("lessons", pageData.content());
            model.addAttribute("pageNumbers", pageData.pageNumbers());

            return "pages/lesson-selection";
        }

        return "redirect:/";
    }

    @GetMapping("/choose-lesson")
    public String chooseLesson(
            @RequestParam long lessonId, Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);
        model.addAttribute("lessonId", lessonId);

        return "pages/language-selection";
    }

    @PostMapping("/apply-lesson")
    public String applyLesson(
            long lessonId, String language, Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        UserDetails details = ControllerUtils.addUserDetailsToModel(authentication, model);

        if (details != null) {
            List<WordToCheck> wordsToCheck = convertWordsToQuestionsAndAnswers(lessonId, language);
            iterator = wordsToCheck.iterator();
            if (iterator.hasNext()) {
                setModelForGuessing(model);
                return "pages/guessing";
            }
        }

        return "redirect:/";
    }

    @PostMapping("/check-word")
    public String checkWord(String answer, Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);
        model.addAttribute("question", wordToCheck.question());
        model.addAttribute("answer", answer);

        if (wordToCheck.answer().equals(answer)) {
            return "pages/checking";
        } else {
            model.addAttribute("isCorrect", false);
            return "pages/guessing";
        }
    }

    @PostMapping("/next-word")
    public String nextWord(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        ControllerUtils.addUserDetailsToModel(authentication, model);

        if (iterator.hasNext()) {
            setModelForGuessing(model);
            return "pages/guessing";
        }

        return "redirect:/";
    }

    private void setModelForGuessing(Model model) {
        wordToCheck = iterator.next();
        model.addAttribute("question", wordToCheck.question());
        model.addAttribute("answer", "");
        model.addAttribute("isCorrect", true);
    }

    private List<WordToCheck> convertWordsToQuestionsAndAnswers(long id, String language) {
        List<WordDto> words = wordService.findAllByLessonId(id);

        return words.stream().map(w -> setQuestionsAndAnswers(language, w)).toList();
    }

    private WordToCheck setQuestionsAndAnswers(String language, WordDto w) {
        return switch (language) {
            case "EN" -> new WordToCheck(w.sk(), w.en());
            case "SK" -> new WordToCheck(w.en(), w.sk());
            default -> new WordToCheck();
        };
    }
}
