package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(
			ProductoRepository productoRepo,
			MarcaRepository marcaRepo,
			ConfigurableApplicationContext context
	) {
		return (args) -> {
			// Crear marcas
			Marca apple = new Marca("Apple");
			Marca samsung = new Marca("Samsung");
			Marca lenovo = new Marca("Lenovo");
			Marca sony = new Marca("Sony");
			marcaRepo.save(apple);
			marcaRepo.save(samsung);
			marcaRepo.save(lenovo);
			marcaRepo.save(sony);

			// Crear productos asociados a cada marca
			productoRepo.save(new Producto("iPhone 15", "Smartphone de última generación", 25000.00, apple));
			productoRepo.save(new Producto("iPad Pro", "Tablet profesional", 30000.00, apple));

			productoRepo.save(new Producto("Galaxy S23", "Smartphone Samsung gama alta", 22000.00, samsung));
			productoRepo.save(new Producto("Smart TV", "Televisión inteligente 55\"", 15000.00, samsung));

			productoRepo.save(new Producto("Laptop Lenovo IdeaPad", "Portátil de 15 pulgadas", 12500.00, lenovo));
			productoRepo.save(new Producto("Tablet Lenovo M10", "Pantalla 10 pulgadas", 7800.00, lenovo));

			productoRepo.save(new Producto("Sony Xperia 5 V", "Smartphone Sony de gama media", 18000.00, sony));
			productoRepo.save(new Producto("Audífonos Sony WH-1000XM5", "Audífonos con cancelación de ruido", 8500.00, sony));

			// Mostrar productos agrupados por marca
			System.out.println("📚 Productos por marca:");
			marcaRepo.findAll().forEach(marca -> {
				System.out.println("🏷️ " + marca.getNombre() + ":");
				productoRepo.findAll().stream()
						.filter(p -> p.getMarca() != null && p.getMarca().getId().equals(marca.getId()))
						.forEach(p -> System.out.println("   - " + p.getNombre()));
			});

			SpringApplication.exit(context, () -> 0);
		};
	}

}
