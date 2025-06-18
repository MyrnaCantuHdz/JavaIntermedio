import misionespacial.*;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> vida = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> com = executor.submit(new SistemaComunicaciones());

        // Mostrar resultados (el orden puede variar)
        System.out.println(com.get());
        System.out.println(vida.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        executor.shutdown();

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }
}
