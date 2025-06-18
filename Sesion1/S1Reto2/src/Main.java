import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Video> videos = List.of(
                new Video("Introducción a Java", "Mario", 15),
                new Video("POO en Java", "Carlos", 20)
        );

        List<Articulo> articulos = List.of(
                new Articulo("Historia de Java", "Ana", 1200),
                new Articulo("Tipos de datos", "Luis", 800)
        );

        List<Ejercicio> ejercicios = List.of(
                new Ejercicio("Variables y tipos", "Luis"),
                new Ejercicio("Condicionales", "Mario")
        );

        // ✅ Mostrar todos los materiales
        PlataformaEducativa.mostrarMateriales(videos);
        PlataformaEducativa.mostrarMateriales(articulos);
        PlataformaEducativa.mostrarMateriales(ejercicios);

        // ✅ Contar duración de videos
        PlataformaEducativa.contarDuracionVideos(videos);

        // ✅ Marcar ejercicios como revisados
        PlataformaEducativa.marcarEjerciciosRevisados(ejercicios);

        // ✅ Filtrar por autor
        List<MaterialCurso> todos = List.of(
                videos.get(0), videos.get(1),
                articulos.get(0), articulos.get(1),
                ejercicios.get(0), ejercicios.get(1)
        );
        PlataformaEducativa.filtrarPorAutor(todos, m -> m.getAutor().equals("Mario"));
    }
}
