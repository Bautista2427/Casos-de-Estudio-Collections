package org.Escenario3.Version2;

import java.util.Iterator;

public class Main {

    // ==========================================
    // PRUEBA FUNCIONAL
    // ==========================================

    public static void pruebaFuncional() {

        System.out.println("============================================");
        System.out.println("           PRUEBA FUNCIONAL");
        System.out.println("============================================");

        PlataformaTaxis plataforma =
                new PlataformaTaxis();

        // ------------------------------------------
        // REGISTRAR SOLICITUDES
        // ------------------------------------------

        plataforma.registrarSolicitud(
                new SolicitudTaxi(
                        1,
                        "Carlos",
                        "Centro",
                        "Universidad"
                )
        );

        plataforma.registrarSolicitud(
                new SolicitudTaxi(
                        2,
                        "Maria",
                        "Norte",
                        "Centro"
                )
        );

        plataforma.registrarSolicitud(
                new SolicitudTaxi(
                        3,
                        "Juan",
                        "Sur",
                        "Terminal"
                )
        );

        plataforma.registrarSolicitud(
                new SolicitudTaxi(
                        4,
                        "Laura",
                        "Centro",
                        "Aeropuerto"
                )
        );

        // ------------------------------------------
        // MOSTRAR SOLICITUDES
        // ------------------------------------------

        System.out.println("\nSolicitudes pendientes:");

        plataforma.mostrarSolicitudesPendientes();

        // ------------------------------------------
        // CANCELAR SOLICITUD
        // ------------------------------------------

        System.out.println(
                "\nCancelando solicitud ID 3..."
        );

        boolean cancelada =
                plataforma.cancelarSolicitud(3);

        if (cancelada) {

            System.out.println(
                    "Solicitud cancelada correctamente."
            );

        } else {

            System.out.println(
                    "Solicitud no encontrada."
            );
        }

        // ------------------------------------------
        // MOSTRAR DESPUÉS DE CANCELAR
        // ------------------------------------------

        System.out.println(
                "\nSolicitudes pendientes después de cancelar:"
        );

        plataforma.mostrarSolicitudesPendientes();

        // ------------------------------------------
        // ATENDER SOLICITUD MÁS ANTIGUA
        // ------------------------------------------

        System.out.println(
                "\nAtendiendo solicitud más antigua:"
        );

        SolicitudTaxi atendida =
                plataforma.atenderSolicitud();

        if (atendida != null) {

            System.out.println(
                    "Solicitud atendida:"
            );

            System.out.println(atendida);

        } else {

            System.out.println(
                    "No hay solicitudes pendientes."
            );
        }

        // ------------------------------------------
        // MOSTRAR DESPUÉS DE ATENDER
        // ------------------------------------------

        System.out.println(
                "\nSolicitudes pendientes después de atender:"
        );

        plataforma.mostrarSolicitudesPendientes();
    }


    // ==========================================
    // MEDICIÓN DE RENDIMIENTO
    // ==========================================

