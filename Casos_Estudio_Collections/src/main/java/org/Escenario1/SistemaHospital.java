package org.Escenario1;

import java.util.Iterator;
import java.util.LinkedHashMap;

class SistemaHospital {

    private LinkedHashMap<String, Paciente> pacientes;

    public SistemaHospital() {
        pacientes = new LinkedHashMap<>();
    }

    // =============================================================
    // REGISTRAR PACIENTE - USO NORMAL
    // =============================================================

    public void registrarPaciente(Paciente paciente) {

        String documento = paciente.getDocumento();

        if (pacientes.containsKey(documento)) {

            System.out.println("No se puede registrar.");
            System.out.println(
                    "Ya existe un paciente con el documento "
                            + documento
            );

        } else {

            pacientes.put(documento, paciente);

            System.out.println(
                    "Paciente registrado correctamente: "
                            + paciente.getNombre()
            );
        }
    }


    // =============================================================
    // REGISTRAR PACIENTE - PARA MEDICIÓN
    // =============================================================

    public void registrarPacienteMedicion(Paciente paciente) {

        String documento = paciente.getDocumento();

        if (!pacientes.containsKey(documento)) {
            pacientes.put(documento, paciente);
        }
    }


    // =============================================================
    // BUSCAR PACIENTE
    // =============================================================

    public void buscarPaciente(String documento) {

        Paciente paciente = pacientes.get(documento);

        if (paciente != null) {

            System.out.println("Paciente encontrado:");
            System.out.println(paciente);

        } else {

            System.out.println(
                    "No existe un paciente con el documento "
                            + documento
            );
        }
    }


    // =============================================================
    // BUSCAR PACIENTE - PARA MEDICIÓN
    // =============================================================

    public Paciente buscarPacienteMedicion(String documento) {

        return pacientes.get(documento);
    }


    // =============================================================
    // MOSTRAR PACIENTES USANDO ITERATOR
    // =============================================================

    public void mostrarPacientes() {

        System.out.println(
                "\n--- PACIENTES EN ORDEN DE LLEGADA ---"
        );

        Iterator<Paciente> iterator =
                pacientes.values().iterator();

        while (iterator.hasNext()) {

            Paciente paciente = iterator.next();

            System.out.println(paciente);
        }
    }


    // =============================================================
    // CANTIDAD DE PACIENTES
    // =============================================================

    public int cantidadPacientes() {

        return pacientes.size();
    }
}