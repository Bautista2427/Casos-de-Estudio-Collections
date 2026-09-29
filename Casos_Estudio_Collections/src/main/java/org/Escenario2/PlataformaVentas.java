package org.Escenario2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;


class PlataformaVentas {

    // Buscar por código
    private HashMap<Integer, Producto> productosPorCodigo;

    // Ordenar por precio
    private TreeMap<Double, ArrayList<Producto>> productosPorPrecio;

    // Insertar al inicio
    private LinkedList<Producto> productosPorLlegada;

    // Filtrar por categoría
    private HashMap<String, ArrayList<Producto>> productosPorCategoria;


    // Constructor
    public PlataformaVentas() {

        productosPorCodigo = new HashMap<>();

        productosPorPrecio = new TreeMap<>();

        productosPorLlegada = new LinkedList<>();

        productosPorCategoria = new HashMap<>();
    }


    // ==================================================
    // AGREGAR PRODUCTO
    // ==================================================

    public void agregarProducto(Producto producto) {

        int codigo = producto.getCodigo();

        // Evitar códigos duplicados
        if (productosPorCodigo.containsKey(codigo)) {
            return;
        }

        // -----------------------------
        // HASHMAP - Código
        // -----------------------------

        productosPorCodigo.put(codigo, producto);


        // -----------------------------
        // TREEMAP - Precio
        // -----------------------------

        double precio = producto.getPrecio();

        if (!productosPorPrecio.containsKey(precio)) {

            productosPorPrecio.put(
                    precio,
                    new ArrayList<>()
            );
        }

        productosPorPrecio.get(precio).add(producto);


        // -----------------------------
        // LINKEDLIST - Inicio
        // -----------------------------

        productosPorLlegada.addFirst(producto);


        // -----------------------------
        // HASHMAP - Categoría
        // -----------------------------

        String categoria = producto.getCategoria();

        if (!productosPorCategoria.containsKey(categoria)) {

            productosPorCategoria.put(
                    categoria,
                    new ArrayList<>()
            );
        }

        productosPorCategoria.get(categoria).add(producto);
    }


    // ==================================================
    // BUSCAR POR CÓDIGO
    // ==================================================

    public Producto buscarPorCodigo(int codigo) {

        return productosPorCodigo.get(codigo);
    }


    // ==================================================
    // RECORRER PRODUCTOS ORDENADOS POR PRECIO
    // USANDO ITERATOR
    // ==================================================

    public long recorrerPorPrecio() {

        long cantidad = 0;

        // Iterator para recorrer el TreeMap
        Iterator<Map.Entry<Double, ArrayList<Producto>>> iterator =
                productosPorPrecio.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<Double, ArrayList<Producto>> entrada =
                    iterator.next();

            ArrayList<Producto> lista = entrada.getValue();

            // Iterator para recorrer los productos
            Iterator<Producto> iteratorProducto =
                    lista.iterator();

            while (iteratorProducto.hasNext()) {

                iteratorProducto.next();

                cantidad++;
            }
        }

        return cantidad;
    }


    // ==================================================
    // FILTRAR POR CATEGORÍA
    // USANDO ITERATOR
    // ==================================================

    public ArrayList<Producto> filtrarPorCategoria(
            String categoria) {

        ArrayList<Producto> resultado =
                productosPorCategoria.get(categoria);

        if (resultado == null) {
            return new ArrayList<>();
        }

        // Creamos una nueva lista para almacenar
        // los productos encontrados
        ArrayList<Producto> productosFiltrados =
                new ArrayList<>();

        // Iterator para recorrer los productos
        Iterator<Producto> iterator =
                resultado.iterator();

        while (iterator.hasNext()) {

            Producto producto = iterator.next();

            productosFiltrados.add(producto);
        }

        return productosFiltrados;
    }


    // ==================================================
    // CANTIDAD DE PRODUCTOS
    // ==================================================

    public int cantidadProductos() {

        return productosPorCodigo.size();
    }
}