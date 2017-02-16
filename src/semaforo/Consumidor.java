package semaforo;

import java.util.concurrent.Semaphore;

/**
 * Created by fjruiz on 16/02/17.
 */
public class Consumidor implements Runnable{
    private Semaphore semaphore;
    private MiArray array;

    public Consumidor(Semaphore semaphore, MiArray array) {
        this.semaphore = semaphore;
        this.array = array;
    }

    public void lee(){
        System.out.println(array.leeArray());
    }

    @Override
    public void run() {
        for(int i=0;i<array.getMiArray().length;i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lee();
        }
    }
}
