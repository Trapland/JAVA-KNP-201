package step.learning;
import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.async.AsyncDemo;
import step.learning.async.TaskDemo;
import step.learning.ioc.ConfigModule;
import step.learning.ioc.IocDemo;
import javax.inject.Inject;
import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
import step.learning.oop.OopDemo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //new BasicsDemo().run();
        //new FilesDemo().run();
        new OopDemo().run();
//        Injector injector = Guice.createInjector(new ConfigModule());
//        IocDemo iocDemo = injector.getInstance(IocDemo.class);
//        iocDemo.run();
        //Guice.createInjector(new ConfigModule()).getInstance(AsyncDemo.class).run();
        //Guice.createInjector(new ConfigModule()).getInstance(TaskDemo.class).run();
    }
}

/*
Вступ.
Встановити:
1. JRE (Java Runtime Environment - аналог .NET ß платформа виконання)
https://www.oracle.com/java/technologies/downloads/
2. JDK (Java Developer Kit - набір розробника - компілятор + бібліотеки)
    за тим самим посиланням або через інструменти IDE
3. IDE (JetBrains Idea/ NetBeans (Apache) / Eclipse / VS Code)

Новий проект.
1. Архетип (система збірки / управління пакетами - NuGet)
    Maven / Gradle / Ant / Idea
    Назва проекту - довільна
    Архетип - quickstart
    ! розкрити додаткові налаштування, вписати групу "step.learning"
    Вибрати JDK, за відсутності - завантажити
2. Конфігурація запуску - після створення проєкту маємо лише розпакований
    шаблон, потрібна конфігурація.
        меню Run - Edit Configuration - Add new - Application
        Вводимо назву конфігурації(довільна, App)
        Та вибираємо головний клас (з методом main)
3. Пробний запуск

Про Java
Парадигма - ООП
Поколiння - 4
Запуск - трансльований, на базі платформи
Вихідний код - Юнікод, файли.java
Виконавчий код - проміжний, файли.class
Висока чутливість до організації:
 - назва файлу збігається з назвою класу (реєстрочутливою) ->
    один файл - один клас (public)
 - назва пакету відповідає назві директорії
 */