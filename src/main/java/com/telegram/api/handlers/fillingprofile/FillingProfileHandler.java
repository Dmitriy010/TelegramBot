package com.telegram.api.handlers.fillingprofile;

import com.telegram.api.BotState;
import com.telegram.api.InputMessageHandler;
import com.telegram.cache.UserDataCache;
import com.telegram.model.UserProfileData;
import com.telegram.services.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class FillingProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessageService messageService;

    public FillingProfileHandler(UserDataCache userDataCache,
                                 ReplyMessageService messageService) {
        this.userDataCache = userDataCache;
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.FILLING_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_NAME);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_PROFILE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        long userId = inputMsg.getFrom().getId();
        String chatId = String.valueOf(inputMsg.getChatId());

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_NAME)) {
            replyToUser = messageService.getReplyMessage(chatId, "reply.askName");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_AGE);
        }

        if (botState.equals(BotState.ASK_AGE)) {
            profileData.setName(usersAnswer);
            replyToUser = messageService.getReplyMessage(chatId, "reply.askAge");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_LEVEL_ENGLISH);
        }

        if (botState.equals(BotState.ASK_LEVEL_ENGLISH)) {
            replyToUser = messageService.getReplyMessage(chatId, "reply.askLevelEnglish");
            profileData.setAge(Integer.parseInt(usersAnswer));
            userDataCache.setUsersCurrentBotState(userId, BotState.PROFILE_FILLED);
        }

        if (botState.equals(BotState.PROFILE_FILLED)) {
            profileData.setLevelEnglish(usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_START);
            String result = "Your data:\n"+profileData+"\nLet's go to learn new English words";
            replyToUser = new SendMessage(chatId, result);
        }

        userDataCache.saveUserProfileData(userId, profileData);

        return replyToUser;
    }
}

