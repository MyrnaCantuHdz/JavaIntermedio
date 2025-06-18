import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrimeSistema {

    private static final AtomicInteger semaforoRojoConsecutivo = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        // 🚗 Sensores de tráfico (nivel 0–100%)
        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> ThreadLocalRandom.current().nextInt(30, 101))
                .filter(congestion -> congestion > 70)
                .map(congestion -> "🚗 Alerta: Congestión del " + congestion + "% en Avenida Solar")
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.parallel());

        // 🌫️ Contaminación del aire (PM2.5)
        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> ThreadLocalRandom.current().nextInt(20, 80))
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)")
                .subscribeOn(Schedulers.parallel());

        // 🚑 Accidentes viales (Prioridad: Baja, Media, Alta)
        List<String> prioridades = Arrays.asList("Baja", "Media", "Alta");
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades.get(ThreadLocalRandom.current().nextInt(3)))
                .filter(prioridad -> prioridad.equals("Alta"))
                .map(p -> "🚑 Emergencia vial: Accidente con prioridad Alta")
                .subscribeOn(Schedulers.parallel());

        // 🚝 Trenes maglev (retraso en minutos)
        Flux<String> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 11))
                .filter(min -> min > 5)
                .map(min -> "🚝 Tren maglev con retraso crítico: " + min + " minutos")
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.parallel());

        // 🚦 Semáforos inteligentes
        List<String> estados = Arrays.asList("Verde", "Amarillo", "Rojo");
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> estados.get(ThreadLocalRandom.current().nextInt(3)))
                .filter(estado -> {
                    if (estado.equals("Rojo")) {
                        return semaforoRojoConsecutivo.incrementAndGet() >= 3;
                    } else {
                        semaforoRojoConsecutivo.set(0);
                        return false;
                    }
                })
                .map(e -> "🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte")
                .subscribeOn(Schedulers.parallel());

        // 🔔 Unir todos los eventos críticos y verificar si ocurren simultáneamente
        Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .buffer(Duration.ofSeconds(1)) // Agrupa eventos por segundo
                .filter(lista -> lista.size() >= 3)
                .map(lista -> {
                    lista.forEach(System.out::println);
                    return "🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime";
                })
                .subscribe(System.out::println);

        // Mantener el programa corriendo por 15 segundos
        Thread.sleep(15000);
    }
}

