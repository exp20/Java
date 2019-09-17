package t1.buildings.threads;

import java.util.concurrent.Semaphore;

public class CleanRepairSemaphore {
    private Semaphore repair_semaphore;
    private Semaphore clean_semaphore;
    public CleanRepairSemaphore(){
        this.repair_semaphore = new Semaphore(1);
        this.clean_semaphore = new Semaphore(0);
    }

     public void repair_acquire(){
        try {
            repair_semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

     public void clean_acquire(){
        try {
            clean_semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void repair_release(){
        repair_semaphore.release();
    }
    public void clean_release(){
        clean_semaphore.release();
    }
}
