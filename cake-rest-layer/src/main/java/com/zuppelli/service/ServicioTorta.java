package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Torta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 *
 */
@Service
public class ServicioTorta extends ServicioGenerico<Torta>
{
    @Autowired private Servicio<Piso> servicioPiso;
    @Autowired private Servicio<Cobertura> servicioCobertura;

    public ServicioTorta() {
        super(Torta.class);
    }

    @Override
    public Torta store( Torta torta ) {

        if( null != torta.getCobertura() && null != torta.getCobertura().getId() )
        {
            torta.setCobertura( servicioCobertura.get( torta.getCobertura().getId() ) );
        }

        if( null != torta.getBase() && null == torta.getBase().getId() )
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

        return super.store( torta );
    }

    public void agregarPiso(Long tortaId, Piso piso ) {
        super.get( tortaId ).agregarPiso( piso );
    }

    public void removerPiso( Long tortaId, Long pisoId ) {
        servicioPiso.remove( pisoId );
        removerPisoDeTorta( super.get( tortaId ), pisoId );
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
