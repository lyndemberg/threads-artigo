public class BufferExemplo implements Buffer {

    private int buffer = -1;

    public void set(int valor) {
        System.out.println("Produtor grava: " + valor);
        buffer = valor;
    }

    public int get() {
        System.out.println("Consumidor lê: " + buffer);
        return buffer;
    }
}
