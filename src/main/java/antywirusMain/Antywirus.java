package antywirusMain;

import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;

import java.sql.*;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class Antywirus {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:antywirusDB.db";
    private Connection conn;
    private Statement stat;
    private PreparedStatement insertPakietStat;
    private PreparedStatement insertAntywirStat;
    private PreparedStatement selectPakietStat;
    private PreparedStatement selectAntywirStat;
    private PreparedStatement dropTablePakietStat;
    private PreparedStatement dropTableAntywirStat;
    private PreparedStatement deleteFromPakietStat;
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

            /*
            insertAntywirStat = conn.prepareStatement(
                    "insert into antywirus values ('Nazwa','opis opis opis',8.5,5);");
            insertPakietStat =conn.prepareStatement(
                    "INSERT INTO pakiet VALUES ('Pakiet','opis',150.99)");


            selectPakietStat=conn.prepareStatement
                    ("Select * from kategoria");
            selectAntywirStat=conn.prepareStatement
                    ("Select * from pakiet");
            deleteFromPakietStat=conn.prepareStatement(
                    "DELETE FROM kategoria where id_kategoria=1");
            deleteFromAntywirStat=conn.prepareStatement(
                    "DELETE FROM pakiet WHERE id_pakiet=1");
            dropTablePakietStat=conn.prepareStatement(
                    "DROP table kategoria");
            dropTableAntywirStat= conn.prepareStatement(
                    "DROP TABLE pakiet");
            */

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public boolean createTable(){

        String createAntywirString="CREATE TABLE IF NOT EXISTS antywirus ( id_nazwa INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nazwaAntywir TEXT, opis TEXT, ocena TEXT, IdPakiet LONG, FOREIGN KEY(IdPakiet) REFERENCES pakiet(id_pakiet))";
        String createPakietString="CREATE TABLE IF NOT EXISTS pakiet ( id_pakiet INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, pakiet TEXT, opis TEXT, cena DOUBLE)";

        try {
            stat.execute(createAntywirString);
            stat.execute(createPakietString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    }
