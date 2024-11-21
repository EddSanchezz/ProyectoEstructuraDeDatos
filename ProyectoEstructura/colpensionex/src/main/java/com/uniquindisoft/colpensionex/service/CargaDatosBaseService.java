package com.uniquindisoft.colpensionex.service;

import com.uniquindisoft.colpensionex.cache.SuperCache;
import com.uniquindisoft.colpensionex.model.*;
import com.uniquindisoft.colpensionex.util.CSVReader;
import java.nio.file.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class CargaDatosBaseService {
    private static final Logger logger = Logger.getLogger(CargaDatosBaseService.class.getName());
    private final SuperCache cache;
    private final Path directorioBase;

    // Constantes para nombres de archivos
    private static final String ARCHIVO_PAISES = "paises.csv";
    private static final String ARCHIVO_DEPARTAMENTOS = "departamentos.csv";
    private static final String ARCHIVO_CIUDADES = "ciudades.csv";

    public CargaDatosBaseService(Path directorioBase) {
        this.directorioBase = directorioBase;
        this.cache = SuperCache.getInstance();
    }

    public boolean cargarDatosBase() {
        try {
            // Verificar que existe el directorio base
            if (!Files.exists(directorioBase)) {
                Files.createDirectories(directorioBase);
            }

            // Cargar países
            cargarPaises();

            // Cargar departamentos
            cargarDepartamentos();

            // Cargar ciudades
            cargarCiudades();

            // Crear directorios necesarios si no existen
            crearDirectoriosNecesarios();

            return true;
        } catch (Exception e) {
            logger.severe("Error cargando datos base: " + e.getMessage());
            return false;
        }
    }

    private void cargarPaises() throws Exception {
        Path archivoPaises = directorioBase.resolve(ARCHIVO_PAISES);
        if (!Files.exists(archivoPaises)) {
            // Si no existe el archivo, crear uno por defecto
            crearArchivoPaisesDefecto(archivoPaises);
        }

        CSVReader<Pais> reader = new CSVReader<Pais>(archivoPaises, ",") {
            @Override
            protected Pais parseLine(String line) {
                String[] campos = line.split(",");
                Pais pais = new Pais();
                pais.setId(campos[0].trim());
                pais.setNombre(campos[1].trim());
                pais.setCodigo(campos[2].trim());
                if (campos.length > 3) {
                    pais.setCodigoIso(campos[3].trim());
                }
                return pais;
            }
        };

        LinkedList<Pais> paises = reader.leerTodasLasFilas();
        for (Pais pais : paises) {
            cache.putCache("paises", pais.getId(), pais);
        }
        logger.info("Países cargados: " + paises.size());
    }

    private void cargarDepartamentos() throws Exception {
        Path archivoDepartamentos = directorioBase.resolve(ARCHIVO_DEPARTAMENTOS);
        if (!Files.exists(archivoDepartamentos)) {
            crearArchivoDepartamentosDefecto(archivoDepartamentos);
        }

        CSVReader<Departamento> reader = new CSVReader<Departamento>(archivoDepartamentos, ",") {
            @Override
            protected Departamento parseLine(String line) {
                String[] campos = line.split(",");
                Departamento departamento = new Departamento();
                departamento.setId(campos[0].trim());
                departamento.setNombre(campos[1].trim());
                departamento.setPaisId(campos[2].trim());
                departamento.setCodigo(campos[3].trim());
                return departamento;
            }
        };

        LinkedList<Departamento> departamentos = reader.leerTodasLasFilas();
        for (Departamento departamento : departamentos) {
            cache.putCache("departamentos", departamento.getId(), departamento);
        }
        logger.info("Departamentos cargados: " + departamentos.size());
    }

    private void cargarCiudades() throws Exception {
        Path archivoCiudades = directorioBase.resolve(ARCHIVO_CIUDADES);
        if (!Files.exists(archivoCiudades)) {
            crearArchivoCiudadesDefecto(archivoCiudades);
        }

        CSVReader<Ciudad> reader = new CSVReader<Ciudad>(archivoCiudades, ",") {
            @Override
            protected Ciudad parseLine(String line) {
                String[] campos = line.split(",");
                Ciudad ciudad = new Ciudad();
                ciudad.setId(campos[0].trim());
                ciudad.setNombre(campos[1].trim());
                ciudad.setDepartamentoId(campos[2].trim());
                ciudad.setCodigo(campos[3].trim());
                return ciudad;
            }
        };

        LinkedList<Ciudad> ciudades = reader.leerTodasLasFilas();
        for (Ciudad ciudad : ciudades) {
            cache.putCache("ciudades", ciudad.getId(), ciudad);
        }
        logger.info("Ciudades cargadas: " + ciudades.size());
    }

    private void crearDirectoriosNecesarios() throws Exception {
        // Crear directorios si no existen
        Files.createDirectories(directorioBase.resolve("SolicitudesEntrantes"));
        Files.createDirectories(directorioBase.resolve("SolicitudesEnProcesamiento"));
        Files.createDirectories(directorioBase.resolve("CaracterizacionesEntrantes"));
        Files.createDirectories(directorioBase.resolve("CaracterizacionesEnProcesamiento"));

        // Crear directorio de procesados con fecha actual
        String fechaHoy = java.time.LocalDate.now().toString().replace("-", "_");
        Files.createDirectories(directorioBase.resolve("SolicitudesProcesadas_" + fechaHoy));
    }

    private void crearArchivoPaisesDefecto(Path archivo) throws Exception {
        List<String> lineas = Arrays.asList(
                "id,nombre,codigo,codigoIso",
                "1,Colombia,COL,CO"
        );
        Files.write(archivo, lineas);
    }

    private void crearArchivoDepartamentosDefecto(Path archivo) throws Exception {
        List<String> lineas = Arrays.asList(
                "id,nombre,paisId,codigo",
                "1,Bogotá D.C.,1,BOG",
                "2,Antioquia,1,ANT",
                "3,Valle del Cauca,1,VAL"
        );
        Files.write(archivo, lineas);
    }

    private void crearArchivoCiudadesDefecto(Path archivo) throws Exception {
        List<String> lineas = Arrays.asList(
                "id,nombre,departamentoId,codigo",
                "1,Bogotá,1,BOG",
                "2,Medellín,2,MED",
                "3,Cali,3,CAL"
        );
        Files.write(archivo, lineas);
    }
}