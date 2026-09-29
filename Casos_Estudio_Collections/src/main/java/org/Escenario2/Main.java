package org.Escenario2;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] cantidades = {
                100,
                1000,
                10000,
                100000
        };


        System.out.println(
                "============================================"
        );

        System.out.println(
                "     PRUEBAS DE RENDIMIENTO"
        );

        System.out.println(
                "============================================"
        );


        for (int cantidad : cantidades) {

            realizarPrueba(cantidad);
        }
    }


    // ==================================================
    // REALIZAR PRUEBA
    // ==================================================

    public static void realizarPrueba(int cantidad) {

        System.out.println(
                "\n============================================"
        );

        System.out.println(
                "PRODUCTOS: " + cantidad
        );

        System.out.println(
                "============================================"
        );


        // ------------------------------------------
        // CREAR SISTEMA
        // ------------------------------------------

        PlataformaVentas plataforma =
                new PlataformaVentas();


        Random random = new Random(12345);


        String[] categorias = {
                "Celulares",
                "Computadores",
                "Accesorios",
                "Audio",
                "Tecnologia"
        };


        // ==========================================
        // MEDICIÓN DE MEMORIA INICIAL
        // ==========================================

        System.gc();

        long memoriaInicial =
                Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory();


        // ==========================================
        // MEDICIÓN DE INSERCIÓN
        // ==========================================

        long inicioInsercion =
                System.nanoTime();


        for (int i = 1; i <= cantidad; i++) {

            int codigo = i;

            String nombre = "Producto " + i;

            double precio =
                    10000 + random.nextInt(500000);

            String categoria =
                    categorias[random.nextInt(
                            categorias.length
                    )];


            Producto producto =
                    new Producto(
                            codigo,
                            nombre,
                            precio,
                            categoria
                    );


            plataforma.agregarProducto(producto);
        }


        long finInsercion =
                System.nanoTime();


        long tiempoInsercion =
                finInsercion - inicioInsercion;


        // ==========================================
        // MEDICIÓN DE BÚSQUEDA
        // ==========================================

        int codigoBuscar = cantidad / 2;


        long inicioBusqueda =
                System.nanoTime();


        Producto resultado =
                plataforma.buscarPorCodigo(codigoBuscar);


        long finBusqueda =
                System.nanoTime();


        long tiempoBusqueda =
                finBusqueda - inicioBusqueda;


        // ==========================================
        // MEDICIÓN DE ORDENAMIENTO / RECORRIDO
        // ==========================================

        long inicioPrecio =
                System.nanoTime();


        long productosRecorridos =
                plataforma.recorrerPorPrecio();


        long finPrecio =
                System.nanoTime();


        long tiempoPrecio =
                finPrecio - inicioPrecio;


        // ==========================================
        // MEDICIÓN DE FILTRADO
        // ==========================================

        long inicioFiltro =
                System.nanoTime();


        ArrayList<Producto> filtrados =
                plataforma.filtrarPorCategoria(
                        "Celulares"
                );


        int cantidadFiltrados = 0;

        if (filtrados != null) {

            cantidadFiltrados =
                    filtrados.size();
        }


        long finFiltro =
                System.nanoTime();


        long tiempoFiltro =
                finFiltro - inicioFiltro;


        // ==========================================
        // MEMORIA FINAL
        // ==========================================

        long memoriaFinal =
                Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory();


        long memoriaUtilizada =
                memoriaFinal - memoriaInicial;


        // ==========================================
        // RESULTADOS
        // ==========================================

        System.out.println(
                "\n--- INSERCIÓN ---"
        );

        System.out.println(
                "Productos registrados: "
                        + plataforma.cantidadProductos()
        );

        System.out.println(
                "Tiempo: "
                        + tiempoInsercion
                        + " ns"
        );

        System.out.println(
                "Tiempo: "
                        + (tiempoInsercion / 1_000_000.0)
                        + " ms"
        );


        System.out.println(
                "\n--- BÚSQUEDA POR CÓDIGO ---"
        );

        System.out.println(
                "Código buscado: "
                        + codigoBuscar
        );

        System.out.println(
                "Resultado: "
                        + (resultado != null
                        ? resultado.getNombre()
                        : "No encontrado")
        );

        System.out.println(
                "Tiempo: "
                        + tiempoBusqueda
                        + " ns"
        );


        System.out.println(
                "\n--- PRODUCTOS POR PRECIO ---"
        );

        System.out.println(
                "Productos recorridos: "
                        + productosRecorridos
        );

        System.out.println(
                "Tiempo: "
                        + tiempoPrecio
                        + " ns"
        );

        System.out.println(
                "Tiempo: "
                        + (tiempoPrecio / 1_000_000.0)
                        + " ms"
        );


        System.out.println(
                "\n--- FILTRADO POR CATEGORÍA ---"
        );

        System.out.println(
                "Categoría: Celulares"
        );

        System.out.println(
                "Productos encontrados: "
                        + cantidadFiltrados
        );

        System.out.println(
                "Tiempo: "
                        + tiempoFiltro
                        + " ns"
        );


        System.out.println(
                "\n--- MEMORIA ---"
        );

        System.out.println(
                "Memoria utilizada aproximadamente: "
                        + memoriaUtilizada
                        + " bytes"
        );

        System.out.println(
                "Memoria utilizada aproximadamente: "
                        + (memoriaUtilizada / 1024.0)
                        + " KB"
        );
    }
}