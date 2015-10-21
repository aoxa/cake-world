package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.config.ConfigHelper;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.service.Servicio;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/torta" )
@Autowire
public class RecursoTorta extends Recurso<Torta>
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
        return super.add( servicioTorta.store( entity ) );
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
    public Response update( Torta entity ) {
        return super.add( servicioTorta.store( entity ));
    }

    public void setServicioTorta( Servicio<Torta> servicioTorta ) {
        this.servicioTorta = servicioTorta;
    }
}
