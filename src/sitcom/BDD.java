/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Classe pour la connection à la BDD*/
package sitcom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxime Avillach
 */
public class BDD {

    Connection con;
    Statement stm;
    ResultSet results;

    public BDD() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/movedb";
        String user = "root";
        String psswd = "";

        con = DriverManager.getConnection(url, user, psswd);

        stm = con.createStatement();

    }

    public List<Float> recupdonne(float pas, int tranche) throws SQLException {
        List<Float> valeurs = new ArrayList<Float>();
        float cpt[] = new float[tranche + 1];
        float borneinf = 0;
        float bornesup = pas;
        int i = 1;
        String query = "";
        while (bornesup <= 20000000) {

            query = "Select * from effectue where PoidsNet>" + borneinf + " and PoidsNet<= " + bornesup;
            results = stm.executeQuery(query);

            while (results.next()) {
                cpt[i]++;
            }
            //System.out.println(cpt[i]);
            bornesup = bornesup + pas;
            borneinf = borneinf + pas;
            i++;
        }
        query = "Select * from effectue where PoidsNet > 20000000";
        results = stm.executeQuery(query);

        while (results.next()) {
            cpt[i]++;
        }
        //System.out.println(cpt[i]);
        for (int j = 1; j <= tranche; j++) {
            valeurs.add(cpt[j]);
        }

        return valeurs;
    }

    /**
     *
     * @param pas
     * @param tranche
     * @return
     * @throws java.sql.SQLException
     */
    public List<Float> rechcamion(float pas, int tranche, int num) throws SQLException {
        List<Float> valeurs = new ArrayList<Float>();
        float cpt[] = new float[tranche + 1];
        float borneinf = 0;
        float bornesup = pas;
        int i = 1;
        String query = "";
        while (bornesup <= 20000000) {

            query = "Select * from effectue where PoidsNet>" + borneinf + " and PoidsNet<= " + bornesup + " and IdVehicule = " + num;
            results = stm.executeQuery(query);

            while (results.next()) {
                cpt[i]++;
            }
            //System.out.println(cpt[i]);
            bornesup = bornesup + pas;
            borneinf = borneinf + pas;
            i++;
        }
        query = "Select * from effectue where PoidsNet > 20000000";
        results = stm.executeQuery(query);

        while (results.next()) {
            cpt[i]++;
        }
        //System.out.println(cpt[i]);
        for (int j = 1; j <= tranche; j++) {
            valeurs.add(cpt[j]);
        }

        return valeurs;
    }

    public void donneemployee(List<Float> valeurs, List<String> nom) throws SQLException {

        String query = "SELECT e.IdChauffeur, Nom, SUM(TIMESTAMPDIFF(HOUR, e.HeureDepart, e.HeureArrivee) )\n"
                + "	FROM movedb.effectue e\n"
                + "	INNER JOIN chauffeur c on c.IdChauffeur = e.IdChauffeur\n"
                + "	GROUP BY e.IdChauffeur, c.nom";
        results = stm.executeQuery(query);
        while (results.next()) {
            valeurs.add(results.getFloat(3));
            nom.add(results.getString(2));
        }
    }

}