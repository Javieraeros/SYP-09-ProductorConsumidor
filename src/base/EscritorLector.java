package base;

/**
 * Created by fjruiz on 3/02/17.
 */
public class EscritorLector {
    private boolean avisoDespertar;
    private Integer[] cadena;

    public EscritorLector(Integer[] cadena){
        this.cadena=cadena;
        avisoDespertar=false;
    }
    public int length(){
        return cadena.length;
    }

    public synchronized void hazEsperar(){
        while (!avisoDespertar){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Han despertado al hilo lector");
        avisoDespertar=false;
    }

    public synchronized void despierta(){
        if(!avisoDespertar) {
            avisoDespertar = true;
            this.notify();
            System.out.println("Notificando");
        }
    }

    public void escribe(int index){
        cadena[index]=(int) (Math.random()*9);
        System.out.println("El hilo escritor ha escrito en el indice "+index+" con valor: "+cadena[index]);
        try {
            Thread.sleep((int) (Math.random()*2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            this.despierta();
    }

    public void lee(int index){
        avisoDespertar=false;
        while (cadena[index]==null){
            System.out.println("Siestecita caletera");
            this.hazEsperar();
        }
        if(this.cadena[index]==null){
            System.out.println("Soy nuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuulooooooooooooooooooooooooooooooooo");
        }else {
            System.out.println("Índice: "+index+" Valor:"+this.cadena[index]);
        }
    }
}
