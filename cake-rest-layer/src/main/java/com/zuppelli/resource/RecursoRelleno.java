package com.zuppelli.resource;

import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.service.Servicio;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/relleno" )
@Component
public class RecursoRelleno extends Recurso<Relleno> implements InitializingBean
{
    @Autowired private Servicio<Relleno> servicioRelleno;

    @Override
    public Collection<Relleno> get()
    {
        return servicioRelleno.get();
    }

    @Override
    public Relleno get( Long id )
    {
        return servicioRelleno.get( id );
    }

    @Override
    public Response add( Relleno entity )
    {
        entity = servicioRelleno.store( entity );
        return Response.created( uriInfo.getAbsolutePathBuilder()
                                         .path( entity.getId().toString() ).build() )
                       .entity( entity.getId().toString() ).build();
    }

    @Override
    public void delete( Long id )
    {
        servicioRelleno.remove( id );
    }

    @Override
    public Response update( Relleno entity )
    {
        return Response.status( Response.Status.NOT_ACCEPTABLE ).build();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        Relleno relleno = new Relleno();
        relleno.setPrecio( 30l );
        relleno.setTipo( "Dulce de leche" );
        servicioRelleno.store( relleno );

        relleno = new Relleno();
        relleno.setPrecio( 35l );
        relleno.setTipo( "Dulce de leche con chispas" );
        servicioRelleno.store( relleno );

        relleno = new Relleno();
        relleno.setPrecio( 35l );
        relleno.setTipo( "Dulce de leche con merengue" );
        servicioRelleno.store( relleno );

        relleno = new Relleno();
        relleno.setPrecio( 35l );
        relleno.setTipo( "Mermelada y frutas" );
        servicioRelleno.store( relleno );

        relleno = new Relleno();
        relleno.setPrecio( 35l );
        relleno.setTipo( "Chocolate" );
        servicioRelleno.store( relleno );
    }
}
