package com.zuppelli.resource;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.service.Servicio;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/cobertura" )
@Component
public class RecursoCobertura extends Recurso<Cobertura, Long> implements InitializingBean
{
    @Autowired private Servicio<Cobertura> servicioCobertura;

    @Override
    public Collection<Cobertura> get()
    {
        return servicioCobertura.get();
    }

    @Override
    public Cobertura get( Long id )
    {
        return servicioCobertura.get( id );
    }

    @Override
    public Response add( Cobertura entity )
    {
        entity = servicioCobertura.store( entity );
        return Response.created( uriInfo.getAbsolutePathBuilder()
                                         .path( entity.getId().toString() ).build() )
                       .entity( entity.getId().toString() ).build();
    }

    @Override
    public void delete( Long id )
    {
        servicioCobertura.remove( id );
    }

    @Override
    public Response update( Cobertura entity )
    {
        return Response.status( Response.Status.NOT_ACCEPTABLE ).build();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        Cobertura cobertura = new Cobertura();
        cobertura.setTipo( "Chocolate" );
        cobertura.setPrecio( 20l );
        this.servicioCobertura.store( cobertura );

        cobertura = new Cobertura();
        cobertura.setTipo( "Chispas" );
        cobertura.setPrecio( 15l );
        this.servicioCobertura.store( cobertura );
        cobertura = new Cobertura();

        cobertura.setTipo( "Glaseado" );
        cobertura.setPrecio( 10l );
        this.servicioCobertura.store( cobertura );
    }
}
