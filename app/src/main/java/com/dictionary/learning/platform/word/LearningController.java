package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.ui.ControllerUtils;
import com.dictionary.learning.platform.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LearningController {

    private final WordService wordService;
    private final UserService userService;
    private Iterator<WordToCheck> iterator;
    private WordToCheck wordToCheck;

    public LearningController(WordService wordService, UserService userService) {
        this.wordService = wordService;
        this.userService = userService;
    }

    @GetMapping("/learning")
    public String learning(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        UserDetails details = ControllerUtils.addUserDetailsToModel(authentication, model);

        int grade = 1;
        if (details != null) {
            grade = userService.findGradeByUsername(details.getUsername());
        }
        model.addAttribute("grade", grade);

        return "pages/learning";
    }

    @PostMapping("/choose-lesson")
    public String chooseLesson(
            int grade,
            int lesson,
            String language,
            Authentication authentication,
            HttpServletRequest request,
            Model model) {
        ControllerUtils.addCsrfTokenToModel(request, model);
        UserDetails details = ControllerUtils.addUserDetailsToModel(authentication, model);

        if (details != null) {
            List<WordToCheck> wordsToCheck =
                    convertWordsToQuestionsAndAnswers(grade, lesson, details.getUsername(), language);
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

    private List<WordToCheck> convertWordsToQuestionsAndAnswers(
            int grade, int lesson, String username, String language) {
        List<WordDto> words = wordService.findAllByLessonId(lesson);

        return words.stream().map(w -> setQuestionsAndAnswers(language, w)).toList();
    }

    private static WordToCheck setQuestionsAndAnswers(String language, WordDto w) {
        return switch (language) {
            case "EN" -> new WordToCheck(w.sk(), w.en());
            case "SK" -> new WordToCheck(w.en(), w.sk());
            default -> new WordToCheck();
        };
    }
}
