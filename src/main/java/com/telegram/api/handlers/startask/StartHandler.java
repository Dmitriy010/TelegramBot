package com.telegram.api.handlers.startask;

import com.telegram.api.BotState;
import com.telegram.api.InputMessageHandler;
import com.telegram.cache.UserDataCache;
import com.telegram.services.MainMenuService;
import com.telegram.services.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

    @Slf4j
    @Component
    public class StartHandler implements InputMessageHandler {

        private MainMenuService mainMenuService;
        private ReplyMessageService replyMessageService;

        public StartHandler(MainMenuService mainMenuService, ReplyMessageService replyMessageService) {
            this.mainMenuService = mainMenuService;
            this.replyMessageService = replyMessageService;
        }

        @Override
        public SendMessage handle(Message message) {
            return processUsersInput(message);
        }

        @Override
        public BotState getHandlerName() {
            return BotState.START;
        }

        private SendMessage processUsersInput(Message inputMsg) {

            SendMessage replyToUser = mainMenuService.getMainMenuMessage(String.valueOf(inputMsg.getChatId()),
                    replyMessageService.getReplyText("reply.showMainMenu"));

            return replyToUser;
        }

    }

