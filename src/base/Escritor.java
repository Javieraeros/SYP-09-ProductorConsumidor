package base;

/**
 * Created by fjruiz on 9/02/17.
 */
public class Escritor implements Runnable {
    private EscritorLector el;

    public Escritor(EscritorLector el) {
        this.el = el;
    }

    @Override
    public void run() {
        for(int i=0;i<el.length();i++) {
            el.escribe(i);
        }
    }
}
