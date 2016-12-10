import java.util.Calendar;
import java.util.Date;


/**
 * Created by arseniy on 10.12.16.
 */
public class Data {
    int day = 1;
    int week = 1;
    long time;

    public int getDayWeek() {

        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        week = c.get(Calendar.DAY_OF_WEEK);
        return week;
    }

}
