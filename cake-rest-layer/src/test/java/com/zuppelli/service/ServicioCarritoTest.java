package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Carrito;
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
        carrito = new Carrito();
        carrito.setId( 10L );
        carrito.addContenido( new Torta() );
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
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Carrito.class ) ).andReturn( Arrays.asList( carrito ) );
        control.replay();
        servicioCarrito.get();
        control.verify();
    }
}
