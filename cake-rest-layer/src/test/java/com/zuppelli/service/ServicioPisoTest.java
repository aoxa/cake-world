package com.zuppelli.service;

import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.repository.RepositorioGenerico;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.expect;

public class ServicioPisoTest {
    private ServicioPiso servicioPiso = new ServicioPiso();
    private RepositorioGenerico<Piso> mockRepositorioGenerico;
    private IMocksControl control;
    private Piso piso;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        mockRepositorioGenerico = control.createMock( RepositorioGenerico.class );
        servicioPiso.setRepositorioGenerico( mockRepositorioGenerico );
        piso = new Piso();
        piso.setId( 10L );
    }

    @After
    public void reset() {
        control.reset();
    }

    @Test
    public void testStore() {
        expect( mockRepositorioGenerico.store( piso ) ).andReturn( piso );
        control.replay();
        servicioPiso.store( piso );
        control.verify();
    }

    @Test
    public void testRemove() {
        mockRepositorioGenerico.remove( piso.getId(), Piso.class );
        control.replay();
        servicioPiso.remove( piso.getId() );
        control.verify();
    }

    @Test
    public void testGetOne() {
        expect( mockRepositorioGenerico.retrieve( piso.getId(), Piso.class ) ).andReturn( piso );
        control.replay();
        servicioPiso.get( piso.getId() );
        control.verify();
    }

    @Test
    public void testGetAll() {
        expect( mockRepositorioGenerico.retrieve( Piso.class ) ).andReturn( Arrays.asList( piso ) );
        control.replay();
        servicioPiso.get();
        control.verify();
    }
}
