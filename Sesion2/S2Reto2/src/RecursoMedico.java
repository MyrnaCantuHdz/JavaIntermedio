import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println("🏥 " + profesional + " intentando ingresar a " + nombre + "...");

        lock.lock();
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(2000); // Simula el tiempo de uso del recurso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("⚠️ " + profesional + " fue interrumpido.");
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // 🔓 Siempre liberar el lock
        }
    }
}

