package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosDado {
    public static final String TEST_MAIL_COM = "@test-mail.com";
    private static Logger logger = Logger.getLogger( PasosDado.class.getName() );
    CucumberContext context;
    HttpClient client;

    @Before
    public void reset() {
        context = CucumberContext.getInstance();
        context.reset();
        client = context.getClient();
    }


    @Dado( "^que quiero comprar una torta usando$" )
    public void quiero_comprar() {
    }

    @Dado( "^una cobertura de '(.+)' con precio '(\\d+)'$" )
    public void cobertura_con_precio( String cobertura, Long coberturaPrecio ) throws Throwable {
        Cobertura cbt = new Cobertura();
        cbt.setTipo( cobertura );
        cbt.setPrecio( coberturaPrecio );

        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_COBERTURA,
                                                                                   cbt ) );
        cbt.setId( Long.parseLong( response.getEntity() ) );
        context.add( CucumberContext.ContentKeys.COBERTURA_BASE, cbt );
    }

    @Dado( "^un relleno de '(.+)' con precio '(\\d+)'$" )
    public void relleno_con_precio( String relleno, Long rellenoPrecio ) throws Throwable {
        Relleno rel = new Relleno();
        rel.setTipo( relleno );
        rel.setPrecio( rellenoPrecio );

        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_RELLENO,
                                                                                    rel  ) );
        rel.setId( Long.parseLong( response.getEntity() ) );
        context.add( CucumberContext.ContentKeys.RELLENO_BASE, rel );
    }

    @Dado( "^un costo por kilo de '(.+)'$" )
    public void costo_por_kilo( String porKilo ) throws Throwable {
        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper
                                                        .postStringEntity( HttpClientHelper.RECURSO_TORTA_POR_KILO,
                                                                           porKilo, MediaType.TEXT_PLAIN ) );

    }

    @Dado("^soy un usuario nuevo$")
    public void soy_un_usuario_nuevo() throws Throwable {
        String login = RandomStringUtils.randomAlphabetic( 8 );
        Usuario usuario = new Usuario();
        usuario.setLogin( login );
        usuario.setEmail( login + TEST_MAIL_COM );
        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper
                                                                  .postStringEntity( HttpClientHelper.RECURSO_USUARIO,
                                                                                     usuario ) );
        context.add( CucumberContext.ContentKeys.USER_ID, response.getEntity() );
    }

    @Dado("^soy un usuario que ya hizo una compra$")
    public void soy_un_usuario_que_ya_hizo_una_compra() throws Throwable {
        HttpClientHelper.Response response = HttpClientHelper.execute( new HttpGet( HttpClientHelper.RECURSO_USUARIO ) );
        List<Usuario> usuarios = CucumberContext.getInstance().getObjectMapper().readValue( response.getEntity(), new TypeReference< List <Usuario>>(){} );
        Usuario usuario = null;
        for( Usuario temp : usuarios ) {
            if( temp.getEmail().contains( TEST_MAIL_COM  ) && temp.getCarritos().size() > 0 ) {
                usuario = temp;
                break;
            }
        }
        assertTrue("Debe existir al menos un usuario de prueba", null != usuario );
        context.add( CucumberContext.ContentKeys.USER_ID, usuario.getId().toString() );
    }

    @Dado("^que agrego una nueva torta$")
    public void que_agrego_una_nueva_torta() throws Throwable {
        Torta torta = new Torta();

        HttpClientHelper.Response response = HttpClientHelper.execute( HttpClientHelper.postStringEntity(
                HttpClientHelper.RECURSO_TORTA, torta) );
        torta.setId( Long.parseLong( response.getEntity() ) );
        CucumberContext.getInstance().add( CucumberContext.ContentKeys.TORTA, torta );
    }

}
