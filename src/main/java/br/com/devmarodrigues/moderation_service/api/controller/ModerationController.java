package br.com.devmarodrigues.moderation_service.api.controller;

import br.com.devmarodrigues.moderation_service.api.model.ModerationInput;
import br.com.devmarodrigues.moderation_service.api.model.ModerationResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@RestController
@RequestMapping("/api/moderate")
public class ModerationController {

    private static final Set<String> FORBIDDEND_WORDS = Set.of("raiva", "xingamento");
    private static final Pattern FORBIDDEN_PATTERN =
            Pattern.compile("\\b(" + String.join("|", FORBIDDEND_WORDS) + ")\\b",
                    Pattern.CASE_INSENSITIVE);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModerationResult create(@RequestBody ModerationInput moderationInput) {
        Matcher matcher = FORBIDDEN_PATTERN.matcher(moderationInput.getText());

        if (matcher.find()) {
            return ModerationResult.builder()
                .approved(false)
                .reason(matcher.group())
                .build();
        }

        return ModerationResult.builder()
            .approved(true)
            .build();
    }

}
