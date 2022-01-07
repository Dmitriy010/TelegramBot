package com.telegram.config;

import com.telegram.TelegramBot;
import com.telegram.api.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    private boolean isSandBoxMode;


    @Bean
    public TelegramBot TelegramBot(TelegramFacade telegramFacade) {



        TelegramBot telegramBot = new TelegramBot(telegramFacade);
        telegramBot.setBotUserName(botUserName);
        telegramBot.setBotToken(botToken);
        telegramBot.setWebHookPath(webHookPath);

        return telegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public OpenApi api(){

        String token = System.getenv("token");
        return new OkHttpOpenApi(token,isSandBoxMode);
    }
}

