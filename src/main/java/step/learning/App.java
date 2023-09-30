package step.learning;

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
        new FilesDemo().run();
        //new OopDemo().run();
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