import java.util.Random;

public class Produtor implements Runnable{

    private static Random gerador = new Random();
    private Buffer localizacaoCompartilhada;

    public Produtor(Buffer localizacaoCompartilhada) {
        this.localizacaoCompartilhada = localizacaoCompartilhada;
    }

    public void run() {

        for(int i=1; i<=10; i++){
            try {
                Thread.sleep(gerador.nextInt(3000));
                localizacaoCompartilhada.set(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Produtor produz!!!!");
        System.out.println("Fim do produtor");
    }
}
