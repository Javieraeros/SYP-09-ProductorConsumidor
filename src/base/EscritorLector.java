package base;

/**
 * Created by fjruiz on 3/02/17.
 */
public class EscritorLector {
    private boolean avisoDespertar;
    private String cadena;

    public EscritorLector(String cadena){
        this.cadena=cadena;
        avisoDespertar=false;
    }

    public synchronized void hazEsperar(){
        while (!avisoDespertar){
            try {
                cadena.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        avisoDespertar=false;
    }

    public synchronized void despierta(){
        avisoDespertar=true;
        cadena.notify();
    }

    public void escribe(){
        for(int i=0;i<=10;i++){
            cadena+=""+i;
            this.despierta();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void lee(){
        this.hazEsperar();
        System.out.println(this.cadena);
    }
}
