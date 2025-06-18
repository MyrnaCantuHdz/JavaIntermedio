import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

public class MonitoreoSignosVitales {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Crear flujos para 3 pacientes
        Flux<String> paciente1 = crearFlujoPaciente(1, random);
        Flux<String> paciente2 = crearFlujoPaciente(2, random);
        Flux<String> paciente3 = crearFlujoPaciente(3, random);

        // Fusionar los flujos para gestionar todos los eventos juntos
        Flux<String> flujoTotal = Flux.merge(paciente1, paciente2, paciente3);

        // Suscribirse e imprimir alertas críticas
        flujoTotal.subscribe(alerta -> System.out.println("⚠️ " + alerta));

        // Mantener la aplicación viva para que los flujos sigan emitiendo
        Thread.sleep(15000);
    }

    private static Flux<String> crearFlujoPaciente(int idPaciente, Random random) {
        return Flux.interval(Duration.ofMillis(300))
                .onBackpressureBuffer()
                .flatMap(tick -> {
                    String alerta = generarEventoPaciente(idPaciente, random);
                    if (alerta == null) {
                        return Mono.empty();  // No emitir nada si no hay alerta crítica
                    } else {
                        return Mono.just(alerta);
                    }
                })
                .delayElements(Duration.ofSeconds(1)); // Controla el ritmo de procesamiento (backpressure)
    }

    private static String generarEventoPaciente(int idPaciente, Random random) {
        int fc = 40 + random.nextInt(100);    // Frecuencia cardíaca: 40-139 bpm
        int paSist = 80 + random.nextInt(70); // Presión arterial sistólica: 80-149 mmHg
        int paDiast = 50 + random.nextInt(50);// Presión arterial diastólica: 50-99 mmHg
        int spo2 = 85 + random.nextInt(16);  // Saturación O2: 85-100%

        // Checar si hay alerta crítica en Frecuencia Cardíaca
        if (fc < 50 || fc > 120) {
            return "Paciente " + idPaciente + " - FC crítica: " + fc + " bpm";
        }
        // Checar si hay alerta crítica en Presión Arterial
        if (paSist < 90 || paDiast < 60 || paSist > 140 || paDiast > 90) {
            return "Paciente " + idPaciente + " - PA crítica: " + paSist + "/" + paDiast + " mmHg";
        }
        // Checar si hay alerta crítica en Saturación de Oxígeno
        if (spo2 < 90) {
            return "Paciente " + idPaciente + " - SpO2 baja: " + spo2 + "%";
        }

        // No hay alerta crítica, no emitir nada
        return null;
    }
}
