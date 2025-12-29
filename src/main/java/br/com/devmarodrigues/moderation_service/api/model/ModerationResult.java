package br.com.devmarodrigues.moderation_service.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModerationResult {

    private Boolean approved;
    private String reason;
}
