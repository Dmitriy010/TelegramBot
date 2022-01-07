package com.telegram.api.handlers.menu;

import com.telegram.api.BotState;
import com.telegram.api.InputMessageHandler;
import com.telegram.cache.UserDataCache;
import com.telegram.dto.PortfolioDto;
import com.telegram.model.MyPortfolio;
import com.telegram.model.UserProfileData;
import com.telegram.services.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ShowProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private StockService stockService;


    public ShowProfileHandler(UserDataCache userDataCache, StockService stockService) {
        this.userDataCache = userDataCache;
        this.stockService = stockService;
    }

    @Override
    public SendMessage handle(Message message) throws ExecutionException, InterruptedException {
        final long userId = message.getFrom().getId();

        userDataCache.setUsersCurrentBotState(userId,BotState.SHOW_INVEST_PROFILE);
        PortfolioDto portfolioDto = stockService.getPortfolioDto();
        List<MyPortfolio> portfolioDtoPrice = stockService.getPortfolioDtoPrice(portfolioDto,
                stockService.getPricesProfile(portfolioDto));
        StringBuilder stringBuilder = new StringBuilder();
        for (MyPortfolio myPortfolio : portfolioDtoPrice) {
            stringBuilder.append(myPortfolio);
            stringBuilder.append("\n");
        }
        String str = stringBuilder.toString();
        return new SendMessage(String.valueOf(message.getChatId()),str);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_INVEST_PROFILE;
    }
}
