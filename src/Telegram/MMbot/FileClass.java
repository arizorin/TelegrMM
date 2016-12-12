package Telegram.MMbot;

import java.io.*;
import java.util.Scanner;

/**
 * Created by arseniy on 09.12.16.
 */
public class FileClass {
    String line;
    int week = 1;

    private String file = "week";

    TelegBot tb = new TelegBot();
    DateClass dat = new DateClass();

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
