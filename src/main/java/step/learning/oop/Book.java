package step.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
@Serializable
public class Book extends Literature implements Copyable, Printable,Multiple{
    private String author;

    public Book(String author, String title) {
        this.setAuthor(author);
        super.setTitle(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getCard() {
        return String.format("Book: %s %s",this.getAuthor(),super.getTitle());
    }

    @FromJsonParser
    public static Book fromJson(JsonObject jsonObject) throws ParseException{
        String[] requiredField = {"author","title"};
        for (String field : requiredField){
            if(!jsonObject.has(field)){
                throw new ParseException("Missing required field: " + field,0);
            }
        }
        return new Book(jsonObject.get(requiredField[0]).getAsString(),
                        jsonObject.get(requiredField[1]).getAsString());
    }
    @ParseChecker
    public static boolean isParseableFromJson ( JsonObject jsonObject){
        String[] requiredFields = {"author", "title"};
        for (String field : requiredFields){
            if(!jsonObject.has(field)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
/*
this.getTitle(), якщо є
getTitle < super.getTitle(), якщо немає this.getTitle()

виклик методу не є однозначним і залежить від наявності/відсутності
this.getTitle()
Іншими словами, переозначення this.getTitle() призведе до "перемикання"
роботи на себе. А оскільки @Override не вимагається, це може статись
випадково.
 */