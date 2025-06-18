import java.util.concurrent.*;

public class MovilidadApp {

    public static void main(String[] args) {
        System.out.println("🚦 Iniciando simulación de viaje...\n");
        System.out.println("🚦 Calculando ruta...");
        System.out.println("💰 Estimando tarifa...");

        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        // Combinar ambos resultados asincrónicos
        CompletableFuture<String> resultadoFinal = rutaFuture
                .thenCombine(tarifaFuture, (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
                )
                .exceptionally(ex -> "🚨 Error al procesar la solicitud: " + ex.getMessage());

        // Esperar y mostrar el resultado
        System.out.println(resultadoFinal.join());
    }

    // Simula el cálculo de la ruta con latencia
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            dormir(3); // 2-3 segundos de latencia simulada
            // Simulación de posible error (descomenta para probar):
            // if (true) throw new RuntimeException("No se pudo calcular la ruta.");
            return "Centro -> Norte";
        });
    }

    // Simula la estimación de la tarifa con latencia
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            dormir(2); // 1-2 segundos de latencia simulada
            return 75.50;
        });
    }

    // Método auxiliar para simular latencia
    public static void dormir(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
