package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.es.Cuando;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosCuando {
    @Cuando( "^ordeno una torta de '(\\d+)' kilos$" )
    public void ordeno_una_torta_de_kilos( Double kilo ) throws Throwable {
        CucumberContext context = CucumberContext.getInstance();
        Torta torta = context.get( CucumberContext.ContentKeys.TORTA );
        if ( null == torta ) {
            torta = new Torta();
            context.add( CucumberContext.ContentKeys.TORTA, torta );
        }

        Cobertura cobertura = context.get( CucumberContext.ContentKeys.COBERTURA_BASE );
        torta.setCobertura( cobertura );
        Relleno relleno = context.get( CucumberContext.ContentKeys.RELLENO_BASE );
        Piso piso = new Piso();
        piso.setRelleno( relleno );
        piso.setPeso( kilo );
        torta.setBase( piso );
        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper
                .postStringEntity( HttpClientHelper.RECURSO_TORTA,
                        torta ) );
        CucumberContext.getInstance()
                .add( CucumberContext.ContentKeys.TORTA_URL, getLocation( response ) );
    }

    private String getLocation( HttpClientHelper.Response response ) {
        return response.getClosedResponse().getFirstHeader( "Location" ).getValue();
    }

    @Cuando( "^la agrego al carrito$" )
    public void la_agrego_al_carrito() throws Throwable {
        CucumberContext context = CucumberContext.getInstance();
        String location = context.get( CucumberContext.ContentKeys.TORTA_URL );
        HttpClientHelper.Response response = HttpClientHelper.execute( new HttpGet( location ) );
        Torta torta = context.getObjectMapper()
                .readValue( response.getEntity(), Torta.class );
        Carrito carrito = context.get( CucumberContext.ContentKeys.CARRITO );
        if ( null == carrito ) {
            carrito = new Carrito();
        }

        carrito.addContenido( torta );

        if ( null == carrito.getId() ) {
            response = HttpClientHelper.execute( HttpClientHelper
                    .postStringEntity( String.format( HttpClientHelper.RECURSO_CARRITO_USUARIO,
                            context.get( CucumberContext.ContentKeys.USER_ID ) ),
                            carrito ) );
            context.add( CucumberContext.ContentKeys.CARRITO_URL, getLocation( response ) );
        } else {
            HttpClientHelper.execute( HttpClientHelper
                    .postStringEntity( String.format( HttpClientHelper.RECURSO_CARRITO_USUARIO_TORTA,
                            context.get( CucumberContext.ContentKeys.USER_ID ), carrito.getId() ),
                            torta, false ) );
        }

        response = HttpClientHelper.execute( HttpClientHelper.getEntity( context.get( CucumberContext.ContentKeys.CARRITO_URL ).toString() ) );
        context.add( CucumberContext.ContentKeys.CARRITO,
                context.getObjectMapper().readValue(
                        response.getEntity(), Carrito.class ) );

    }

    @Cuando( "^agrego un piso$" )
    public void agrego_un_piso() throws Throwable {
        Torta torta = CucumberContext.getInstance().get( CucumberContext.ContentKeys.TORTA );
        Piso piso = new Piso();
        piso.setPeso( RandomUtils.nextInt() % 10d + 1 );
        torta.agregarPiso( piso );

        HttpClientHelper.execute( HttpClientHelper.postStringEntity(
                String.format( HttpClientHelper.RECURSO_TORTA_PISO, torta.getId() ), piso ) );

    }
}
