package com.zuppelli.service;

import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.repository.RepositorioTorta;
import com.zuppelli.repository.Repository;

/**
 *
 */
public class ServicioTorta {

    private Repository<Torta, Long> repository = new RepositorioTorta();

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
