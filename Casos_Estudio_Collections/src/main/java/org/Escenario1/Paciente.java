package org.Escenario1;

class Paciente {

    private String documento;
    private String nombre;
    private int edad;

    public Paciente(String documento, String nombre, int edad) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Documento: " + documento
                + " | Nombre: " + nombre
                + " | Edad: " + edad;
    }
}