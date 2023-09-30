package step.learning.oop;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Library {
    public List<Literature> getFunds() {
        return funds;
    }

    private final List<Literature> funds;

    public Library(){
        funds = new LinkedList<>();
    }
    public void add(Literature literature){
        funds.add(literature);
    }
    private List<Literature> getSerializableFunds(){
        List<Literature> serializableFunds = new ArrayList<>();
        for(Literature literature : getFunds()){
            if(literature.getClass().isAnnotationPresent(Serializable.class))
            {
                serializableFunds.add(literature);
            }
        }
        return serializableFunds;
    }

    private List<Class<?>> getSerializableClasses(){
        List<Class<?>> literatures = new LinkedList<>();
        // Визначаємо ім'я довільного об'єкту, який гарантовано знаходиться у пакеті "oop"
        String className = Literature.class.getName(); // step.learning.oop.Literature
        // Видаляємо ім'я самого класу, залишаємо лише пакет
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String packagePath = packageName.replace(".","/");
        String absolutePath = Literature.class.getClassLoader().getResource(packagePath).getPath();
        File[] files = new File(absolutePath).listFiles();
        for(File file : files){
            if(file.isFile()){
                String filename = file.getName();
                if(filename.endsWith(".class")){
                    String fileClassName = filename.substring(0,filename.lastIndexOf('.'));
                    try {
                        Class<?> fileClass = Class.forName(packageName + '.' + fileClassName);
                        if (fileClass.isAnnotationPresent(Serializable.class))
                        {
                            literatures.add(fileClass);
                        }
                    } catch (ClassNotFoundException ignored) {
                        continue;
                    }
                }
            }
            else if (file.isDirectory()) {
                continue;
            }
        }
        return literatures;
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        FileWriter writer = new FileWriter( "./src/main/resources/library.json");
        writer.write(gson.toJson(this.getSerializableFunds()));
        writer.close();
    }

    public void load() throws RuntimeException{
        try(InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(
            this.getClass().getClassLoader().getResourceAsStream("library.json"))
        )) {
            this.funds.clear();
        for (JsonElement item : JsonParser.parseReader(reader).getAsJsonArray()){
            this.funds.add(this.fromJson(item.getAsJsonObject()));
        }
        }
        catch (IOException | ParseException ex){
            throw new RuntimeException(ex);
        }
        catch (NullPointerException ignored){
            throw new RuntimeException( "Resource not found");
        }
    }

    private Literature fromJson(JsonObject jsonObject) throws ParseException{

        List<Class<?>> literatures = getSerializableClasses();
        try {
            for (Class<?> literature : literatures){
                Method isParseableFromJson = null;
                for (Method method : literature.getDeclaredMethods())
                {
                    if(method.isAnnotationPresent(ParseChecker.class))
                    {
                        if(isParseableFromJson != null){
                            throw new ParseException("Multiple ParseChecker annotations",0);
                        }
                        isParseableFromJson = method;

                    }

                }
                if(isParseableFromJson == null){
                    continue;
                }

                isParseableFromJson.setAccessible(true);
                boolean res = (boolean) isParseableFromJson.invoke(null,jsonObject);
                if(res){
                    Method fromJson = literature.getMethod("fromJson", JsonObject.class);
                    fromJson.setAccessible(true);
                    return (Literature) fromJson.invoke(null,jsonObject);
                }
            }

        } catch (Exception e) {
            throw new ParseException("Reflection error: " + e.getMessage(),0);
        }
        /*
        if(Book.isParseableFromJson(jsonObject)){
            return Book.fromJson(jsonObject);
        }
        else if(Journal.isParseableFromJson(jsonObject)){
            return Journal.fromJson(jsonObject);
        }
        else if(Newspaper.isParseableFromJson(jsonObject)){
                return Newspaper.fromJson(jsonObject);
        }*/
        throw new ParseException("Literature type unrecognised",0);
    }
    public void Add(Literature l){
        if(l != null)
            funds.add(l);
    }
    public void PrintAll(){
        for (Literature l: funds) {
            System.out.println(l.getCard());
        }
    }

    public void printAllCards(){
        for (Literature literature : funds){
            System.out.println(literature.getCard());
        }
    }

    public void printCopyable(){
        for (Literature literature : funds){
            if(isCopyable(literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonCopyable(){
        for (Literature literature : funds){
            if(!isCopyable(literature)){
                System.out.println(literature.getCard());
            }
        }
    }
    public boolean isCopyable(Literature literature){
        return (literature instanceof Copyable);
    }
    public void printPeriodic(){
        for (Literature literature : funds){
            if(isPeriodic(literature)){
                Periodic litAsPeriodic = (Periodic) literature;
                System.out.println(litAsPeriodic.getPeriod() + " " + literature.getCard());
            }
        }
    }
    public void printPeriodic2(){
        for (Literature literature : funds){
            try {
                Method getPeriodMethod = literature.getClass().getDeclaredMethod("getPeriod");
                System.out.println(getPeriodMethod.invoke(literature) + " " + literature.getCard());
            }
            catch (Exception ignored)
            {

            }
        }
    }
    public void printNonPeriodic(){
        for (Literature literature : funds){
            if(!isPeriodic(literature)){
                System.out.println(literature.getCard());
            }
        }
    }
    public boolean isPeriodic(Literature literature){
        return (literature instanceof Periodic);
    }

    public boolean isPrintable(Literature literature){
        return (literature instanceof Printable);
    }
    public void printPrintable(){
        for (Literature literature : funds){
            if(isPrintable(literature)){
                System.out.println(literature.getCard());
            }
        }
    }
    public void printNonPrintable(){
        for (Literature literature : funds){
            if(!isPrintable(literature)){
                System.out.println(literature.getCard());
            }
        }
    }
    public boolean isMultiple(Literature literature){
        return (literature instanceof Multiple);
    }

    public void printMultiple(){
        for (Literature literature : funds){
            if(isMultiple(literature)){
                Multiple litAsMultiple = (Multiple) literature;
                System.out.println(litAsMultiple.getCount() + " " + literature.getCard());
            }
        }
    }
    public void printNonMultiple(){
        for (Literature literature : funds){
            if(!isMultiple(literature)){
                System.out.println(literature.getCard());
            }
        }
    }
}
