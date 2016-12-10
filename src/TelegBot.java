import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class TelegBot extends TelegramLongPollingBot {
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";



    public static void main(String[] args) {
        System.out.println("START POINT: "+ new java.util.Date());

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegBot());
            System.out.println(ANSI_GREEN +"CONNECTION ESTABLISHED SUCCESSFULLY!" + ANSI_RESET);
        } catch (TelegramApiException e) {
            System.out.print(ANSI_RED + "Connection Failed!" + ANSI_RESET);
            e.printStackTrace();
        }
    }




    @Override
    public String getBotUsername() {
        return "ScheduleMGIMObot";
    }

    @Override
    public String getBotToken() {
        return "";
    }


    @Override
    public void onUpdateReceived(Update update) {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Date dNow = new Date( );
        StringBuilder sb = new StringBuilder();
            Message message1 = new Message();
            Chat chat = message1.getChat();
            User user = message1.getFrom();

        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("QUERY SENT ON " + ft.format(dNow));
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/магимэ") || message.getText().equals("/мгимо") || message.getText().equals("/start"))
                sendMsg(message, "Меню помощи\n 1 - Для вызова расписание напиши 'Пары завтра' или 'Что я завтра проебу?'\n2 - Так же можно посмотреть и " +
                        "расписание на текущий день недели\n3 - Для того, чтобы узнать какая идет неделя напиши 'Какая неделя'\n");
            if (message.getText().equals("Пары завтра") || message.getText().equals("Что я завтра проебу?") || message.getText().equals("/rasp")) {
                switch (dayOfWeek) {
                    case 1:
                        sendMsg(message, "1 - Физ-ра\n2 - Мировая экономика\n3 - Испанский\n4 - Статистика");
                        break;
                    case 2:
                        sendMsg(message, "2 - Макроэкономика\n3 - Международные отношения и мировая политика\n4 - Испанский");
                        break;
                    case 3:
                        sendMsg(message, "1 - Английский\n2 - Мененджмент\n3 - Мировая экономика\n4 - Испанский");
                        break;
                    case 4:
                        sendMsg(message, "День военной кафедры");
                        break;
                    case 5:
                        sendMsg(message, "1 - Теория вероятностей и математическая статистика\n2 - Философия\n3 - Испанский\n4 - Английский ");
                        break;
                    case 6:
                        sendMsg(message, "1 - Макроэкономика\n2 - Философия\n3 - Испанский\n4 - Английский");
                        break;
                    default:
                        sendMsg(message, "Занятий нет.");
                        break;
                }
            }

            if (message.getText().equals("Пары сегодня")) {
                switch (dayOfWeek - 1) {
                    case 1:
                        sendMsg(message, "1 - Физ-ра\n2 - Мировая экономика\n3 - Испанский\n4 - Статистика");
                        break;
                    case 2:
                        sendMsg(message, "2 - Макроэкономика\n3 - Международные отношения и мировая политика\n4 - Испанский");
                        break;
                    case 3:
                        sendMsg(message, "1 - Английский\n2 - Мененджмент\n3 - Мировая экономика\n4 - Испанский");
                        break;
                    case 4:
                        sendMsg(message, "День военной кафедры");
                        break;
                    case 5:
                        sendMsg(message, "1 - Теория вероятностей и математическая статистика\n2 - Философия\n3 - Испанский\n4 - Английский ");
                        break;
                    case 6:
                        sendMsg(message, "1 - Макроэкономика\n2 - Философия\n3 - Испанский\n4 - Английский");
                        break;
                    default:
                        sendMsg(message, "Занятий нет.");
                        break;
                }
            }

              if (message.getText().equals("Какая неделя")){
                  String files = "";
                  Base file = new Base();
                   files = file.getFile();
                  int check = Integer.parseInt(files);

                  if((check%2)==0){
                      sendMsg(message, "Сейчас идет " + check + "-я неделя.\nЧетная.");
                  }
                  else{
                      if((check%1)==0){
                          sendMsg(message, "Сейчас идет " + check + "-я неделя.\nНечетная.");
                      }
                  }

              }

            }
        }

    private void sendMsg(Message message, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecRow = new KeyboardRow();
        keyboardFirstRow.add("Пары сегодня");
        keyboardSecRow.add("Пары завтра");
        keyboardFirstRow.add("Какая неделя");
        keyboardSecRow.add("Предложение");
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
