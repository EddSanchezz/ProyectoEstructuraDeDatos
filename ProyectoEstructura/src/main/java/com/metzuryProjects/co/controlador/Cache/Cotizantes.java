package com.metzuryProjects.co.controlador.Cache;

import java.io.IOException;

public class Cotizantes extends ADao<Cotizantes,Integer> {
    public Cotizantes() throws IOException {
        super("ProyectoEstructura\\src\\main\\java\\com\\metzuryProjects\\co\\ArchivosCSV\\cotizantes.csv");
    }
}
