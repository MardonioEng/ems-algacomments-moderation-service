package br.com.devmarodrigues.moderation_service.api.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ModerationInput {

    private String text;
    private UUID commentId;
}
