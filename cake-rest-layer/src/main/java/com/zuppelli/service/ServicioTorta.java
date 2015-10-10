package com.zuppelli.service;

import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 */
@Service
public class ServicioTorta implements Servicio<Torta>
{
    @Autowired private Repository<Torta, Long> repositorioTorta;
    @Autowired private Servicio<Piso> servicioPiso;
    @Autowired private Servicio<Cobertura> servicioCobertura;

    @Override
    public Torta get( Long id ) {
        return repositorioTorta.retrieve( id );
    }

    @Override
    public Collection<Torta> get() {
        return repositorioTorta.retrieve();
    }

    @Override
    public Torta store( Torta torta ) {

        if( null != torta.getCobertura() && null != torta.getCobertura().getId() )
        {
            torta.setCobertura( servicioCobertura.get( torta.getCobertura().getId() ) );
        }

        if( null == torta.getBase().getId() )
        {
            this.servicioPiso.store( torta.getBase() );
        }

        if( null != torta.getPisos() ) {
            for( Piso piso : torta.getPisos() ) {
                if( null == piso.getId() ) {
                    this.servicioPiso.store( piso );
                }
            }
        }

        return repositorioTorta.store( torta );
    }

    @Override
    public void remove( Long id ) {
        repositorioTorta.remove( id );
    }

    public void agregarPiso(Long tortaId, Piso piso ) {
        repositorioTorta.retrieve( tortaId ).agregarPiso( piso );
    }

    public void removerPiso( Long tortaId, Long pisoId ) {
        servicioPiso.remove( pisoId );
        removerPisoDeTorta( repositorioTorta.retrieve( tortaId ), pisoId );
    }

    private void removerPisoDeTorta( Torta torta, Long pisoId )
    {
        final Iterator<Piso> pisosIterator = torta.getPisos().iterator();
        while( pisosIterator.hasNext() ) {
            Piso piso = pisosIterator.next();
            if( pisoId == piso.getId() ) {
                pisosIterator.remove();
                break;
            }
        }
    }
}
