package antywirusMain;
import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;
import org.junit.After;
import org.junit.Before;

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

}
