package com.telegram.api.handlers.ask;

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
    public class StartHandler implements InputMessageHandler {
        private UserDataCache userDataCache;
        private ReplyMessageService messageService;

        public StartHandler(UserDataCache userDataCache,
                                 ReplyMessageService messageService) {
            this.userDataCache = userDataCache;
            this.messageService = messageService;
        }

        @Override
        public SendMessage handle(Message message) {
            return processUsersInput(message);
        }

        @Override
        public BotState getHandlerName() {
            return BotState.ASK_START;
        }

        private SendMessage processUsersInput(Message inputMsg) {
            long userId = inputMsg.getFrom().getId();
            String chatId = String.valueOf(inputMsg.getChatId());

            SendMessage replyToUser = messageService.getReplyMessage(chatId,"reply.askStart");
            userDataCache.setUsersCurrentBotState(userId,BotState.FILLING_PROFILE);

            return replyToUser;
        }

    }

