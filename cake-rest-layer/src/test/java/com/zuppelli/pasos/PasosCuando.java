package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.es.Cuando;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosCuando {
    @Cuando( "^ordeno una torta de '(\\d+)' kilos$" )
    public void ordeno_una_torta_de_kilos( Double kilo ) throws Throwable {
        Torta torta = new Torta();
        Cobertura cobertura = CucumberContext.getInstance().get( CucumberContext.ContentKeys.COBERTURA_BASE );
        torta.setCobertura( cobertura );
        Relleno relleno = CucumberContext.getInstance().get( CucumberContext.ContentKeys.RELLENO_BASE );
        Piso piso = new Piso();
        piso.setRelleno( relleno );
        piso.setPeso( kilo );
        torta.setBase( piso );
        CucumberContext context = CucumberContext.getInstance();
        HttpResponse response = context.getClient()
                                        .execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_TORTA,
                                                                                      torta ) );
        CucumberContext.getInstance()
                .add( CucumberContext.ContentKeys.TORTA_URL, response.getFirstHeader( "Location" ).getValue() );
    }

    @Cuando("^la agrego al carrito$")
    public void la_agrego_al_carrito() throws Throwable {
        CucumberContext context = CucumberContext.getInstance();
        String location = context.get( CucumberContext.ContentKeys.TORTA_URL );
        HttpResponse response = context.getClient().execute( new HttpGet( location ) );
        Torta torta = context.getObjectMapper()
                              .readValue( EntityUtils.toString( response.getEntity() ), Torta.class );
        Carrito carrito = new Carrito();
        carrito.addContenido( torta );
        response = context.getClient()
                .execute( HttpClientHelper
                                  .postStringEntity( String.format( HttpClientHelper.RECURSO_CARRITO_USUARIO,
                                                                    context.get( CucumberContext.ContentKeys.USER_ID ) ),
                                                     carrito ) );

    }
}
