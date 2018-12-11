import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BufferExemploTeste {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        BufferExemplo localizacaoCompartilhada = new BufferExemplo();

        try{
            executor.execute(new Produtor(localizacaoCompartilhada));
            executor.execute(new Consumidor(localizacaoCompartilhada));
        }catch(Exception e){
            e.printStackTrace();
        }

        executor.shutdown();

    }
}
