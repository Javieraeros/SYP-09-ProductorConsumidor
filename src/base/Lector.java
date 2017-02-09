package base;

/**
 * Created by fjruiz on 9/02/17.
 */
public class Lector implements Runnable {
    private Contenedor el;

    public Lector(Contenedor el){
        this.el=el;
    }

    @Override
    public void run() {
        for(int i=0;i<el.length();i++) {
            el.lee(i);
        }
    }
}
