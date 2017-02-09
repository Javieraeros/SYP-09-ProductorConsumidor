package base;

public class Principal {
    public static void main(String[] args) {
        EscritorLector miEL=new EscritorLector(new Integer[20]);

        Escritor miEscritor=new Escritor(miEL);
        Lector miLector=new Lector(miEL);

        Thread hiloEscritor=new Thread(miEscritor);
        Thread hiloLector=new Thread(miLector);

        hiloEscritor.start();
        hiloLector.start();
    }
}
