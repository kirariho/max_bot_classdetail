package ru.classdetail.bot.max.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.classdetail.bot.max.dto.WebhookPayload;

@RestController
@RequestMapping("/webhook/")
public class WebhookController {

    @Value("${max.bot.webhook.secret}")
    private String expectedValue;

    @PostMapping("/max")
    public ResponseEntity<String> handleWebhook(
            @RequestHeader(value = "X-Max-Bot-Api-Secret", required = false) String incomingSecret,
            @RequestBody WebhookPayload payload
            ){
        if(incomingSecret == null || !incomingSecret.equals(expectedValue)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

        return ResponseEntity.ok().build();

    }
}
