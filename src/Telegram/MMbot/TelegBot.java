package Telegram.MMbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


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
            System.out.println("---------------------------------------------------");
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
        DateClass date = new DateClass();

        Date dNow = new Date( );


        SimpleDateFormat ft = new SimpleDateFormat (" E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println(" QUERY SENT ON " + ft.format(dNow));
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            if (message.getText().equals("/магимэ") || message.getText().equals("/мгимо") || message.getText().equals("/start"))
                sendMsg(message, "Меню помощи\n 1 - Для вызова расписание напиши 'Пары завтра' или 'Что я завтра проебу?'\n2 - Так же можно посмотреть и " +
                        "расписание на текущий день недели\n3 - Для того, чтобы узнать какая идет неделя напиши 'Какая неделя'\n");
            if (message.getText().equals("Пары завтра") || message.getText().equals("Что я завтра проебу?") || message.getText().equals("/rasp")) {
                switch (date.getDayWeek()) {
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
                switch (date.getDayWeek() - 1) {
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
                  FileClass file = new FileClass();
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

              if (message.getText().equals("/register")) {
                  User user = message.getFrom();
                  RegisterUser reg = new RegisterUser();
                  StringBuilder sb = new StringBuilder();
                  int memory;
                  sb.append(user.getId());
                  memory = Integer.parseInt(sb.toString());
                  if (reg.checkUser(memory) != true) {
                      String group = "MEO2-2";
                      boolean status;
                      StringBuilder fio = new StringBuilder();
                      fio.append("Name: " + user.getFirstName() + " Surname: " + user.getLastName() + " Username: " + user.getUserName());
                      status = reg.regUser(memory, group, fio.toString());
                      if (status == true) {
                          sendMsg(message, "Пользователь с id: '" + sb.toString() + "' ,был зарегистрирован.");
                      } else
                          sendMsg(message, "Ошибка регистрации.");
                  }
                  else
                      sendMsg(message, "Пользователь уже был зарегистрирован.");
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
