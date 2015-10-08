package com.zuppelli.service;

import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 *
 */
@Service
public class ServicioTorta {

    @Autowired
    private Repository<Torta, Long> repository;

    public Torta get(Long id) {
        return repository.retrieve( id );
    }

    public Collection<Torta> get() {
        return repository.retrieve();
    }

    public Torta store( Torta torta ) {
        return repository.store( torta );
    }

    public void remove( Long id ) {
        repository.remove( id );
    }

    public void agregarPiso(Long tortaId, Piso piso ) {
        repository.retrieve( tortaId ).agregarPiso( piso );
    }
}
