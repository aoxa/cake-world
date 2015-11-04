package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.repository.RepositorioGenerico;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.expect;

public class ServicioRellenoTest {
    private ServicioRelleno servicioRelleno = new ServicioRelleno();
    private RepositorioGenerico<Relleno> mockRepositorioGenerico;
    private IMocksControl control;
    private Relleno relleno;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        mockRepositorioGenerico = control.createMock( RepositorioGenerico.class );
        servicioRelleno.setRepositorioGenerico( mockRepositorioGenerico );
        relleno = new Relleno();
        relleno.setId( 10L );
    }

    @After
    public void reset() {
        control.reset();
    }

    @Test
    public void testStore() {
        expect( mockRepositorioGenerico.store( relleno ) ).andReturn( relleno );
        control.replay();
        servicioRelleno.store( relleno );
        control.verify();
    }

    @Test
    public void testRemove() {
        mockRepositorioGenerico.remove( relleno.getId(), Relleno.class );
        control.replay();
        servicioRelleno.remove( relleno.getId() );
        control.verify();
    }

    @Test
    public void testGetOne() {
        expect( mockRepositorioGenerico.retrieve( relleno.getId(), Relleno.class ) ).andReturn( relleno );
        control.replay();
        servicioRelleno.get( relleno.getId() );
        control.verify();
    }

    @Test
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Relleno.class ) ).andReturn( Arrays.asList( relleno ) );
        control.replay();
        servicioRelleno.get();
        control.verify();
    }
}
