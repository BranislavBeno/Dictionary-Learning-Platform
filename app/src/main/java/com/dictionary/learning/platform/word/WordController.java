package com.dictionary.learning.platform.word;

import com.dictionary.learning.platform.ui.ControllerUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WordController {

    private final WordService wordService;
    private Iterator<WordToCheck> iterator;
    private WordToCheck wordToCheck;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/learning")
    public String learning(Authentication authentication, HttpServletRequest request, Model model) {
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);

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
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            ControllerUtils.addCsrfTokenToModel(request, model);
            model.addAttribute("username", username);

            Set<WordToCheck> wordsToCheck = convertWordsToQuestionsAndAnswers(grade, lesson, username, language);
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
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);
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
        ControllerUtils.addUserDetailsToModel(authentication, model);
        ControllerUtils.addCsrfTokenToModel(request, model);

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

    private Set<WordToCheck> convertWordsToQuestionsAndAnswers(
            int grade, int lesson, String username, String language) {
        Set<WordDto> words = wordService.findAllByUserNameByGradeByLesson(username, grade, lesson);

        return words.stream().map(w -> setQuestionsAndAnswers(language, w)).collect(Collectors.toSet());
    }

    private static WordToCheck setQuestionsAndAnswers(String language, WordDto w) {
        return switch (language) {
            case "EN" -> new WordToCheck(w.sk(), w.en());
            case "SK" -> new WordToCheck(w.en(), w.sk());
            default -> new WordToCheck();
        };
    }
}
