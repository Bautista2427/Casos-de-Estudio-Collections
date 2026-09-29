package org.Escenario1;

public class Main {

    public static void main(String[] args) {

        // =========================================================
        // CONFIGURACIÓN DE LA PRUEBA
        // =========================================================

        final int CANTIDAD_PACIENTES = 100000;

        System.out.println("============================================");
        System.out.println("   ESCENARIO 1 - SISTEMA HOSPITALARIO");
        System.out.println("============================================");
        System.out.println("Cantidad de pacientes: "
                + CANTIDAD_PACIENTES);


        // =========================================================
        // CREAR SISTEMA HOSPITALARIO
        // =========================================================

        SistemaHospital hospital = new SistemaHospital();


        // =========================================================
        // MEDICIÓN DE MEMORIA - ANTES
        // =========================================================

        Runtime runtime = Runtime.getRuntime();

        // Solicitar al Garbage Collector que libere memoria
        // que ya no esté siendo utilizada.
        System.gc();

        long memoriaAntes =
                runtime.totalMemory() - runtime.freeMemory();


        // =========================================================
        // MEDICIÓN DEL TIEMPO DE INSERCIÓN
        // =========================================================

        long inicioInsercion = System.nanoTime();


        // Registrar 100 pacientes automáticamente
        for (int i = 1; i <= CANTIDAD_PACIENTES; i++) {

            String documento = String.valueOf(1000 + i);

            String nombre = "Paciente " + i;

            int edad = 18 + (i % 60);

            Paciente paciente =
                    new Paciente(
                            documento,
                            nombre,
                            edad
                    );

            /*
             * Se registra directamente utilizando el método
             * existente del sistema.
             */
            hospital.registrarPacienteMedicion(paciente);
        }


        long finInsercion = System.nanoTime();


        // =========================================================
        // MEDICIÓN DE MEMORIA - DESPUÉS
        // =========================================================

        long memoriaDespues =
                runtime.totalMemory() - runtime.freeMemory();

        long memoriaUsada =
                memoriaDespues - memoriaAntes;


        // =========================================================
        // CALCULAR TIEMPO DE INSERCIÓN
        // =========================================================

        long tiempoInsercion =
                finInsercion - inicioInsercion;


        // =========================================================
        // MOSTRAR RESULTADOS DE INSERCIÓN
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        RESULTADOS DE INSERCIÓN");
        System.out.println("============================================");

        System.out.println(
                "Pacientes registrados: "
                        + hospital.cantidadPacientes()
        );

        System.out.println(
                "Tiempo de inserción: "
                        + tiempoInsercion
                        + " ns"
        );

        System.out.println(
                "Tiempo de inserción: "
                        + (tiempoInsercion / 1_000_000.0)
                        + " ms"
        );


        // =========================================================
        // RESULTADO DE MEMORIA
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        RESULTADOS DE MEMORIA");
        System.out.println("============================================");

        System.out.println(
                "Memoria utilizada aproximadamente: "
                        + memoriaUsada
                        + " bytes"
        );

        System.out.println(
                "Memoria utilizada aproximadamente: "
                        + (memoriaUsada / 1024.0)
                        + " KB"
        );


        // =========================================================
        // MEDICIÓN DE BÚSQUEDA
        // =========================================================

        /*
         * Vamos a buscar el paciente 50.
         *
         * Documento:
         * 1000 + 50 = 1050
         */

        String documentoBuscar = "1050";

        /*
         * Primera búsqueda:
         * sirve para que la JVM pueda preparar el código.
         * No utilizamos su tiempo como resultado.
         */
        hospital.buscarPacienteMedicion(documentoBuscar);


        // Medición real
        long inicioBusqueda = System.nanoTime();

        Paciente encontrado =
                hospital.buscarPacienteMedicion(
                        documentoBuscar
                );

        long finBusqueda = System.nanoTime();


        long tiempoBusqueda =
                finBusqueda - inicioBusqueda;


        // =========================================================
        // RESULTADOS DE BÚSQUEDA
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        RESULTADOS DE BÚSQUEDA");
        System.out.println("============================================");

        System.out.println(
                "Documento buscado: "
                        + documentoBuscar
        );

        if (encontrado != null) {

            System.out.println(
                    "Paciente encontrado: "
                            + encontrado.getNombre()
            );

        } else {

            System.out.println(
                    "Paciente no encontrado."
            );
        }

        System.out.println(
                "Tiempo de búsqueda: "
                        + tiempoBusqueda
                        + " ns"
        );

        System.out.println(
                "Tiempo de búsqueda: "
                        + (tiempoBusqueda / 1_000_000.0)
                        + " ms"
        );


        // =========================================================
        // PRUEBA DE DUPLICADO
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        PRUEBA DE DUPLICADO");
        System.out.println("============================================");

        hospital.registrarPaciente(
                new Paciente(
                        "1050",
                        "Paciente Duplicado",
                        35
                )
        );


        // =========================================================
        // BÚSQUEDA NORMAL
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        BÚSQUEDA NORMAL");
        System.out.println("============================================");

        hospital.buscarPaciente("1050");


        // =========================================================
        // MOSTRAR TODOS LOS PACIENTES
        // =========================================================

        //hospital.mostrarPacientes();


        // =========================================================
        // CANTIDAD FINAL
        // =========================================================

        System.out.println("\n============================================");
        System.out.println("        RESULTADO FINAL");
        System.out.println("============================================");

        System.out.println(
                "Cantidad de pacientes: "
                        + hospital.cantidadPacientes()
        );
    }


    // =============================================================
    // MÉTODO AUXILIAR PARA REGISTRAR SIN IMPRIMIR
    // =============================================================

    /*
     * Este método utiliza el método público registrarPaciente(),
     * pero necesitamos evitar los mensajes en consola durante
     * la medición.
     *
     * Para no modificar la estructura del ejercicio,
     * hacemos el registro utilizando una búsqueda previa
     * y luego el método registrarPaciente().
     */
}