    public static void realizarMedicion(int cantidad) {

        System.out.println("\n============================================");
        System.out.println(
                "SOLICITUDES: " + cantidad
        );
        System.out.println("============================================");


        // ==========================================
        // CREAR PLATAFORMA
        // ==========================================

        PlataformaTaxis plataforma =
                new PlataformaTaxis();


        // ==========================================
        // MEDICIÓN DE INSERCIÓN
        // ==========================================

        long inicioInsercion =
                System.nanoTime();

        for (int i = 1; i <= cantidad; i++) {

            SolicitudTaxi solicitud =
                    new SolicitudTaxi(
                            i,
                            "Usuario" + i,
                            "Origen" + i,
                            "Destino" + i
                    );

            plataforma.registrarSolicitud(
                    solicitud
            );
        }

        long finInsercion =
                System.nanoTime();

        long tiempoInsercion =
                finInsercion - inicioInsercion;


        System.out.println("\nRESULTADOS DE INSERCIÓN");
        System.out.println("--------------------------------------------");

        System.out.println(
                "Solicitudes registradas: "
                        + plataforma.cantidadSolicitudes()
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


        // ==========================================
        // MEDICIÓN DE MEMORIA
        // ==========================================

        System.gc();

        long memoriaAntes =
                Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory();


        PlataformaTaxis plataformaMemoria =
                new PlataformaTaxis();


        for (int i = 1; i <= cantidad; i++) {

            SolicitudTaxi solicitud =
                    new SolicitudTaxi(
                            i,
                            "Usuario" + i,
                            "Origen" + i,
                            "Destino" + i
                    );

            plataformaMemoria.registrarSolicitud(
                    solicitud
            );
        }


        long memoriaDespues =
                Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory();


        long memoriaUsada =
                memoriaDespues - memoriaAntes;


        System.out.println("\nRESULTADOS DE MEMORIA");
        System.out.println("--------------------------------------------");

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


        // ==========================================
        // MEDICIÓN DE ATENCIÓN
        // ==========================================

        long inicioAtencion =
                System.nanoTime();


        SolicitudTaxi atendida =
                plataforma.atenderSolicitud();


        long finAtencion =
                System.nanoTime();


        long tiempoAtencion =
                finAtencion - inicioAtencion;


        System.out.println("\nRESULTADOS DE ATENCIÓN");
        System.out.println("--------------------------------------------");

        if (atendida != null) {

            System.out.println(
                    "Solicitud atendida: ID "
                            + atendida.getId()
            );
        }


        System.out.println(
                "Tiempo de atención: "
                        + tiempoAtencion
                        + " ns"
        );

        System.out.println(
                "Tiempo de atención: "
                        + (tiempoAtencion / 1_000_000.0)
                        + " ms"
        );


        // ==========================================
        // MEDICIÓN DE CANCELACIÓN
        // ==========================================

        int idCancelar =
                cantidad / 2;


        long inicioCancelacion =
                System.nanoTime();


        boolean cancelada =
                plataforma.cancelarSolicitud(
                        idCancelar
                );


        long finCancelacion =
                System.nanoTime();


        long tiempoCancelacion =
                finCancelacion - inicioCancelacion;


        System.out.println("\nRESULTADOS DE CANCELACIÓN");
        System.out.println("--------------------------------------------");

        System.out.println(
                "ID buscado para cancelar: "
                        + idCancelar
        );

        System.out.println(
                "Solicitud encontrada: "
                        + cancelada
        );

        System.out.println(
                "Tiempo de cancelación: "
                        + tiempoCancelacion
                        + " ns"
        );

        System.out.println(
                "Tiempo de cancelación: "
                        + (tiempoCancelacion / 1_000_000.0)
                        + " ms"
        );


        // ==========================================
        // MEDICIÓN DE RECORRIDO
        // ==========================================

        long inicioRecorrido =
                System.nanoTime();


        Iterator<SolicitudTaxi> iterator =
                plataforma.obtenerIterator();


        int contador = 0;


        while (iterator.hasNext()) {

            iterator.next();

            contador++;
        }


        long finRecorrido =
                System.nanoTime();


        long tiempoRecorrido =
                finRecorrido - inicioRecorrido;


        System.out.println("\nRESULTADOS DE RECORRIDO");
        System.out.println("--------------------------------------------");

        System.out.println(
                "Solicitudes recorridas: "
                        + contador
        );

        System.out.println(
                "Tiempo de recorrido: "
                        + tiempoRecorrido
                        + " ns"
        );

        System.out.println(
                "Tiempo de recorrido: "
                        + (tiempoRecorrido / 1_000_000.0)
                        + " ms"
        );
    }


    // ==========================================
    // MAIN
    // ==========================================

    public static void main(String[] args) {

        // ------------------------------------------
        // PRUEBA FUNCIONAL
        // ------------------------------------------

        pruebaFuncional();


        System.out.println("\n\n");


        // ------------------------------------------
        // PRUEBAS DE RENDIMIENTO
        // ------------------------------------------

        System.out.println("============================================");
        System.out.println("        PRUEBAS DE RENDIMIENTO");
        System.out.println("============================================");


        realizarMedicion(100);

        realizarMedicion(1000);

        realizarMedicion(10000);

        realizarMedicion(100000);
    }
}
