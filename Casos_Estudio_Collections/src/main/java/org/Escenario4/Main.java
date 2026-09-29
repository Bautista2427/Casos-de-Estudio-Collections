package org.Escenario4;

public class Main {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("     PRUEBAS DE RENDIMIENTO");
        System.out.println("============================================");

        ejecutarPrueba(100);
        ejecutarPrueba(1000);
        ejecutarPrueba(10000);
        ejecutarPrueba(100000);
    }

    // ==========================================================
    // EJECUTAR PRUEBA
    // ==========================================================

    public static void ejecutarPrueba(int cantidadProductos) {

        System.out.println();
        System.out.println("============================================");
        System.out.println("PRODUCTOS: " + cantidadProductos);
        System.out.println("============================================");

        CatalogoProductos catalogo = new CatalogoProductos();

        // ------------------------------------------------------
        // MEDICIÓN DE MEMORIA INICIAL
        // ------------------------------------------------------

        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        long memoriaInicial =
                runtime.totalMemory() - runtime.freeMemory();

        // ------------------------------------------------------
        // MEDICIÓN DEL TIEMPO DE INSERCIÓN
        // ------------------------------------------------------

        long inicioInsercion = System.nanoTime();

        for (int i = 1; i <= cantidadProductos; i++) {

            Producto producto = new Producto(
                    i,
                    "Producto " + i,
                    10000 + (i % 1000),
                    "Categoria " + (i % 5)
            );

            catalogo.agregarProducto(producto);
        }

        long finInsercion = System.nanoTime();

        long tiempoInsercion = finInsercion - inicioInsercion;

        // ------------------------------------------------------
        // MEDICIÓN DE MEMORIA FINAL
        // ------------------------------------------------------

        long memoriaFinal =
                runtime.totalMemory() - runtime.freeMemory();

        long memoriaUtilizada =
                memoriaFinal - memoriaInicial;

        // ------------------------------------------------------
        // MOSTRAR RESULTADOS DE INSERCIÓN
        // ------------------------------------------------------

        System.out.println();
        System.out.println("============================================");
        System.out.println("        RESULTADOS DE INSERCIÓN");
        System.out.println("============================================");

        System.out.println(
                "Productos registrados: " +
                        catalogo.cantidadProductos()
        );

        System.out.println(
                "Tiempo de inserción: " +
                        tiempoInsercion +
                        " ns"
        );

        System.out.println(
                "Tiempo de inserción: " +
                        (tiempoInsercion / 1_000_000.0) +
                        " ms"
        );

        // ------------------------------------------------------
        // RESULTADOS DE MEMORIA
        // ------------------------------------------------------

        System.out.println();
        System.out.println("============================================");
        System.out.println("        RESULTADOS DE MEMORIA");
        System.out.println("============================================");

        System.out.println(
                "Memoria utilizada aproximadamente: " +
                        memoriaUtilizada +
                        " bytes"
        );

        System.out.println(
                "Memoria utilizada aproximadamente: " +
                        (memoriaUtilizada / 1024.0) +
                        " KB"
        );

        // ------------------------------------------------------
        // BÚSQUEDA POR CÓDIGO
        // ------------------------------------------------------

        int codigoBuscado = cantidadProductos / 2;

        long inicioBusqueda = System.nanoTime();

        Producto resultado =
                catalogo.buscarPorCodigo(codigoBuscado);

        long finBusqueda = System.nanoTime();

        long tiempoBusqueda =
                finBusqueda - inicioBusqueda;

        // ------------------------------------------------------
        // RESULTADOS DE BÚSQUEDA
        // ------------------------------------------------------

        System.out.println();
        System.out.println("============================================");
        System.out.println("        RESULTADOS DE BÚSQUEDA");
        System.out.println("============================================");

        System.out.println(
                "Código buscado: " +
                        codigoBuscado
        );

        if (resultado != null) {

            System.out.println(
                    "Producto encontrado: " +
                            resultado.getNombre()
            );

        } else {

            System.out.println(
                    "Producto no encontrado"
            );
        }

        System.out.println(
                "Tiempo de búsqueda: " +
                        tiempoBusqueda +
                        " ns"
        );

        System.out.println(
                "Tiempo de búsqueda: " +
                        (tiempoBusqueda / 1_000_000.0) +
                        " ms"
        );
    }
}
