// Ubicación: src/main/java/com/uniquindisoft/colpensionex/service/GeneradorArchivosService.java

package com.uniquindisoft.colpensionex.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class GeneradorArchivosService {
    private static final Logger logger = Logger.getLogger(GeneradorArchivosService.class.getName());
    private final Path directorioSalida;
    private final Random random;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Datos para generación aleatoria
    private static final String[] TIPOS_DOCUMENTO = {"CC", "CE", "PA"};
    private static final String[] NOMBRES = {"Juan", "María", "Pedro", "Ana", "Luis", "Carmen", "José", "Patricia"};
    private static final String[] APELLIDOS = {"García", "Rodríguez", "Martínez", "López", "González", "Pérez", "Sánchez"};
    private static final String[] FONDOS = {"Porvenir", "Proteccion", "Colfondos", "Old Mutual"};
    private static final String[] CIUDADES = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena"};
    private static final String[] INSTITUCIONES = {"Ninguna", "Armada", "Inpec", "Policía", "Minsalud", "Mininterior"};

    public GeneradorArchivosService(Path directorioSalida) {
        this.directorioSalida = directorioSalida;
        this.random = new Random();
        crearDirectorioSiNoExiste();
    }

    private void crearDirectorioSiNoExiste() {
        try {
            if (!Files.exists(directorioSalida)) {
                Files.createDirectories(directorioSalida);
                logger.info("Directorio de salida creado: " + directorioSalida);
            }
        } catch (Exception e) {
            logger.severe("Error creando directorio de salida: " + e.getMessage());
            throw new RuntimeException("Error creando directorio de salida", e);
        }
    }

    public void generarArchivosSolicitudes(int numeroArchivos, int solicitudesPorArchivo) {
        logger.info("Iniciando generación de " + numeroArchivos + " archivos con " +
                solicitudesPorArchivo + " solicitudes cada uno");

        try {
            for (int i = 0; i < numeroArchivos; i++) {
                List<String> solicitudes = new ArrayList<>();

                // Agregar encabezado
                solicitudes.add(generarEncabezadoSolicitud());

                // Generar solicitudes
                for (int j = 0; j < solicitudesPorArchivo; j++) {
                    solicitudes.add(generarLineaSolicitud());
                }

                // Escribir archivo
                String nombreArchivo = String.format("solicitudes_%05d.csv", i + 1);
                Path archivoSalida = directorioSalida.resolve(nombreArchivo);
                Files.write(archivoSalida, solicitudes);

                if ((i + 1) % 100 == 0) {
                    logger.info("Generados " + (i + 1) + " archivos");
                }
            }
            logger.info("Generación de archivos completada exitosamente");
        } catch (Exception e) {
            logger.severe("Error generando archivos: " + e.getMessage());
            throw new RuntimeException("Error generando archivos", e);
        }
    }

    public void generarArchivosCaracterizacion() {
        try {
            // Generar archivo para cada entidad
            generarArchivoCaracterizacion("Fiscalia");
            generarArchivoCaracterizacion("Procuraduria");
            generarArchivoCaracterizacion("Contraloria");

            logger.info("Archivos de caracterización generados exitosamente");
        } catch (Exception e) {
            logger.severe("Error generando archivos de caracterización: " + e.getMessage());
            throw new RuntimeException("Error generando archivos de caracterización", e);
        }
    }

    private String generarEncabezadoSolicitud() {
        return "tipoDocumento,numeroDocumento,nombreCompleto,fechaNacimiento,prePensionado," +
                "semanasCotizadas,fondoOrigen,ciudadNacimiento,ciudadResidencia,institucionPublica," +
                "tieneCondecoracion,tieneHijosInpec,familiaresPolicia,observacionDisciplinaria," +
                "declaranteRenta";
    }

    private String generarLineaSolicitud() {
        StringBuilder sb = new StringBuilder();

        // Generar datos básicos
        String tipoDoc = TIPOS_DOCUMENTO[random.nextInt(TIPOS_DOCUMENTO.length)];
        String numDoc = String.format("%08d", random.nextInt(100000000));
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)] + " " +
                APELLIDOS[random.nextInt(APELLIDOS.length)];

        // Generar fecha de nacimiento (entre 18 y 65 años)
        LocalDate fechaNac = LocalDate.now().minusYears(18 + random.nextInt(48));

        // Generar otros datos
        boolean prePensionado = random.nextDouble() < 0.1; // 10% son pre-pensionados
        int semanasCotizadas = 100 + random.nextInt(901); // Entre 100 y 1000 semanas
        String fondoOrigen = FONDOS[random.nextInt(FONDOS.length)];
        String ciudadNac = CIUDADES[random.nextInt(CIUDADES.length)];
        String ciudadRes = CIUDADES[random.nextInt(CIUDADES.length)];
        String institucion = INSTITUCIONES[random.nextInt(INSTITUCIONES.length)];

        // Generar características booleanas
        boolean tieneCondecoracion = random.nextBoolean();
        boolean tieneHijosInpec = random.nextBoolean();
        boolean familiaresPolicia = random.nextBoolean();
        boolean obsDiscip = random.nextBoolean();
        boolean declaranteRenta = random.nextBoolean();

        // Construir línea CSV
        return String.join(",",
                tipoDoc,
                numDoc,
                nombre,
                fechaNac.format(DATE_FORMATTER),
                String.valueOf(prePensionado),
                String.valueOf(semanasCotizadas),
                fondoOrigen,
                ciudadNac,
                ciudadRes,
                institucion,
                String.valueOf(tieneCondecoracion),
                String.valueOf(tieneHijosInpec),
                String.valueOf(familiaresPolicia),
                String.valueOf(obsDiscip),
                String.valueOf(declaranteRenta)
        );
    }

    private void generarArchivoCaracterizacion(String entidad) throws IOException {
        List<String> caracterizaciones = new ArrayList<>();

        // Agregar encabezado
        caracterizaciones.add("tipoDocumento,numeroDocumento,nombreCompleto,caracterizacion");

        // Generar caracterizaciones aleatorias
        int numCaracterizaciones = 100 + random.nextInt(901); // Entre 100 y 1000 caracterizaciones
        for (int i = 0; i < numCaracterizaciones; i++) {
            caracterizaciones.add(generarLineaCaracterizacion());
        }

        // Escribir archivo
        String nombreArchivo = "caracterizaciones_" + entidad.toLowerCase() + ".csv";
        Path archivoSalida = directorioSalida.resolve(nombreArchivo);
        Files.write(archivoSalida, caracterizaciones);

        logger.info("Archivo de caracterización generado para " + entidad);
    }

    private String generarLineaCaracterizacion() {
        String tipoDoc = TIPOS_DOCUMENTO[random.nextInt(TIPOS_DOCUMENTO.length)];
        String numDoc = String.format("%08d", random.nextInt(100000000));
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)] + " " +
                APELLIDOS[random.nextInt(APELLIDOS.length)];
        String caracterizacion = random.nextBoolean() ? "INHABILITAR" : "EMBARGAR";

        return String.join(",", tipoDoc, numDoc, nombre, caracterizacion);
    }

    // Métodos utilitarios para generación de datos
    private LocalDate generarFechaNacimientoAleatoria() {
        int minAge = 18;
        int maxAge = 65;
        long minDay = LocalDate.now().minusYears(maxAge).toEpochDay();
        long maxDay = LocalDate.now().minusYears(minAge).toEpochDay();
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));

        return LocalDate.ofEpochDay(randomDay);
    }

    private int generarSemanasCotizadas(String fondoOrigen) {
        switch (fondoOrigen) {
            case "Porvenir": return 800 + random.nextInt(201);    // 800-1000
            case "Proteccion": return 590 + random.nextInt(211);  // 590-800
            case "Colfondos": return 300 + random.nextInt(291);   // 300-590
            case "Old Mutual": return 100 + random.nextInt(201);  // 100-300
            default: return 100 + random.nextInt(901);            // 100-1000
        }
    }
}