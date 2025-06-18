package misionespacial;

import java.util.concurrent.Callable;

public class SistemaSoporteVital implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1200); // Simula procesamiento
        return "🧪 Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}
