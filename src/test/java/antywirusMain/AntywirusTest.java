package antywirusMain;
import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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


    @Test
    public void insertInPakiet() throws Exception {
        TabelaPakiet m1=new TabelaPakiet();
        m1.setpakiet("darmowy");
        m1.setOpis("Antywirus do użytku domowego");
        m1.setcena(0.0);
        assertTrue(anty.insertInPakiet(m1));
        List<TabelaPakiet> me=anty.selectPakiet();
        assertEquals("darmowy",me.get(0).getpakiet());
    }

    @Test
    public void updateInPakiet() throws Exception {
        TabelaPakiet z1=new TabelaPakiet();
        z1.setpakiet("darmowy");
        z1.setOpis("Antywirus do użytku domowego");
        z1.setcena(0.0);
        assertTrue(anty.insertInPakiet(z1));
        List<TabelaPakiet> me=anty.selectPakiet();

        TabelaPakiet u1=new TabelaPakiet();
        u1.setpakiet("darmowy222");
        u1.setOpis(me.get(0).getOpis());
        u1.setcena(me.get(0).getcena());
        u1.setid_pakiet(me.get(0).getid_pakiet());
        assertTrue(anty.updateInPakiet(u1));
        List<TabelaPakiet> me2=anty.selectPakiet();
        assertEquals("darmowy222",me2.get(0).getpakiet());
    }

    @Test
    public void updateinAntywir() throws Exception {
        TabelaPakiet p1=new TabelaPakiet();
        p1.setpakiet("Darmowy");
        p1.setOpis("Pakiet stosowany do użytku domowego, wersja darmowa");
        p1.setcena(0.00);
        anty.insertInPakiet(p1);
        List<TabelaPakiet> me=anty.selectPakiet();

        TabelaAntywir l1=new TabelaAntywir();
        l1.setNazwaAntywir("Awast");
        l1.setOpis("Antywirus do użytku domowego");
        l1.setocena(7.5);
        l1.setidpakiet(me.get(0).getid_pakiet());
        assertTrue(anty.insertInAntywir(l1));
        List<TabelaAntywir> ce=anty.selectAntywir();

        TabelaAntywir u1=new TabelaAntywir();
        u1.setNazwaAntywir("Awast123");
        u1.setOpis(me.get(0).getOpis());
        u1.setocena(ce.get(0).getocena());
        u1.setidpakiet(ce.get(0).getidpakiet());
        u1.setid_nazwa(ce.get(0).getid_nazwa());
        assertTrue(anty.updateInAntywir(u1));
        List<TabelaAntywir> me2=anty.selectAntywir();
        assertEquals("Awast123",me2.get(0).getNazwaAntywir());
    }


    @Test
    public void selectPakiet() throws Exception {
        TabelaPakiet m1=new TabelaPakiet();
        m1.setpakiet("Ultimate");
        m1.setOpis("Najwyższa wersja");
        m1.setcena(399.99);
        anty.insertInPakiet(m1);
        List<TabelaPakiet> me=anty.selectPakiet();

        TabelaPakiet c1=new TabelaPakiet();
        c1.setpakiet("damowy");
        c1.setOpis("Do użytku domowego");
        c1.setcena(0.0);
        c1.setid_pakiet(me.get(0).getid_pakiet());
        TabelaPakiet c2=new TabelaPakiet();
        c2.setpakiet("firmowy");
        c2.setOpis("Dla firm");
        c2.setcena(100.0);
        c2.setid_pakiet(me.get(0).getid_pakiet());
        TabelaPakiet c3=new TabelaPakiet();
        c3.setpakiet("darmowy");
        c3.setOpis("Dla firm");
        c3.setcena(100.0);
        c3.setid_pakiet(me.get(0).getid_pakiet());
        assertTrue(anty.insertInPakiet(c1));
        assertTrue(anty.insertInPakiet(c2));
        assertTrue(anty.insertInPakiet(c3));

        List<TabelaPakiet> cl=anty.selectPakiet();

        assertEquals(4,cl.size());

    }

    @Test
    public void selectantywir() throws Exception {
        TabelaPakiet w1=new TabelaPakiet();
        w1.setpakiet("darmowy");
        w1.setOpis("Antywirus do użytku domowego");
        w1.setcena(0.0);
        TabelaPakiet w2=new TabelaPakiet();
        w2.setpakiet("Firmowy");
        w2.setOpis("Antywirus do użytku domowego");
        w2.setcena(199.99);
        assertTrue(anty.insertInPakiet(w1));
        assertTrue(anty.insertInPakiet(w2));
        List<TabelaPakiet> me=anty.selectPakiet();

        TabelaAntywir e1=new TabelaAntywir();
        e1.setNazwaAntywir("Ultimate");
        e1.setOpis("Antywirus najwyższa wersja");
        e1.setocena(8.5);
        e1.setidpakiet(me.get(0).getid_pakiet());
        TabelaAntywir e2=new TabelaAntywir();
        e2.setNazwaAntywir("Ultimateeeeeee");
        e2.setOpis("Antywirus najweeeeyższa wersja");
        e2.setocena(8.3);
        e2.setidpakiet(me.get(0).getid_pakiet());
        TabelaAntywir e3=new TabelaAntywir();
        e3.setNazwaAntywir("Ultimateeeeeee");
        e3.setOpis("Antywirus najweeeeyższa wersja");
        e3.setocena(8.3);
        e3.setidpakiet(me.get(1).getid_pakiet());

        assertTrue(anty.insertInAntywir(e1));
        assertTrue(anty.insertInAntywir(e2));
        assertTrue(anty.insertInAntywir(e3));
        List<TabelaAntywir> me2=anty.selectAntywir();
        assertEquals(3,me2.size());
    }

    @Test
    public void deleteFromPakiet() throws Exception {

        TabelaPakiet m1=new TabelaPakiet();
        m1.setpakiet("Darmowy");
        m1.setOpis("Opis");
        m1.setcena(0.0);
        anty.insertInPakiet(m1);
        TabelaPakiet m2=new TabelaPakiet();
        m2.setpakiet("Darcccmowy");
        m2.setOpis("Opcccis");
        m2.setcena(10.0);
        anty.insertInPakiet(m2);
        List<TabelaPakiet> listPak=anty.selectPakiet();
        assertTrue(anty.deleteFromPakiet(listPak.get(0)));
        listPak=anty.selectPakiet();
        assertEquals(1,listPak.size());
    }

    @Test
    public void deleteFromAntywir() throws Exception {

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

        List<TabelaAntywir> listAntywir=anty.selectAntywir();
        assertTrue(anty.deleteFromAntywir(listAntywir.get(0)));
        listAntywir=anty.selectAntywir();
        assertEquals(0,listAntywir.size());
    }


    @Test
    public void dropTable() throws Exception {
        assertTrue(anty.dropTable());
        TabelaPakiet m1=new TabelaPakiet();
        m1.setpakiet("Nowyy");
        m1.setOpis("Nowy");
        m1.setcena(0.0);
        assertFalse(anty.insertInPakiet(m1));
    }

    @Test
    public void closeCon() throws Exception {
        assertTrue(anty.closeCon());
        assertFalse(anty.createTable());
    }
}
