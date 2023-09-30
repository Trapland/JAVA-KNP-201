package step.learning.basics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class FilesDemo {

    public void run1(){
        //зберігання даних у файлах
        String filename = "test.txt";
        //Всі види роботи з даними у файлі - через Stream, особливість усіх stream
        // це некеровані ресурси, їх треба закривати окремими командами або вживати
        // блоки з автоматичним вивільненням ресурсів(using-C#, try()-Java)
        try(OutputStream writer = new FileOutputStream(filename)) {
            writer.write("Hello, world!".getBytes());
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter(filename, true))
        {
            writer.write("\nNew Line");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();

        try (InputStream reader = new FileInputStream( filename ))
        {
            int c ;
            while ( (c = reader.read()) != -1)
            {
                sb.append((char) c);
            }
            System.out.println(sb);
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }

        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream(4096);
        byte[] buf = new byte[512];
        try( InputStream reader = new BufferedInputStream(
                new FileInputStream( filename ))){
            int cnt ;
            while( (cnt = reader.read(buf)) > 0)
            {
                byteBuilder.write(buf, 0, cnt);
            }
            String content = new String(
                    byteBuilder.toByteArray(),
                    StandardCharsets.UTF_16
            ) ;
            System.out.println(content);
        }

        catch (IOException e) {
            System.err.println(e.getMessage());;
        }
        System.out.println("----------------------------------------------------------------------");
        try(InputStream reader = new FileInputStream(filename);
            Scanner scanner = new Scanner( reader )){
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        Scanner kbScanner = new Scanner(System.in);
        System.out.print("Your name: ");
        //String name = kbScanner.next();
        //System.out.printf("Hello, %s!%n",name);
        System.out.println();
        Random random = new Random();

        StringBuilder random_sb = new StringBuilder();
        for (int i = 0; i < random.nextInt(20); i++) {
            char c = (char)(random.nextInt(107) + 20);
            random_sb.append(c);
        }
        System.out.println(random_sb);
    }

    public void run2(){
        //створення фалйів та папок
        File dir = new File("./uploads");
        if(dir.exists())
        {
            if(dir.isDirectory()){
                System.out.printf("Directory '%s' already exists%n",dir.getName());
            }
            else {
                System.out.printf("'%s' already exists BUT NOT DIRECTORY%n", dir.getName());
            }
        }
        else{
            if(dir.mkdir()){
                System.out.printf("Directory '%s' created%n", dir.getName());
            }
            else {
                System.out.printf("Directory '%s' creation error%n", dir.getName());
            }
        }
        //створити у директорії файл
        File file = new File("./uploads/whitelist.txt");
        if (file.exists()){
            if(file.isFile()){
                System.out.printf("File '%s' already exists%n",file.getName());
            }
            else {
                System.out.printf("'%s' already exists BUT NOT DIRECTORY%n", file.getName());
            }
        }
        else{
            try {
                if(file.createNewFile()){
                    System.out.printf("File '%s' created%n", file.getName());

                }
                else{
                    System.out.printf("File '%s' creation error%n", file.getName());

                }
            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }
    public void run(){
        // Основа роботи з файлами - java.io.File
        File dir = new File("./");
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd.MM.yyyy");
        // створення new File НЕ впливає на файлову систему, це лише
        // програмний об'єкт, який відповідає за зазначений шлях
//        if(dir.exists()){
//            System.out.println("Path exists");
//        }
//        else {
//            System.out.println("Path does not exist");
//        }
//        System.out.printf("Path is %s %n", dir.isDirectory() ? "directory" : "file");
//        System.out.println(dir.getAbsolutePath());
        System.out.println("LastWriteTime       Type   Length Name");
        for ( File file : Objects.requireNonNull(dir.listFiles())) { // dir.list() - лише імена (String)
            System.out.print(DateFormat.format(new Date(file.lastModified())) + "         ");
            if(file.isDirectory())
                System.out.print("<DIR>   ");
            else
                System.out.print("<FILE>  ");
            if(file.length() != 0)
                System.out.print(file.length() + "  ");
            else
                System.out.print("     ");
            System.out.println(file.getName());       // dir.listFiles() - об'єкти (File)
        }
    }
}
/* Робота з файлами розглядається у двох аспектах:
    - робота з файловою системою: створення файлів, пошук, переміщення, видалення, тощо
    - використання файлів для збереження/відновлення даних


 */