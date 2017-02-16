package semaforo;

import java.util.concurrent.Semaphore;

/**
 * Created by fjruiz on 16/02/17.
 */
public class Productor implements Runnable {
    private Semaphore semaphore;
    private MiArray array;

    public Productor(Semaphore semaphore, MiArray array) {
        this.semaphore = semaphore;
        this.array = array;
    }

    public void escribe(Integer numerito){
        try {
            Thread.sleep((long) (2000*Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        array.escribeArray(numerito);
        semaphore.release();
    }

    @Override
    public void run() {
        for(int i=0;i<array.getMiArray().length;i++) {
            escribe((int) (Math.random()*i*10));
        }
    }
}
