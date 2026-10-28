package ru.classdetail.bot.max;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.classdetail.bot.client.ApiException;
import ru.classdetail.bot.client.api.BotsApi;
import ru.classdetail.bot.client.model.BotInfo;

@Slf4j
@Service
public class MaxBotApiPing {
    private final BotsApi botsApi;

    public MaxBotApiPing(BotsApi botsApi){
        this.botsApi = botsApi;
    }

    public void ping() throws ApiException {
        BotInfo me = botsApi.getMyInfo();
        log.info("MAX 200 status with userId={} name={}", me.getUserId(), me.getFirstName());
    }
}
