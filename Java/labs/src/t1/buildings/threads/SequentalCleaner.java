package t1.buildings.threads;

import t1.buildings.interfaces.Floor;

public class SequentalCleaner implements Runnable{
    private Floor floor;
    private CleanRepairSemaphore semaphore;
    public SequentalCleaner (CleanRepairSemaphore semaphore, Floor floor){
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {

            for (int i=0; i < this.floor.getTotalNumberOfSpaces(); i++){
                this.semaphore.clean_acquire();
                System.out.printf("\n SquentalCleaner space number %d with total area %f square meters", i, this.floor.getSpace(i).getSquare());
                Thread.sleep(1);
                this.semaphore.repair_release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
