import java.util.Random;

public class Consumidor implements Runnable{

    private static Random gerador = new Random();
    private Buffer localizacaoCompartilhada;

    public Consumidor(Buffer localizacaoCompartilhada) {
        this.localizacaoCompartilhada = localizacaoCompartilhada;
    }

    public void run() {
        int soma = 0;
        for(int i=1; i<=10; i++){
            try {
                Thread.sleep(gerador.nextInt(3000));
                soma += localizacaoCompartilhada.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fim do consumidor");
        System.out.println("Valor da soma: " + soma);
    }
}
