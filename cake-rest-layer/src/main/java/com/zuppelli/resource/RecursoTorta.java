package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.config.ConfigHelper;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.service.Servicio;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/torta" )
@Autowire
public class RecursoTorta extends Recurso<Torta,Long>
{
    private Servicio<Torta> servicioTorta;

    @Override
    public Collection<Torta> get() {
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

    @Path( "por_kilo" )
    @POST
    @Consumes( MediaType.TEXT_PLAIN )
    public void configuraPorKilo(String porKilo){
        ConfigHelper.getInstance().put( ConfigHelper.Keys.PRECIO_POR_KILO, Double.parseDouble( porKilo ) );
    }

    @Override
    public Response update( com.zuppelli.cake.modelo.Torta entity ) {
        return Response.created( uriInfo.getAbsolutePath() ).entity( servicioTorta.store( entity ) ).build();
    }

    public void setServicioTorta( Servicio<Torta> servicioTorta ) {
        this.servicioTorta = servicioTorta;
    }
}
