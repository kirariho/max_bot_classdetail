package ru.classdetail.bot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.classdetail.bot.client.api.BotsApi;
import ru.classdetail.bot.max.MaxBotApiPing;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final MaxBotApiPing ping;
    public ApplicationRunnerImpl(MaxBotApiPing ping){
        this.ping = ping;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        ping.ping();

    }
}
