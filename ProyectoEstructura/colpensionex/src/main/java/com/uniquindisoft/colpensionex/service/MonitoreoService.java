package com.uniquindisoft.colpensionex.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MonitoreoService {
    private static MonitoreoService instance;
    private final Map<String, AtomicInteger> contadores;
    private final Map<String, LocalDateTime> ultimosProcesos;

    private MonitoreoService() {
        this.contadores = new ConcurrentHashMap<>();
        this.ultimosProcesos = new ConcurrentHashMap<>();
    }

    public static synchronized MonitoreoService getInstance() {
        if (instance == null) {
            instance = new MonitoreoService();
        }
        return instance;
    }

    public void registrarProcesamiento(String tipo) {
        contadores.computeIfAbsent(tipo, k -> new AtomicInteger(0))
                 .incrementAndGet();
        ultimosProcesos.put(tipo, LocalDateTime.now());
    }

    public int obtenerContador(String tipo) {
        return contadores.getOrDefault(tipo, new AtomicInteger(0)).get();
    }

    public LocalDateTime obtenerUltimoProcesamiento(String tipo) {
        return ultimosProcesos.get(tipo);
    }

    public void reiniciarContadores() {
        contadores.clear();
        ultimosProcesos.clear();
    }
}