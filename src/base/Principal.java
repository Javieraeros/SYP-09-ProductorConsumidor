package base;

/**
 * Created by fjruiz on 3/02/17.
 */
public class Principal {
    //TODO hacer que escritorLector implemente runnable y esas cosas :D
    public static void main(String[] args) {
        EscritorLector miEL=new EscritorLector("prueba");
        miEL.escribe();
    }
}
