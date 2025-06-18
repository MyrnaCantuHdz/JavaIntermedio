import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Listas separadas
        List<OrdenMasa> listaMasa = new ArrayList<>();
        listaMasa.add(new OrdenMasa("A123", 500));
        listaMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> listaPersonalizadas = new ArrayList<>();
        listaPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        listaPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> listaPrototipos = new ArrayList<>();
        listaPrototipos.add(new OrdenPrototipo("T789", 10, "Diseño"));
        listaPrototipos.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar todas las órdenes
        System.out.println("📋 Órdenes registradas:");
        ProcesadorOrdenes.mostrarOrdenes(listaMasa);

        System.out.println("\n📋 Órdenes registradas:");
        ProcesadorOrdenes.mostrarOrdenes(listaPersonalizadas);

        System.out.println("\n📋 Órdenes registradas:");
        ProcesadorOrdenes.mostrarOrdenes(listaPrototipos);

        // Procesar personalizadas
        ProcesadorOrdenes.procesarPersonalizadas(new ArrayList<>(listaPersonalizadas), 200);

        // Desafío: contar todas
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(listaMasa);
        todas.addAll(listaPersonalizadas);
        todas.addAll(listaPrototipos);

        ProcesadorOrdenes.contarTipos(todas);
    }
}
