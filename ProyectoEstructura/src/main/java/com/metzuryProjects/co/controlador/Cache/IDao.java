package com.metzuryProjects.co.controlador.Cache;

import java.util.List;

public interface IDao <ClaseEntidad, TipoId> {

    public List<ClaseEntidad> obtenerTodos();
}
