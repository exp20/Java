import t1.buildings.interfaces.Floor;
import t1.buildings.office.OfficeFloor;
import t1.buildings.threads.*;

public class Test3 {
    public static void main(String... args){
        Floor floor = new OfficeFloor(1000);
        Repairer rep1 = new Repairer(floor);
        Cleaner cleaner1 = new Cleaner(floor);
/*
        try {
            rep1.start();
            Thread.sleep(40);
            rep1.interrupt();

            cleaner1.start();
            Thread.sleep(40);
            cleaner1.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

*/
        CleanRepairSemaphore sem = new CleanRepairSemaphore();
        Runnable seq_rep = new SequentalRepairer(sem,floor);
        Runnable seq_cle = new SequentalCleaner(sem,floor);

        Thread cl = new Thread(seq_cle);
        Thread rp = new Thread(seq_rep);

        cl.start();
        rp.start();



    }
}
