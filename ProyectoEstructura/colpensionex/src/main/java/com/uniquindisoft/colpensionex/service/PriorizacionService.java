package com.uniquindisoft.colpensionex.service;

import com.uniquindisoft.colpensionex.model.Solicitud;
import java.time.LocalDate;
import java.time.Period;

public class PriorizacionService {
    private static final int PRIORIDAD_MENOR_35 = 100;
    private static final int PRIORIDAD_NO_DECLARANTE = 50;
    private static final int PRIORIDAD_BASE = 0;

    public int calcularPrioridad(Solicitud solicitud) {
        int prioridad = PRIORIDAD_BASE;
        
        // Prioridad por edad
        LocalDate fechaNacimiento = solicitud.getCotizante().getFechaNacimiento();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 35) {
            prioridad += PRIORIDAD_MENOR_35;
        }

        // Prioridad por no declarante
        if (!solicitud.getCotizante().getAtributo("declaranteRenta").equals(true)) {
            prioridad += PRIORIDAD_NO_DECLARANTE;
        }

        return prioridad;
    }
}