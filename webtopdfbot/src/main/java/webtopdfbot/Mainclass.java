package webtopdfbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new webtopdfbot());
            
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
