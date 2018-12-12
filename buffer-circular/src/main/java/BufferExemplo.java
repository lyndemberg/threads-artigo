import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferExemplo implements Buffer {

    private Lock lockDeAcesso = new ReentrantLock();
    private Condition podeGravar = lockDeAcesso.newCondition();
    private Condition podeLer = lockDeAcesso.newCondition();
    private int[] buffer = {-1,-1,-1};
    private int buffersOcupados = 0;
    private int gravarIndice = 0;
    private int lerIndice = 0;

    public void set(int valor) {
        lockDeAcesso.lock();
        try {
            while(buffersOcupados == buffer.length){
                System.out.println("Todos os buffers cheios. Produtor aguarda!");
                podeGravar.await();
            }
            buffer[gravarIndice] = valor;
            gravarIndice = (gravarIndice + 1) % buffer.length;
            buffersOcupados ++;
            mostrarEstado("Produtor grava: " + buffer);
            podeLer.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lockDeAcesso.unlock();
        }
    }

    public int get() {
        int valorLido = 0;
        lockDeAcesso.lock();
        try{
            while(buffersOcupados == 0){
                System.out.println("Todos os buffers estão vazios. Consumidor aguarda!");
                podeLer.await();
            }
            valorLido = buffer[lerIndice];
            lerIndice = (lerIndice+1) % buffer.length;
            buffersOcupados--;
            mostrarEstado("Consumidor lê: " + valorLido);
            podeGravar.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lockDeAcesso.unlock();
        }

        System.out.println("Consumidor lê: " + buffer);
        return valorLido;
    }

    public void mostrarEstado(String operacao){
        System.out.println(operacao + "-" + "Buffers ocupados: " + "-" + buffersOcupados);
    }
}
