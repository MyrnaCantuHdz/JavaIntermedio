package com.bedu.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Min(value = 1, message = "El precio debe ser mayor o igual a 1")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    protected Producto() {}

    // Nuevo constructor que incluye Marca
    public Producto(String nombre, String descripcion, double precio, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public Marca getMarca() { return marca; }

    @Override
    public String toString() {
        return String.format(
                "Producto[id=%d, nombre='%s', precio=%.2f, marca='%s']",
                id, nombre, precio, marca != null ? marca.getNombre() : "Sin marca"
        );
    }
}
