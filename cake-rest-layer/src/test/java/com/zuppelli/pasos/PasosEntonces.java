package com.zuppelli.pasos;

import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.es.Entonces;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jettison.json.JSONObject;

import static org.junit.Assert.assertEquals;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosEntonces {
    @Entonces("^debo pagar '(\\d+)'$")
    public void debo_pagar(Double esperado) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String location = CucumberContext.getInstance().get( CucumberContext.ContentKeys.TORTA_URL );
        HttpClientHelper.Response response = HttpClientHelper.execute( new HttpGet( location ) );
        JSONObject result = new JSONObject( response.getEntity() );
        Double actual = result.getDouble( "precio" );
        assertEquals( "Los valores esperado y el corriente son distintos.", esperado, actual );
    }

    @Entonces("^debo pagar '(\\d+)' en el carrito con descuento '(.+)'$")
    public void debo_pagar_en_el_carrito( Double precio, String descuento ) throws Throwable {
        Double esperado = precio * (1-Double.parseDouble( descuento ) );
        CucumberContext context = CucumberContext.getInstance();

        HttpClientHelper.Response response = HttpClientHelper
                                        .execute(new HttpGet(context.get(CucumberContext.ContentKeys.CARRITO_URL)
                                                .toString()));
        JSONObject object = new JSONObject( response.getEntity() );
        Double corriente = object.getDouble( "precio" );

        assertEquals( "El precio esperado no es el recuperado.", esperado, corriente );
    }

    @Entonces("^debo pagar '(\\d+)' en el carrito$")
    public void debo_pagar_en_el_carrito(Double esperado) throws Throwable {
        CucumberContext context = CucumberContext.getInstance();

        HttpClientHelper.Response response = HttpClientHelper
                .execute(new HttpGet(context.get(CucumberContext.ContentKeys.CARRITO_URL)
                        .toString()));
        JSONObject object = new JSONObject( response.getEntity() );
        Double corriente = object.getDouble( "precio" );

        assertEquals( "El precio esperado no es el recuperado.", esperado, corriente );
    }

    @Entonces("^recuperarla y validar el contenido$")
    public void recuperarla_y_validar_el_contenido() throws Throwable {
        HttpClientHelper.Response response = HttpClientHelper
                .execute(new HttpGet( CucumberContext.getInstance().get(CucumberContext.ContentKeys.TORTA_URL)
                        .toString()));
        Torta corriente = CucumberContext.getInstance().getObjectMapper().readValue(response.getEntity(), Torta.class);
        assertEquals( CucumberContext.getInstance().get( CucumberContext.ContentKeys.TORTA ), corriente );
    }


    @Entonces("^debo pagar '(\\d+)' en el carrito con descuento '(.+)' y '(.+)' por cantidad$")
    public void debo_pagar_en_el_carrito_con_descuento_y_por_cantidad(Double precio, String descuento, String porCantidad) throws Throwable {
        Double esperado = precio -  precio * (Double.parseDouble( descuento ) ) - precio * Double.parseDouble( porCantidad );
        CucumberContext context = CucumberContext.getInstance();

        HttpClientHelper.Response response = HttpClientHelper
                .execute(new HttpGet(context.get(CucumberContext.ContentKeys.CARRITO_URL)
                        .toString()));
        JSONObject object = new JSONObject( response.getEntity() );
        Double corriente = object.getDouble( "precio" );

        assertEquals( "El precio esperado no es el recuperado.", esperado, corriente );
    }

}
