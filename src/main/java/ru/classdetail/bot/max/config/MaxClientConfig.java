package ru.classdetail.bot.max.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.classdetail.bot.client.ApiClient;
import ru.classdetail.bot.client.api.BotsApi;
import ru.classdetail.bot.client.api.MessagesApi;
import ru.classdetail.bot.client.api.SubscriptionsApi;
import ru.classdetail.bot.client.api.UploadApi;

@Configuration
public class MaxClientConfig {

    @Bean
    public ApiClient maxApiClient(MaxProperties properties) {
        ApiClient client = new ApiClient();
        client.updateBaseUri(properties.getUrl());
        client.setRequestInterceptor(request ->
                request.header("Authorization", properties.getToken()));
        return client;
    }

    @Bean
    public MessagesApi messagesApi(ApiClient maxApiClient) {
        return new MessagesApi(maxApiClient);
    }

    @Bean
    public UploadApi uploadApi(ApiClient maxApiClient) {
        return new UploadApi(maxApiClient);
    }

    @Bean
    public SubscriptionsApi subscriptionsApi(ApiClient maxApiClient) {
        return new SubscriptionsApi(maxApiClient);
    }

    @Bean
    public BotsApi botsApi(ApiClient maxApiClient) {
        return new BotsApi(maxApiClient);
    }
}
