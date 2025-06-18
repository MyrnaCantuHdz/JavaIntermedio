import java.util.List;

public class ProcesadorOrdenes {

    // Lee cualquier tipo de orden y muestra su resumen
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Aplica un costo adicional a órdenes personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada personalizada) {
                personalizada.aplicarCostoAdicional(costoAdicional);
            }
        }
    }

    // Desafío adicional: contar por tipo
    public static void contarTipos(List<OrdenProduccion> lista) {
        int masa = 0, personalizadas = 0, prototipos = 0;

        for (OrdenProduccion orden : lista) {
            if (orden instanceof OrdenMasa) masa++;
            else if (orden instanceof OrdenPersonalizada) personalizadas++;
            else if (orden instanceof OrdenPrototipo) prototipos++;
        }

        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizadas);
        System.out.println("🧪 Prototipos: " + prototipos);
    }
}
