package org.Escenario4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CatalogoProductos {

    // Búsqueda rápida por código
    private HashMap<Integer, Producto> productosPorCodigo;

    // Productos agrupados y ordenados por precio
    private TreeMap<Double, ArrayList<Producto>> productosPorPrecio;

    // Almacenamiento general de productos
    private ArrayList<Producto> productos;

    public CatalogoProductos() {

        productosPorCodigo = new HashMap<>();
        productosPorPrecio = new TreeMap<>();
        productos = new ArrayList<>();
    }

    // ==========================================================
    // INSERTAR PRODUCTO
    // ==========================================================

    public boolean agregarProducto(Producto producto) {

        // Evitar productos con códigos repetidos
        if (productosPorCodigo.containsKey(producto.getCodigo())) {
            return false;
        }

        // 1. Guardar para búsqueda rápida por código
        productosPorCodigo.put(producto.getCodigo(), producto);

        // 2. Guardar en la lista general
        productos.add(producto);

        // 3. Guardar agrupado por precio
        productosPorPrecio
                .computeIfAbsent(producto.getPrecio(), k -> new ArrayList<>())
                .add(producto);

        return true;
    }

    // ==========================================================
    // BUSCAR POR CÓDIGO
    // ==========================================================

    public Producto buscarPorCodigo(int codigo) {

        return productosPorCodigo.get(codigo);
    }

    // ==========================================================
    // MOSTRAR PRODUCTOS ORDENADOS POR PRECIO
    // ==========================================================

    public void mostrarProductosOrdenadosPorPrecio() {

        Iterator<Map.Entry<Double, ArrayList<Producto>>> iterator =
                productosPorPrecio.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Double, ArrayList<Producto>> entrada =
                    iterator.next();

            double precio = entrada.getKey();

            ArrayList<Producto> productosMismoPrecio =
                    entrada.getValue();

            Iterator<Producto> productoIterator =
                    productosMismoPrecio.iterator();

            while (productoIterator.hasNext()) {

                Producto producto = productoIterator.next();

                System.out.println(
                        "Código: " + producto.getCodigo() +
                                " | Nombre: " + producto.getNombre() +
                                " | Precio: $" + precio +
                                " | Categoría: " + producto.getCategoria()
                );
            }
        }
    }

    // ==========================================================
    // CANTIDAD DE PRODUCTOS
    // ==========================================================

    public int cantidadProductos() {
        return productos.size();
    }
}