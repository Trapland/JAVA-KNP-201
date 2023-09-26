package step.learning.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class OopDemo {

    public void run(){
        // JSON - засобами Gson
        Gson gson = new Gson();
        String str = "{\"author\": \"D. Knuth\",\"title\": \"Art of Programming\"}";
        Book book = gson.fromJson(str, Book.class); // - typeof
        System.out.println(book.getCard());
        System.out.println(gson.toJson(book));
        book.setAuthor(null);
        System.out.println(gson.toJson(book));
        Gson gson2 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.println(gson2.toJson(book));
        try (
                InputStream bookStream = this.getClass().getClassLoader().getResourceAsStream("book.json");
                InputStreamReader bookReader = new InputStreamReader(Objects.requireNonNull(bookStream));

        ){
            book = gson.fromJson(bookReader, Book.class);
            System.out.println(book.getCard());
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    public void run1(){
        Library library = new Library();
        try {
            library.add(new Book("D. Knuth", "Art of Programming"));
            library.add(new Newspaper("Daily mail", "2023-05-06"));
            library.add(new Journal("New Journal", 157));
            library.add(new Book("Richter", "Platform .NET"));
            library.add(new Newspaper("Washington Post", "2023-05-06"));
            library.add(new Journal("Amogus Spawning", 32));
        }
        catch (Exception ex){
            System.out.println("Literature creation error" + ex.getMessage());
        }

        library.printAllCards();
        System.out.println("--------- COPYABLE -------------");
        library.printCopyable();

        System.out.println("--------- NON COPYABLE -------------");
        library.printNonCopyable();

        System.out.println("--------- PERIODIC -------------");
        library.printPeriodic2();

        System.out.println("--------- NON PERIODIC -------------");
        library.printNonPeriodic();

    }
}
/*
getClassLoader(), викликаний на довільному типі з нашого проєкту
дозволить

 */
/*
ООП - об'єктно-орієнтована парадигма программування
Програма - управління об'єктами та їх взаємодія
Програмування - налаштування об'єктів та зв'язків
Види зв'язків
    - спадкування (наслідування)
    - асоціація
    - композиція
    - агрегація
    - залежність
 */
/*
Бібліотека
Моделюємо книгосховище (бібліотеку) у якому є каталог
Видання є різного типу: книги, газети, журнали, тощо
Кожен тип має однакову картку у каталозі, у якій відзначається тип видання.

Література - термін, що поєднує реальні сутності (книги, газети, журнали, тощо)
Оскільки довільне видання повинно формувати картку для каталога, у цей клас
(Література) має бути включений метод getCard(), але, оскільки до нього входить
відомість про тип (який відомий тільки у конкретному об'єкті), цей метод може
бути тільки абстрактний
Чи є у всіх "літератур" щось спільне(поле)? Так, це назва. Відповідно, її
бажано закласти на рівень абстракції
 */