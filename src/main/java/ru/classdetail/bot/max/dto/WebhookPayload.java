package ru.classdetail.bot.max.dto;

import java.util.Map;

public record WebhookPayload(
        String type,
        Long timestamp,
        Map<String, Object> data
) {
}
