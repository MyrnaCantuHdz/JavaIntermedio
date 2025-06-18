import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando simulación de acceso al recurso...");
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        Runnable tarea1 = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable tarea2 = () -> salaCirugia.usar("Dr. Gómez");
        Runnable tarea3 = () -> salaCirugia.usar("Enfermera López");
        Runnable tarea4 = () -> salaCirugia.usar("Dr. Martínez");
        Runnable tarea5 = () -> salaCirugia.usar("Dra. Rivera");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(tarea1);
        executor.execute(tarea2);
        executor.execute(tarea3);
        executor.execute(tarea4);
        executor.execute(tarea5);

        executor.shutdown();
    }
}
