import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferSync implements Buffer {

    private Lock lockDeAcesso = new ReentrantLock();
    private Condition podeGravar = lockDeAcesso.newCondition();
    private Condition podeLer = lockDeAcesso.newCondition();
    private int buffer = -1;
    private boolean ocupado = false;

    public void set(int valor) {
        lockDeAcesso.lock();
        try {
            while(ocupado){
                System.out.println("Produtor tenta gravar");
                mostrarEstado("Buffer vazio. Consumidor aguarda");
                podeGravar.await();
            }
            buffer = valor;
            ocupado = true;
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
            while(!ocupado){
                System.out.println("Consumidor tenta lê");
                mostrarEstado("Buffer vazio. Consumidor aguarda");
                podeLer.await();
            }
            ocupado = false;
            valorLido = buffer;
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
        System.out.println(operacao + "-" + buffer + "-" + ocupado);
    }
}
