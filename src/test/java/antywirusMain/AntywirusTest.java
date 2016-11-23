package antywirusMain;
import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;
import antywirusMain.Antywirus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Redbullek on 2016-11-23.
 */
public class AntywirusTest {

    Antywirus anty;

    @Before
    public void setUp() throws Exception {
        anty =new Antywirus();
    }
    @After
    public void tearDown() throws Exception {
        anty.dropTable();
        anty.closeCon();
    }

    @Test
    public void createTable() throws Exception {
        assertTrue(anty.createTable());
    }
    @Test
    public void testConn() throws Exception {
        Exception ex = null;
        try {
            new Antywirus();
        } catch (Exception e) {
            ex = e;
        }
        assertEquals(null,ex);
    }
    @Test
    public void insertInAntywir() throws Exception {
        TabelaPakiet m1=new TabelaPakiet();
        m1.setpakiet("Darmowy");
        m1.setOpis("Pakiet stosowany do użytku domowego, wersja darmowa");
        m1.setcena(0.00);
        anty.insertInPakiet(m1);
        List<TabelaPakiet> me=anty.selectPakiet();

        TabelaAntywir c1=new TabelaAntywir();
        c1.setNazwaAntywir("Awast");
        c1.setOpis("Antywirus do użytku domowego");
        c1.setocena(7.5);
        c1.setidpakiet(me.get(0).getid_pakiet());
        assertTrue(anty.insertInAntywir(c1));
        List<TabelaPakiet> ce=anty.selectPakiet();
        assertEquals("Darmowy",ce.get(0).getpakiet());


    }

}
