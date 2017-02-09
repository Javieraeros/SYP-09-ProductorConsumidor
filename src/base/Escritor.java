package base;

/**
 * Created by fjruiz on 9/02/17.
 */
public class Escritor implements Runnable {
    private Contenedor el;

    public Escritor(Contenedor el) {
        this.el = el;
    }

    @Override
    public void run() {
        for(int i=0;i<el.length();i++) {
            el.escribe(i);
        }
    }
}
