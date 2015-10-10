package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.service.ServicioTorta;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path( "/torta" )
@Autowire
public class RecursoTorta extends Recurso<Torta,Long>
{
    private ServicioTorta servicioTorta;

    @Override
    public Collection<Torta> get() {

        List<Torta> tortas = new ArrayList<Torta>(  );
        for( int i = 0; i < 5; i++) {
            Torta torta = new Torta();

            torta.setCobertura( new Cobertura() );
            Piso piso = new Piso();
            torta.setBase( piso );
            piso.setMasa("Marmolada");
            piso.setPrecio( 100d + 3 * i );
            piso.setRelleno( new Relleno() );
            piso.getRelleno().setTipo( "Chocolate" );
            torta.getCobertura().setTipo( "Chispas" );
            servicioTorta.store( torta );
        }

        return servicioTorta.get();
    }

    @Override
    public Torta get(Long id) {
        return servicioTorta.get( id );
    }

    @Override
    public Response add( Torta entity ) {
        servicioTorta.store( entity );
        return Response.created( uriInfo.getAbsolutePathBuilder().path( entity.getId().toString() ).build(  ) ).build();
    }

    @Override
    public void delete( Long id ) {
        servicioTorta.remove( id );
    }

    @Override
    public Response update( com.zuppelli.cake.modelo.Torta entity ) {
        return Response.created( uriInfo.getAbsolutePath() ).entity( servicioTorta.store( entity ) ).build();
    }

    public void setServicioTorta( ServicioTorta servicioTorta ) {
        this.servicioTorta = servicioTorta;
    }
}
