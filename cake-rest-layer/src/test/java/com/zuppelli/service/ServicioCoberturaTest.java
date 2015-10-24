package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.repository.RepositorioGenerico;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.expect;

public class ServicioCoberturaTest {
    private ServicioCobertura servicioCobertura = new ServicioCobertura();
    private RepositorioGenerico<Cobertura> mockRepositorioGenerico;
    private IMocksControl control;
    private Cobertura cobertura;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        mockRepositorioGenerico = control.createMock( RepositorioGenerico.class );
        servicioCobertura.setRepositorioGenerico( mockRepositorioGenerico );
        cobertura = new Cobertura();
        cobertura.setId( 10L );
    }

    @After
    public void reset() {
        control.reset();
    }

    @Test
    public void testStore() {
        expect( mockRepositorioGenerico.store( cobertura ) ).andReturn( cobertura );
        control.replay();
        servicioCobertura.store( cobertura );
        control.verify();
    }

    @Test
    public void testRemove() {
        mockRepositorioGenerico.remove( cobertura.getId(), Cobertura.class );
        control.replay();
        servicioCobertura.remove( cobertura.getId() );
        control.verify();
    }

    @Test
    public void testGetOne() {
        expect( mockRepositorioGenerico.retrieve( cobertura.getId(), Cobertura.class ) ).andReturn( cobertura );
        control.replay();
        servicioCobertura.get( cobertura.getId() );
        control.verify();
    }

    @Test
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Cobertura.class ) ).andReturn( Arrays.asList( cobertura ) );
        control.replay();
        servicioCobertura.get();
        control.verify();
    }
}
