import java.util.Calendar;
import java.util.Date;

public class DateObj {

    private final Calendar calDate = Calendar.getInstance();

    private final Integer year, month, day;

    DateObj(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        calDate.set(year, month, day);
    }

    public Date getDate() {
        return calDate.getTime();
    }

    public Calendar getCalendar() {
        return calDate;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }
}
