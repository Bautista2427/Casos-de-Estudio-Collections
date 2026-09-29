package org.Escenario3.Version1;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class PlataformaTaxis {

    private Queue<SolicitudTaxi> solicitudes;

    public PlataformaTaxis() {
        solicitudes = new ArrayDeque<>();
    }

    // ==========================================
    // REGISTRAR SOLICITUD
    // ==========================================

    public void registrarSolicitud(SolicitudTaxi solicitud) {
        solicitudes.offer(solicitud);
    }

    // ==========================================
    // ATENDER SOLICITUD MÁS ANTIGUA
    // ==========================================

    public SolicitudTaxi atenderSolicitud() {
        return solicitudes.poll();
    }

    // ==========================================
    // CANCELAR SOLICITUD ESPECÍFICA
    // ==========================================

    public boolean cancelarSolicitud(int id) {

        Iterator<SolicitudTaxi> iterator =
                solicitudes.iterator();

        while (iterator.hasNext()) {

            SolicitudTaxi solicitud =
                    iterator.next();

            if (solicitud.getId() == id) {

                iterator.remove();

                return true;
            }
        }

        return false;
    }

    // ==========================================
    // MOSTRAR SOLICITUDES PENDIENTES
    // ==========================================

    public void mostrarSolicitudesPendientes() {

        if (solicitudes.isEmpty()) {

            System.out.println(
                    "No hay solicitudes pendientes."
            );

            return;
        }

        Iterator<SolicitudTaxi> iterator =
                solicitudes.iterator();

        while (iterator.hasNext()) {

            SolicitudTaxi solicitud =
                    iterator.next();

            System.out.println(solicitud);
        }
    }

    // ==========================================
    // CANTIDAD DE SOLICITUDES
    // ==========================================

    public int cantidadSolicitudes() {
        return solicitudes.size();
    }

    // ==========================================
    // ITERATOR
    // ==========================================

    public Iterator<SolicitudTaxi> obtenerIterator() {
        return solicitudes.iterator();
    }
}