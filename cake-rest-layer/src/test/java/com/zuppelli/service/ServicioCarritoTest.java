package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.cake.modelo.comercio.Usuario;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.repository.RepositorioGenerico;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.expect;

public class ServicioCarritoTest {
    private ServicioCarrito servicioCarrito = new ServicioCarrito();
    private RepositorioGenerico<Carrito> mockRepositorioGenerico;
    private IMocksControl control;
    private Carrito carrito;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        mockRepositorioGenerico = control.createMock( RepositorioGenerico.class );
        servicioCarrito.setRepositorioGenerico( mockRepositorioGenerico );
        carrito = creaCarrito();

    }

    @After
    public void reset() {
        control.reset();
    }

    @Test
    public void testStore() {
        expect( mockRepositorioGenerico.store( carrito ) ).andReturn( carrito );
        control.replay();
        servicioCarrito.store( carrito );
        control.verify();
    }

    @Test
    public void testRemove() {
        mockRepositorioGenerico.remove( carrito.getId(), Carrito.class );
        control.replay();
        servicioCarrito.remove( carrito.getId() );
        control.verify();
    }

    @Test
    public void testGetOne() {
        expect( mockRepositorioGenerico.retrieve( carrito.getId(), Carrito.class ) ).andReturn( carrito );
        control.replay();
        servicioCarrito.get( carrito.getId() );
        control.verify();
    }

    @Test
    public void testGetWithParent() {
        expect( mockRepositorioGenerico.retrieve( carrito.getId(), Carrito.class ) ).andReturn( carrito );
        control.replay();
        servicioCarrito.get( new Usuario(), carrito.getId() );
        control.verify();
    }

    @Test
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Carrito.class ) ).andReturn( Arrays.asList( carrito ) );
        control.replay();
        servicioCarrito.get();
        control.verify();
    }

    private Carrito creaCarrito() {
        Carrito carrito = new Carrito();
        carrito.setId( 10L );
        Torta torta = new Torta();
        carrito.addContenido( torta );
        Piso base = new Piso();
        base.setPeso( 1 );
        torta.setBase( base );

        return carrito;
    }
}
