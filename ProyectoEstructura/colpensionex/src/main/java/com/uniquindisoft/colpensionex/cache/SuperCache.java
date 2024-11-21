package com.uniquindisoft.colpensionex.cache;

import com.uniquindisoft.colpensionex.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDate;
import java.util.logging.Logger;


public class SuperCache {
    private static final Logger logger = Logger.getLogger(SuperCache.class.getName());
    private static SuperCache instance;

    // Cache principal para datos generales
    private final Map<String, Map<String, Object>> cacheData;

    // Caches específicos para mejorar el rendimiento
    private final Map<String, List<Caracterizacion>> caracterizacionesPorDocumento;
    private final Map<String, Cotizante> cotizantesPorDocumento;
    private final Map<String, Ciudad> ciudadesPorCodigo;
    private final Map<String, Departamento> departamentosPorCodigo;
    private final Map<String, Pais> paisesPorCodigo;

    private SuperCache() {
        this.cacheData = new ConcurrentHashMap<>();
        this.caracterizacionesPorDocumento = new ConcurrentHashMap<>();
        this.cotizantesPorDocumento = new ConcurrentHashMap<>();
        this.ciudadesPorCodigo = new ConcurrentHashMap<>();
        this.departamentosPorCodigo = new ConcurrentHashMap<>();
        this.paisesPorCodigo = new ConcurrentHashMap<>();
    }

    public static synchronized SuperCache getInstance() {
        if (instance == null) {
            instance = new SuperCache();
        }
        return instance;
    }

    public void putCache(String cacheName, String key, Object value) {
        cacheData.computeIfAbsent(cacheName, k -> new ConcurrentHashMap<>())
                .put(key, value);

        // Actualizar caches específicos según el tipo de objeto
        if (value instanceof Ciudad) {
            ciudadesPorCodigo.put(((Ciudad) value).getCodigo(), (Ciudad) value);
        } else if (value instanceof Departamento) {
            departamentosPorCodigo.put(((Departamento) value).getCodigo(), (Departamento) value);
        } else if (value instanceof Pais) {
            paisesPorCodigo.put(((Pais) value).getCodigo(), (Pais) value);
        }

        logger.fine("Objeto agregado al caché: " + cacheName + " - " + key);
    }

    public Object getCache(String cacheName, String key) {
        return cacheData.getOrDefault(cacheName, new ConcurrentHashMap<>())
                .get(key);
    }

    public void addCaracterizacion(Caracterizacion caracterizacion) {
        String key = caracterizacion.getTipoDocumento() + "-" + caracterizacion.getNumeroDocumento();
        caracterizacionesPorDocumento.computeIfAbsent(key, k -> new ArrayList<>())
                .add(caracterizacion);
        logger.fine("Caracterización agregada para: " + key);
    }

    public void addCotizante(Cotizante cotizante) {
        String key = cotizante.getTipoDocumento() + "-" + cotizante.getNumeroDocumento();
        cotizantesPorDocumento.put(key, cotizante);
        logger.fine("Cotizante agregado: " + key);
    }

    public List<Caracterizacion> getCaracterizaciones(String tipoDoc, String numDoc) {
        String key = tipoDoc + "-" + numDoc;
        return caracterizacionesPorDocumento.getOrDefault(key, new ArrayList<>());
    }

    public Cotizante getCotizante(String tipoDoc, String numDoc) {
        String key = tipoDoc + "-" + numDoc;
        return cotizantesPorDocumento.get(key);
    }

    public Ciudad getCiudad(String codigo) {
        return ciudadesPorCodigo.get(codigo);
    }

    public Departamento getDepartamento(String codigo) {
        return departamentosPorCodigo.get(codigo);
    }

    public Pais getPais(String codigo) {
        return paisesPorCodigo.get(codigo);
    }

    public boolean existeCaracterizacion(String tipoDoc, String numDoc) {
        String key = tipoDoc + "-" + numDoc;
        return caracterizacionesPorDocumento.containsKey(key) &&
                !caracterizacionesPorDocumento.get(key).isEmpty();
    }

    public void clear() {
        cacheData.clear();
        caracterizacionesPorDocumento.clear();
        cotizantesPorDocumento.clear();
        ciudadesPorCodigo.clear();
        departamentosPorCodigo.clear();
        paisesPorCodigo.clear();
        logger.info("Cache limpiado completamente");
    }

    public Map<String, Integer> getEstadisticas() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("caracterizaciones", caracterizacionesPorDocumento.size());
        stats.put("cotizantes", cotizantesPorDocumento.size());
        stats.put("ciudades", ciudadesPorCodigo.size());
        stats.put("departamentos", departamentosPorCodigo.size());
        stats.put("paises", paisesPorCodigo.size());
        return stats;
    }
}