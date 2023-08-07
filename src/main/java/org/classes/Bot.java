package org.classes;
import lombok.*;
import lombok.Getter;

import org.telegram.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.util.HashMap;
import java.util.Map;



//
public final class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    @Getter
    private static final Settings defaultSettings = new Settings(1, 15, 1);

    @Getter
    private static Map<Long, Settings> userSettings;
    private NonCommand nonCommand;

    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        //создаём вспомогательный класс для работы с сообщениями, не являющимися командами
        this.nonCommand = new NonCommand();//NonCommand создан без реализации
        //регистрируем команды
        //register(new StartCommand("start", "Старт"));//Не находит StartCommand
        //register(new HelpCommand("help","Помощь"));
        //Я так понял мы позже должны будем написать эти функции
        userSettings = new HashMap<Long, Settings>();
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String userName = "Joe"; //getUsername(msg);//Функции нет

        String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());//nonCommand не найдена
        setAnswer(chatId, userName, answer);
    }
    //Settings нужно создать
    public static Settings getUserSettings(Long chatId) {
        Map<Long, Settings> userSettings = Bot.getUserSettings();
        Settings settings = userSettings.get(chatId);
        if (settings == null) {
            return defaultSettings;
        }
        return settings;
    }

    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Error executing answer:" + e.getMessage());
        }
    }
}