package com.zuppelli.resource;

import com.sun.jersey.api.spring.Autowire;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.service.Servicio;
import com.zuppelli.service.ServicioTorta;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path( "/torta/{tortaId}/piso" )
@Autowire
public class RecursoPiso extends Recurso<Piso, Long> {

    @PathParam( "tortaId" )
    private Long tortaId;

    private ServicioTorta servicioTorta;
    private Servicio<Piso> servicioPiso;


    @Override
    public Collection<Piso> get() {
        return this.servicioTorta.get( tortaId ).getPisos();
    }

    @Override
    public Piso get( Long id ) {
        return servicioPiso.get( id );
    }

    @Override
    public Response add( Piso entity ) {
        this.servicioTorta.agregarPiso( tortaId, this.servicioPiso.store( entity ) );
        return Response.created( uriInfo.getAbsolutePathBuilder().path( entity.getId().toString() ).build() ).build();
    }

    @Override
    public void delete( Long id ) {
        this.servicioTorta.removerPiso( tortaId, id );
    }

    @Override
    public Response update( Piso entity ) {
        return add( entity );
    }

    public void setTortaId( Long tortaId ) {
        this.tortaId = tortaId;
    }

    @PUT
    @Path( "{pisoId}/relleno" )
    public Response updateRelleno( Relleno relleno, @PathParam( "pisoId" ) Long pisoId ) {
        servicioPiso.get( pisoId ).setRelleno( relleno );
        return Response.ok().build();
    }

    public void setServicioPiso( Servicio<Piso> servicioPiso ) {
        this.servicioPiso = servicioPiso;
    }

    public void setServicioTorta( ServicioTorta servicioTorta ) {
        this.servicioTorta = servicioTorta;
    }
}
