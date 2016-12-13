package Telegram.MMbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
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


public class TelegBot extends TelegramLongPollingBot {
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    AuthClass ac = new AuthClass();


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
        return ac.getName();
    }

    @Override
    public String getBotToken() {
        return ac.getToken();
    }


    @Override
    public void onUpdateReceived(Update update) {
        DateClass date = new DateClass();
        RegisterUser reg = new RegisterUser();
        Message message = update.getMessage();
        User user = message.getFrom();
        StringBuilder id = new StringBuilder();
        id.append(user.getId());
        Date dNow = new Date( );


        SimpleDateFormat ft = new SimpleDateFormat (" E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println(" QUERY SENT ON " + ft.format(dNow));


        if (message.hasText()) {
            if (message.getText().equalsIgnoreCase("/магимэ") || message.getText().equals("/мгимо") || message.getText().equals("/start"))
                sendMsg(message, "Меню помощи\n 1 - Для вызова расписание напиши 'Пары завтра' или 'Что я завтра проебу?'\n2 - Так же можно посмотреть и " +
                        "расписание на текущий день недели\n3 - Для того, чтобы узнать какая идет неделя напиши 'Какая неделя'\n");
            if (message.getText().equalsIgnoreCase("Пары завтра") || message.getText().equals("Что я завтра проебу?") || message.getText().equals("/rasp")) {
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

            if (message.getText().equalsIgnoreCase("Пары сегодня")) {
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

              if (message.getText().equalsIgnoreCase("Какая неделя")){
                  String files = "";
                  FileClass file = new FileClass();
                   files = file.getFile();
                  int check = Integer.parseInt(files);

                  if((check%2)==0){
                      sendMsg(message, "Сейчас идет " + check + "-я неделя.\nЧетная.");
                  }
                  else{
                      sendMsg(message, "Сейчас идет " + check + "-я неделя.\nНечетная.");
                  }
              }

              if (message.getText().equalsIgnoreCase("регистрация")) {
                  int memory;
                  memory = Integer.parseInt(id.toString());
                  if (!reg.checkUser(memory)) {
                          String group = "nogroup";
                          StringBuilder fio = new StringBuilder();
                          StringBuilder username = new StringBuilder();
                          username.append(user.getFirstName());
                          fio.append("Name: " + user.getFirstName() + " Surname: " + user.getLastName() + " Username: " + user.getUserName());
                          group = message.getText();

                          if (reg.regUser(memory, group, fio.toString())) {
                              sendMsg(message, username.toString() +", вы успешно зарегистрировались.");
                          } else
                              sendMsg(message, "Ошибка регистрации.");
                      }
                            else {
                              sendMsg(message, "Пользователь уже был зарегистрирован."); }

                  }
                  if(message.getText().equalsIgnoreCase("Выбрать группу")){
                      sendMsg(message, "Чтобы выбрать/сменить группу, напишите ее название по примеру:\n\n МЭО-1-2\n\nГде МЭО - название факультета\n1 - " +
                              "номер группы\n2 - номер курса.");

                      int memory;
                      memory = Integer.parseInt(id.toString());
                      reg.setGroup(memory,message.getText());

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
        keyboardSecRow.add("Регистрация");
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
