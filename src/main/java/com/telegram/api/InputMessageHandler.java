package com.telegram.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.concurrent.ExecutionException;

public interface InputMessageHandler {
    SendMessage handle(Message message) throws ExecutionException, InterruptedException;

    BotState getHandlerName();
}
