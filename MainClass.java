import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;


public class MainClass {


    public static void main(String[] args) throws SQLException {
        ConnectionBot cm= new ConnectionBot();
        Connection connection =cm.getConnection();
        Statement myStmt =connection.createStatement();




        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();


        try {
            telegramBotsApi.registerBot(new TelegramBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
