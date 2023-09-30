package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
@Serializable
public class Hologram extends Literature {
    private boolean animated;

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public Hologram(String title,Boolean animated) {
        super.setTitle(title);
        this.setAnimated(animated);
    }
    @Override
    public String getCard() {
        return String.format("Hologram '%s' isAnimated '%s'", super.getTitle(),this.isAnimated());
    }

    public static Hologram fromJson(JsonObject jsonObject) throws ParseException {
        String[] requiredField = {"title","animated"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Hologram(jsonObject.get(requiredField[0]).getAsString(),
                jsonObject.get(requiredField[1]).getAsBoolean());
    }
    @ParseChecker
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"title", "animated"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }
}
