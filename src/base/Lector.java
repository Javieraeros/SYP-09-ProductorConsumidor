package base;

/**
 * Created by fjruiz on 9/02/17.
 */
public class Lector implements Runnable {
    private EscritorLector el;

    public Lector(EscritorLector el){
        this.el=el;
    }

    @Override
    public void run() {
        for(int i=0;i<el.length();i++) {
            el.lee(i);
        }
    }
}
