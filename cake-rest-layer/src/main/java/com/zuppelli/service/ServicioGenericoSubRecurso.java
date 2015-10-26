package com.zuppelli.service;

import com.zuppelli.cake.modelo.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ServicioGenericoSubRecurso<T extends Entity, K extends Entity> extends ServicioGenerico<K> {
    protected ServicioGenerico<T> servicioGenerico;

    protected ServicioGenericoSubRecurso( Class<K> current ) {
        super( current );
    }

    /**
     * Devuelve el elemento dependiendo del parent.
     *
     * @param parent - el parent
     * @param id     - el id del elemento a buscar
     * @return El elemento encontrado.
     */
    public abstract K get( T parent, Long id );

    @Autowired
    public abstract void setParentService( ServicioGenerico<T> servicioGenerico );
}
