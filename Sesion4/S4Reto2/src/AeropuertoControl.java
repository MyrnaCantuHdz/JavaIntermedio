import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {

    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        // Mensajes de inicio de verificación
        System.out.println("🛣️ Verificando pista...");
        System.out.println("🌦️ Verificando clima...");
        System.out.println("🚦 Verificando tráfico aéreo...");
        System.out.println("👷‍♂️ Verificando personal en tierra...");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> resultadoFinal = CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenApply(v -> pista.join() && clima.join() && trafico.join() && personal.join())
                .thenAccept(todasCondiciones -> {
                    if (todasCondiciones) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n🚨 Error durante la verificación: " + ex.getMessage());
                    return null;
                });

        resultadoFinal.join(); // Esperar a que todo termine
    }

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            dormirAleatorio(2, 3);
            boolean disponible = probabilidad(0.80);
            System.out.println("🛣️ Pista disponible: " + disponible);
            return disponible;
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            dormirAleatorio(2, 3);
            boolean favorable = probabilidad(0.85);
            System.out.println("🌦️ Clima favorable: " + favorable);
            return favorable;
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            dormirAleatorio(2, 3);
            boolean despejado = probabilidad(0.90);
            System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
            return despejado;
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            dormirAleatorio(2, 3);
            boolean disponible = probabilidad(0.95);
            System.out.println("👷‍♂️ Personal disponible: " + disponible);
            return disponible;
        });
    }

    public static void dormirAleatorio(int min, int max) {
        try {
            int segundos = ThreadLocalRandom.current().nextInt(min, max + 1);
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean probabilidad(double chance) {
        return ThreadLocalRandom.current().nextDouble() < chance;
    }
}
