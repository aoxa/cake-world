package com.zuppelli.service;

import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Torta store( Torta torta ) {
        return repository.store( torta );
    }

    public void remove( Torta torta ) {
        repository.remove( torta );
    }
}
