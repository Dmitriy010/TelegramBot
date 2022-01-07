package com.telegram.api.handlers.menu;

import com.telegram.api.BotState;
import com.telegram.api.InputMessageHandler;
import com.telegram.cache.UserDataCache;
import com.telegram.dto.PortfolioDto;
import com.telegram.dto.ResultsDto;
import com.telegram.model.Results;
import com.telegram.model.UserProfileData;
import com.telegram.services.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ShowResultHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private StockService stockService;


    public ShowResultHandler(UserDataCache userDataCache, StockService stockService) {
        this.userDataCache = userDataCache;
        this.stockService = stockService;
    }

    @Override
    public SendMessage handle(Message message) throws ExecutionException, InterruptedException {
        final long userId = message.getFrom().getId();

        userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_INVEST_RESULT);
        PortfolioDto portfolioDto = stockService.getPortfolioDto();

        List<Results> resultsList = stockService.getResultsProfile(portfolioDto,
                stockService.getPricesProfile(portfolioDto)).getResultsList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Results results : resultsList) {
            stringBuilder.append(results);
            stringBuilder.append("\n");
        }
        String str = stringBuilder.toString();
        return new SendMessage(String.valueOf(message.getChatId()),str);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_INVEST_RESULT;
    }
}
