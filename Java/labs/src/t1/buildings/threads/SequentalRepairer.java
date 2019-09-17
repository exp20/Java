package t1.buildings.threads;

import t1.buildings.interfaces.Floor;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    private CleanRepairSemaphore semaphore;

    public SequentalRepairer (CleanRepairSemaphore semaphore, Floor floor){
        this.floor = floor;
        this.semaphore=semaphore;
    }

    @Override
    public void run() {
        try {
            for (int i=0; i < this.floor.getTotalNumberOfSpaces(); i++){
                this.semaphore.repair_acquire();
                System.out.printf("\n SquentalRepairing space number %d with total area %f square meters", i, this.floor.getSpace(i).getSquare());
                Thread.sleep(1);
                this.semaphore.clean_release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
