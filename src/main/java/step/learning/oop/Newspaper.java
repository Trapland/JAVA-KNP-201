package step.learning.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Newspaper extends Literature implements Periodic{
    private Date date;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat cardDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Newspaper(String title, String date) throws ParseException
    {
        this(title, dateFormat.parse(date));
    }

    public Newspaper(String title, Date parse) {
        super.setTitle(title);
        this.setDate(parse);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String getCard(){
        return String.format("Newspaper '%s' from %s", super.getTitle(), cardDateFormat.format(this.getDate()));
    }

    @Override
    public String getPeriod() {
        return "daily";
    }
}
