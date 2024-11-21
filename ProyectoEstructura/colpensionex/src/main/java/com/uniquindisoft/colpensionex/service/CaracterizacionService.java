package com.uniquindisoft.colpensionex.service;
import com.uniquindisoft.colpensionex.cache.SuperCache;
import com.uniquindisoft.colpensionex.model.*;
import java.util.List;

public class CaracterizacionService {
    private final SuperCache cache = SuperCache.getInstance();

    public boolean verificarCaracterizaciones(Solicitud solicitud) {
        Cotizante cotizante = solicitud.getCotizante();
        List<Caracterizacion> caracterizaciones = cache.getCaracterizaciones(
            cotizante.getTipoDocumento(),
            cotizante.getNumeroDocumento()
        );

        for (Caracterizacion caracterizacion : caracterizaciones) {
            switch (caracterizacion.getTipoCaracterizacion()) {
                case INHABILITAR:
                    return false;
                case EMBARGAR:
                    solicitud.setEmbargado(true);
                    break;
            }
        }

        return true;
    }
}