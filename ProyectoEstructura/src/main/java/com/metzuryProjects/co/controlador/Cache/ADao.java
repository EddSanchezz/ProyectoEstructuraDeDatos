package com.metzuryProjects.co.controlador.Cache;




import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.metzuryProjects.co.utilidades.GestorDeEntidad;

public abstract class ADao<ClaseEntidad, TipoId>
        implements IDao<ClaseEntidad, TipoId> {

    private Class<ClaseEntidad> claseEntidad;
    protected GestorDeEntidad gestorDeEntidad;

    @SuppressWarnings("unchecked")
    public ADao(String rutaArchivo) throws IOException {

        this.claseEntidad = (Class<ClaseEntidad>) (
                (ParameterizedType) getClass().getGenericSuperclass()
        ).getActualTypeArguments()[0];

        this.gestorDeEntidad = new GestorDeEntidad(rutaArchivo);
    }

    @Override
    public List<ClaseEntidad> obtenerTodos() {
        return this.gestorDeEntidad.obtenerTodos(this.claseEntidad);
    }
}