package downdetector.bot;

import downdetector.config.BotProperties;
import downdetector.domain.CheckResult;
import downdetector.entity.SiteUrl;
import downdetector.entity.SiteUrlAdd;
import downdetector.repository.SiteUrlRepository;
import downdetector.service.GetSitesUrlAndStatus;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;


@Component
public class DownDetektorBot  {

    private final BotProperties botProperties;
    private final GetSitesUrlAndStatus getSitesAndUrl;
    private final TelegramLongPollingBot bot;
    private final TelegramBotsApi telegramBotsApi;
    private final SiteUrlRepository siteUrlRepository;

    class MyBot extends TelegramLongPollingBot {



        @Override
        public String getBotUsername() {
            return botProperties.bot().username();
        }

        @Override
        public String getBotToken() {
            return botProperties.bot().token();
        }

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                Chat chat = message.getChat();
                User user = message.getFrom();

                if (message.isCommand()) {
                    if ("/all".equals(message.getText())) {
                        List<CheckResult> checkResults = getSitesAndUrl.getUrlAndStatus();
                        StringBuilder sb = new StringBuilder();
                        for (CheckResult checkResult : checkResults) {
                            sb.append(checkResult.getUrl()).append(" ").append(checkResult.getStatus()).append("\n");
                        }
                        sendTextMessage(chat.getId(), sb.toString());
                    }

                    if ("/add".equals(message.getText())) {
                        String textMessage = "Adding sites";
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(update.getMessage().getChatId().toString());
                        sendMessage.setText(textMessage);

                        if (message.hasText()) {
                            String url = message.getText();
                            SiteUrlAdd siteUrlAdd = new SiteUrlAdd(url);
                            SiteUrl siteUrl = new SiteUrl(null, siteUrlAdd.getUrl());
                            siteUrlRepository.save(siteUrl);

                        }

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }



        private void sendTextMessage(Long chatId, String text) {
            try {

                execute(
                    SendMessage.builder()
                        .text(text)
                        .chatId(chatId.toString())
                        .build()

                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public DownDetektorBot(BotProperties botProperties,
                           SiteUrlRepository siteUrlRepository,
                           GetSitesUrlAndStatus getSitesAndUrl) throws TelegramApiException {
        this.botProperties = botProperties;
        this.siteUrlRepository = siteUrlRepository;
        this.getSitesAndUrl = getSitesAndUrl;
        this.telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        this.bot = new MyBot();
        this.telegramBotsApi.registerBot(bot);
    }
}

