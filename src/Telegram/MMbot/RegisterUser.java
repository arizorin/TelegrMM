package Telegram.MMbot;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;
import java.nio.file.Files;

public class RegisterUser{
    String text;
    String group;
    String format = "";
    String filePath = "userbase";


    public boolean regUser(int id, String group, String fio) {
        if(group != null && group != format) {

           try {
                text ="'" + Integer.toString(id) + "'" + "," + "'" + group + "'" + "; - " + fio + "\n";
                FileWriter writer = new FileWriter(filePath, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(text);
                bufferWriter.close();
                System.out.println("NEW USER REGISTERED.");
               }
                catch (IOException e) {
                System.out.println("ERROR. USER CAN'T BE REGISTERED");
                                      }
            return true;
        }
        else
            return false;
    }


    public boolean checkUser(int id)
    {
        String search = "'"+Integer.toString(id)+"'";
        try{
            FileInputStream ips = new FileInputStream(new File(filePath));
            byte[] content = new byte[ips.available()];
            ips.read(content);
            ips.close();

            String[] lines = new String(content, "Cp1251").split(";\n"); // кодировку указать нужную
            for (String line : lines) {
                if (line.contains(search)) {

                        return true;
                    }
                    else
                        return false;
                }

        }
        catch (IOException e){
            System.out.println("USER SEARCH ERROR");

        }
        System.out.println("BAD");
        return false;

    }

}
