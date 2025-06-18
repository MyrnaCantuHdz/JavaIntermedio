import java.util.List;
import java.util.stream.*;

public class SeguimientoEncuestas {

    public static void main(String[] args) {

        // 🏥 Lista de sucursales con encuestas
        List<Sucursal> sucursales = List.of(
                new Sucursal("Centro", List.of(
                        new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                        new Encuesta("Luis", null, 3),
                        new Encuesta("Karen", "Todo perfecto", 5)
                )),
                new Sucursal("Norte", List.of(
                        new Encuesta("Diego", "La atención fue buena, pero la limpieza puede mejorar", 3),
                        new Encuesta("Sandra", null, 4)
                )),
                new Sucursal("Sur", List.of(
                        new Encuesta("Pedro", null, 2),
                        new Encuesta("Lucía", "El doctor fue muy amable", 5)
                ))
        );

        System.out.println("📋 Seguimiento a encuestas de pacientes insatisfechos:");

        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3) // 🔍 Solo insatisfechos
                                .map(encuesta -> new Registro(encuesta, sucursal.getNombre()))
                )
                .filter(reg -> reg.encuesta.getComentario().isPresent()) // ✅ Con comentario
                .map(reg -> {
                    String comentario = reg.encuesta.getComentario().get();
                    return "Sucursal " + reg.sucursal +
                            ": Seguimiento a paciente con comentario: \"" + comentario + "\"";
                })
                .forEach(System.out::println); // 📤 Imprimir mensaje
    }

    // Clase interna para combinar encuesta con sucursal
    private static class Registro {
        private final Encuesta encuesta;
        private final String sucursal;

        public Registro(Encuesta encuesta, String sucursal) {
            this.encuesta = encuesta;
            this.sucursal = sucursal;
        }
    }
}

