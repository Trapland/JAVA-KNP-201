package step.learning.oop;

import java.util.Date;

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
}
