import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("Juan", "domicilio", "555-1234"),
                new Pedido("María", "local", null),
                new Pedido("Carlos", "domicilio", null),
                new Pedido("Luisa", "domicilio", "555-5678"),
                new Pedido("Diana", "domicilio", null),
                new Pedido("Sofía", "domicilio", "555-3456")
        );

        System.out.println("📋 Confirmaciones de pedidos:");

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // Solo entrega a domicilio
                .map(Pedido::getTelefono)                                      // Optional<String>
                .filter(Optional::isPresent)                                   // Solo con teléfono
                .map(Optional::get)                                            // Obtener valor
                .map(tel -> "📞 Confirmación enviada al número: " + tel)       // Crear mensaje
                .forEach(System.out::println);                                 // Mostrar
    }
}
