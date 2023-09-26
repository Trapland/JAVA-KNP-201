package step.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class Library {
    private final List<Literature> funds;

    public Library(){
        funds = new LinkedList<>();
    }
    public void add(Literature literature){
        funds.add(literature);
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
}
