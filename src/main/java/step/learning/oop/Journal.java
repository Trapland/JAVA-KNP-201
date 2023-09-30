package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.Date;
@Serializable
public class Journal extends Literature implements Copyable,Periodic{
    private int number;

    public Journal(String title, int number) {
        this.setNumber(number);
        super.setTitle(title);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getCard() {
        return String.format("Journal: %s %s",this.getNumber(),super.getTitle());
    }

    @Override
    public String getPeriod() {
        return "monthly";
    }
    public static Journal fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredField = {"title","number"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Journal(jsonObject.get(requiredField[0]).getAsString(),
                            jsonObject.get(requiredField[1]).getAsInt());
    }
    @ParseChecker
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"number", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
}
