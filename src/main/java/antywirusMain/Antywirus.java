package antywirusMain;

import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class Antywirus {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:antywirusDB.db";
    private Connection conn;
    private Statement stat;
    private PreparedStatement insertpakietStat;
    private PreparedStatement insertAntywirStat;
    private PreparedStatement selectpakietStat;
    private PreparedStatement selectAntywirStat;
    private PreparedStatement selectAntywirStatt;
    private PreparedStatement dropTablepakietStat;
    private PreparedStatement dropTableAntywirStat;
    private PreparedStatement deleteFrompakietStat;
    private PreparedStatement deleteFromAntywirStat;
    private PreparedStatement updateantywirStat;
    private PreparedStatement updatepakietStat;


    public Antywirus (){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
            createTable();



            insertpakietStat =conn.prepareStatement(
                    "INSERT INTO pakiet VALUES (NULL,?,?,?)");
            insertAntywirStat = conn.prepareStatement(
                    "insert into antywirus values (NULL,?,?,?,?);");
            selectpakietStat=conn.prepareStatement
                    ("Select * from pakiet");
            selectAntywirStat=conn.prepareStatement
                    ("Select * from antywirus");
            deleteFrompakietStat=conn.prepareStatement(
                    "DELETE FROM pakiet where id_pakiet=?");
            deleteFromAntywirStat=conn.prepareStatement(
                    "DELETE FROM antywirus WHERE id_nazwa=?");

            updateantywirStat=conn.prepareStatement(
                    "UPDATE antywirus SET nazwaAntywir=?,opis=?,ocena=?,idpakiet=? WHERE id_nazwa=?");
            updatepakietStat=conn.prepareStatement(
                    "UPDATE pakiet SET pakiet=?,opis=?,cena=? WHERE id_pakiet=?");

            dropTablepakietStat=conn.prepareStatement(
                    "DROP table pakiet");
            dropTableAntywirStat= conn.prepareStatement(
                    "DROP TABLE antywirus");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    //-----------------------------------------------------------------------------------------------
    //Funkcje
    //-----------------------------------------------------------------------------------------------


    public boolean createTable(){

        String createAntywirString="CREATE TABLE IF NOT EXISTS antywirus ( id_nazwa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwaAntywir TEXT, opis TEXT, ocena TEXT, idpakiet LONG, FOREIGN KEY(idpakiet) REFERENCES pakiet(id_pakiet))";
        String createpakietString="CREATE TABLE IF NOT EXISTS pakiet ( id_pakiet INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, pakiet TEXT, opis TEXT, cena DOUBLE)";

        try {
            stat.execute(createAntywirString);
            stat.execute(createpakietString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertInAntywir(TabelaAntywir ant){
        try {
            insertAntywirStat.setString(1, ant.getNazwaAntywir());
            insertAntywirStat.setString(2, ant.getOpis());
            insertAntywirStat.setDouble(3, ant.getocena());
            insertAntywirStat.setLong(4,ant.getidpakiet());
            insertAntywirStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean insertInPakiet(TabelaPakiet pak){
        try {
            insertpakietStat.setString(1, pak.getpakiet());
            insertpakietStat.setString(2, pak.getOpis());
            insertpakietStat.setDouble(3, pak.getcena());
            insertpakietStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateInPakiet(TabelaPakiet pak){
        try {
            updatepakietStat.setString(1, pak.getpakiet());
            updatepakietStat.setString(2, pak.getOpis());
            updatepakietStat.setDouble(3, pak.getcena());
            updatepakietStat.setLong(4, pak.getid_pakiet());
            updatepakietStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateInAntywir(TabelaAntywir anty){
        try {
            updateantywirStat.setString(1, anty.getNazwaAntywir());
            updateantywirStat.setString(2, anty.getOpis());
            updateantywirStat.setDouble(3, anty.getocena());
            updateantywirStat.setLong(4, anty.getidpakiet());
            updateantywirStat.setLong(5, anty.getid_nazwa());
            updateantywirStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<TabelaAntywir> selectAntywir(){
        List<TabelaAntywir> allAntywir =new ArrayList();
        try {
            ResultSet resultSet = selectAntywirStat.executeQuery();
            while (resultSet.next()) {
                TabelaAntywir c=new TabelaAntywir();
                c.setid_nazwa(resultSet.getLong("id_nazwa"));
                c.setNazwaAntywir(resultSet.getString("nazwaAntywir"));
                c.setOpis(resultSet.getString("opis"));
                c.setocena(resultSet.getDouble("ocena"));
                c.setidpakiet(resultSet.getLong("idpakiet"));
                allAntywir.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAntywir;
    }

    public List<TabelaPakiet> selectPakiet(){
        List<TabelaPakiet> allPakiet =new ArrayList();
        try {
            ResultSet resultSet = selectpakietStat.executeQuery();
            while (resultSet.next()) {
                TabelaPakiet m=new TabelaPakiet();
                m.setid_pakiet(resultSet.getLong("id_pakiet"));
                m.setpakiet(resultSet.getString("pakiet"));
                m.setOpis(resultSet.getString("opis"));
                m.setcena(resultSet.getDouble("cena"));
                allPakiet.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPakiet;
    }


    public boolean deleteFromPakiet(TabelaPakiet idpak){
        try {
            deleteFrompakietStat.setLong(1, idpak.getid_pakiet());
            deleteFrompakietStat.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public boolean deleteFromAntywir(TabelaAntywir idant){
        try {
            deleteFromAntywirStat.setLong(1,idant.getid_nazwa());
            deleteFromAntywirStat.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }


    public boolean dropTable(){
        try {

            dropTableAntywirStat.execute();
            dropTablepakietStat.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeCon(){
        try {
            conn.close();

        } catch(SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    }
