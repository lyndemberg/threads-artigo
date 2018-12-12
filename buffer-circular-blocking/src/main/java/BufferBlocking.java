import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferBlocking implements Buffer {

    private ArrayBlockingQueue<Integer> buffer;

    public BufferBlocking() {
        this.buffer = new ArrayBlockingQueue<>(3);
    }

    public void set(int valor) {
        try {
            buffer.put(valor);
            System.out.println("Produtor grava: " + valor + " -- " + "Buffers ocupados: " + buffer.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int get() {
        int readValue = 0;
        try {
            readValue = buffer.take();
            System.out.println("Consumidor lê: " + readValue +" -- " + "Buffers ocupados: " + buffer.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readValue;
    }

}
