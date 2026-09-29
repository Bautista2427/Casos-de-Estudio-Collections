package org.Escenario2;

class Producto {

    private int codigo;
    private String nombre;
    private double precio;
    private String categoria;

    public Producto(int codigo, String nombre,
                    double precio, String categoria) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {

        return "Código: " + codigo
                + " | Nombre: " + nombre
                + " | Precio: $" + precio
                + " | Categoría: " + categoria;
    }
}
