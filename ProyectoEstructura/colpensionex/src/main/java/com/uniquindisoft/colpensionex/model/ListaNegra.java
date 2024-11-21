package com.uniquindisoft.colpensionex.model;

import java.time.LocalDate;
import java.util.*;

public class ListaNegra {
    private Map<String, Map<String, LocalDate>> cotizantesInhabilitados;
    private Map<String, Set<String>> cotizantesEmbargables;
    private static final long PERIODO_INHABILITACION_MESES = 6;

    public ListaNegra() {
        this.cotizantesInhabilitados = new HashMap<>();
        this.cotizantesEmbargables = new HashMap<>();
    }

    public void agregarInhabilitado(String tipoDoc, String numDoc, LocalDate fechaInhabilitacion) {
        cotizantesInhabilitados.computeIfAbsent(tipoDoc, k -> new HashMap<>())
                              .put(numDoc, fechaInhabilitacion);
    }

    public void agregarEmbargable(String tipoDoc, String numDoc) {
        cotizantesEmbargables.computeIfAbsent(tipoDoc, k -> new HashSet<>())
                            .add(numDoc);
    }

    public boolean esInhabilitado(String tipoDoc, String numDoc, LocalDate fechaActual) {
        Map<String, LocalDate> inhabilitados = cotizantesInhabilitados.get(tipoDoc);
        if (inhabilitados != null && inhabilitados.containsKey(numDoc)) {
            LocalDate fechaInhabilitacion = inhabilitados.get(numDoc);
            return fechaInhabilitacion.plusMonths(PERIODO_INHABILITACION_MESES).isAfter(fechaActual);
        }
        return false;
    }

    public boolean esEmbargable(String tipoDoc, String numDoc) {
        return cotizantesEmbargables.getOrDefault(tipoDoc, Collections.emptySet())
                                   .contains(numDoc);
    }

    public Iterator<Map.Entry<String, String>> getEmbargablesIterator() {
        List<Map.Entry<String, String>> todosEmbargables = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : cotizantesEmbargables.entrySet()) {
            String tipoDoc = entry.getKey();
            for (String numDoc : entry.getValue()) {
                todosEmbargables.add(new AbstractMap.SimpleEntry<>(tipoDoc, numDoc));
            }
        }
        return todosEmbargables.iterator();
    }

    public void limpiarInhabilitacionesVencidas(LocalDate fechaActual) {
        cotizantesInhabilitados.values().forEach(inhabilitados -> {
            inhabilitados.entrySet().removeIf(entry ->
                entry.getValue().plusMonths(PERIODO_INHABILITACION_MESES).isBefore(fechaActual)
            );
        });
        cotizantesInhabilitados.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
}