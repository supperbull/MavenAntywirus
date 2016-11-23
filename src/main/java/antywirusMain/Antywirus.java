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
    private PreparedStatement dropTablepakietStat;
    private PreparedStatement dropTableAntywirStat;
    private PreparedStatement deleteFrompakietStat;
    private PreparedStatement deleteFromAntywirStat;


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


            insertAntywirStat = conn.prepareStatement(
                    "insert into antywirus values ('Nazwa','opis opis opis',8.5,5);");
            insertpakietStat =conn.prepareStatement(
                    "INSERT INTO pakiet VALUES ('pakiet','opis',150.99)");
            selectpakietStat=conn.prepareStatement
                    ("Select * from kategoria");
            selectAntywirStat=conn.prepareStatement
                    ("Select * from pakiet");
            deleteFrompakietStat=conn.prepareStatement(
                    "DELETE FROM kategoria where id_kategoria=1");
            deleteFromAntywirStat=conn.prepareStatement(
                    "DELETE FROM pakiet WHERE id_pakiet=1");
            dropTablepakietStat=conn.prepareStatement(
                    "DROP table kategoria");
            dropTableAntywirStat= conn.prepareStatement(
                    "DROP TABLE pakiet");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    //-----------------------------------------------------------------------------------------------
    //Funkcje



    public boolean createTable(){

        String createAntywirString="CREATE TABLE IF NOT EXISTS antywirus ( id_nazwa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwaAntywir TEXT, opis TEXT, ocena TEXT, Idpakiet LONG, FOREIGN KEY(Idpakiet) REFERENCES pakiet(id_pakiet))";
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

/*
    public boolean insertInAntywir(TabelaAntywir ant){
        try {
            insertAntywirStat.setString(1, ant.getNazwaAntywir());
            insertAntywirStat.setString(2, ant.getOpis());
            insertAntywirStat.setDouble(3, ant.getocena());
            insertAntywirStat.setLong(4,ant.getIdpakiet());
            insertAntywirStat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertInPakiet(Tabelapakiet pak){
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
*/

    }
