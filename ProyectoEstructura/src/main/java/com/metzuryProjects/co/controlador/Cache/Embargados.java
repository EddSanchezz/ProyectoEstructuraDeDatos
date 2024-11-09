package com.metzuryProjects.co.controlador.Cache;

public class Embargados extends ADao<Embargados, Integer> {
    public Embargados() throws Exception {
        super("ProyectoEstructura\\src\\main\\java\\com\\metzuryProjects\\co\\ArchivosCSV\\embargados.csv");
    }
}
