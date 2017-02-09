package base;

public class Principal {
    public static void main(String[] args) {
        Contenedor miEL=new Contenedor(new Integer[2000]);

        Escritor miEscritor=new Escritor(miEL);
        Lector miLector=new Lector(miEL);

        Thread hiloEscritor=new Thread(miEscritor);
        Thread hiloLector=new Thread(miLector);

        hiloEscritor.start();
        hiloLector.start();
    }
}
