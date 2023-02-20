import java.util.Calendar;
import java.util.Date;

public class DateObj {

    private final Calendar calDate = Calendar.getInstance();

    private Date date;
    private final Integer year, month, day;

    DateObj(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        calDate.set(year, month, day);
    }

    public Date getDate() {
        date = calDate.getTime();
        return date;
    }
}
