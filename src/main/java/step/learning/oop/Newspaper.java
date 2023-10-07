package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Serializable
public class Newspaper extends Literature implements Periodic, Printable{
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

    @FromJsonParser
    public static Newspaper fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredField = {"title","date"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Newspaper(jsonObject.get(requiredField[0]).getAsString(),
                jsonObject.get(requiredField[1]).getAsString());
    }
    @ParseChecker
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"date", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
}
