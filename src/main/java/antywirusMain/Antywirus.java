package antywirusMain;

import antywirusTabele.TabelaAntywir;
import antywirusTabele.TabelaPakiet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Redbullek on 2016-11-22.
 */
public class Antywirus {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:antywirusDB.db";
    private Connection conn;
    private Statement stat;


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
