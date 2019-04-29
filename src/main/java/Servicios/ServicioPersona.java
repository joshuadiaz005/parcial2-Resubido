package Servicios;

import Modelos.Persona;

public class ServicioPersona extends ServicioBaseDeDatos<Persona> {
    private static ServicioPersona instancia;

    private ServicioPersona() {
        super(Persona.class);
    }

    public static ServicioPersona getInstancia() {
        if (instancia == null) {
            instancia = new ServicioPersona();
        }
        return instancia;
    }
}