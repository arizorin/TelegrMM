import org.telegram.telegrambots.api.objects.Message;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.Scanner;

import static java.time.DayOfWeek.*;

/**
 * Created by arseniy on 09.12.16.
 */
public class Base {
    String line;
    int week = 1;

    private String file = "week";

    TelegBot tb = new TelegBot();
    Data dat = new Data();

    public String getFile() {

        try {
            Scanner scanner = new Scanner( new File("week")) ;

            line = scanner.nextLine();
            if(dat.getDayWeek()-1 == 1 )
            {       int check = Integer.parseInt(line);
                    System.out.println(check);
                    check++;
                    System.out.println(check);
                    String newline = Integer.toString(check);
                    line = line.replaceAll(line,newline);

                System.out.println("DATA UPDATED");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(tb.ANSI_RED +"FILE 'WEEK' CAN'T BE OPENED" + tb.ANSI_RESET);
            e.printStackTrace();
        }



        return line;
    }



}
