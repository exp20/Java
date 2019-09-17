package t1.buildings.threads;

import t1.buildings.interfaces.Floor;

public class Cleaner extends Thread {
    private Floor floor;
    public Cleaner (Floor floor){
        this.floor=floor;
    }

    public void run(){
        for (int i = 0; i < this.floor.getTotalNumberOfSpaces(); i++) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println(" Поток прерван");
                return;
            }
            else {
                System.out.printf("\n Cleaning space number %d with total area %f square meters", i, this.floor.getSpace(i).getSquare());
            }
        }
        System.out.println(" Поток "+Thread.currentThread().getName()+" закончил выполнение");
    }
}
