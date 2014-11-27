/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitcom;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *
 * @author Toshiba
 */
public class SITCOM implements ActionListener {

    //constantes
    private static final String COMMANDE_POIDS = "poids";
    private static final String COMMANDE_NUMERO = "numero";
    int pas;
    int tranche;
    List<Float> valeurs = new ArrayList<Float>();
    List<String> l1 = new ArrayList<String>();
    List<String> l2 = new ArrayList<String>();
    BDD bd;
    JPanel pan;
    JFrame fenetre;
    camion histogramme;
    Employee emp;

    public SITCOM() throws SQLException {
        fenetre = new JFrame();
        bd = new BDD();
        pan = new JPanel();
        //construireFenetre();
        //bd.donneemployee();
        l2.add("cat");
        fenetreemployee();
    }

    private void construireFenetre() throws SQLException {

        testFenetre();
        fenetre.pack();
        fenetre.setVisible(true);

    }

    private void fenetreemployee() throws SQLException {

        bd.donneemployee(valeurs, l1);
        emp = new Employee(valeurs, l1);
        pan.add(emp);
        fenetre.add(pan);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setBounds(10, 10, 500, 500);
        fenetre.pack();
        fenetre.setVisible(true);
    }

    public void testFenetre() {
        JComboBox macombo = new JComboBox();
        JComboBox macombo2 = new JComboBox();

        macombo.addActionListener(this);
        macombo.setActionCommand(COMMANDE_POIDS);
        macombo2.addActionListener(this);
        macombo2.setActionCommand(COMMANDE_NUMERO);

        macombo.addItem(null);
        macombo.addItem("10kg");
        macombo.addItem("100kg");
        macombo.addItem("1000kg");
        macombo2.addItem(null);
        macombo2.addItem("0");
        macombo2.addItem("1");
        macombo2.addItem("2");
        macombo2.addItem("3");
        macombo2.addItem("4");
        macombo2.addItem("5");

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setBounds(10, 10, 500, 500);

        pan.add(macombo);
        pan.add(macombo2);

        histogramme = new camion("Histogramme", "Poids", "Nombres d'occurences", valeurs, Color.red, l2, l1, true);
        pan.add(histogramme);
        fenetre.add(pan);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj instanceof JComboBox) {
            int num;
            JComboBox macombo = (JComboBox) obj;
            String itemselected = (String) macombo.getSelectedItem();
            String commande = e.getActionCommand();

            switch (commande) {
                case COMMANDE_POIDS:
                    int i = 1;
                    switch (itemselected) {
                        case "10kg":
                            pas = 10000;
                            tranche = 2001;
                            l1.clear();
                            while (i <= 2000) {
                                l1.add("" + i * 10);
                                i++;
                            }
                            l1.add("20000+");
                            break;

                        case "100kg":
                            pas = 100000;
                            tranche = 201;
                            l1.clear();
                            while (i <= 200) {
                                l1.add("" + i * 100);
                                i++;
                            }
                            l1.add("20000+");
                            break;

                        case "1000kg":
                            l1.clear();
                            pas = 1000000;
                            tranche = 21;
                            while (i <= 20) {
                                l1.add("" + i * 1000);
                                i++;
                            }
                            l1.add("20000+");
                            break;
                        default:
                            break;
                    }

                    /*try {
                     valeurs = bd.recupdonne(pas, tranche);
                     } catch (SQLException ex) {
                     Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                     }*/
                    break;

                case COMMANDE_NUMERO:
                    num = Integer.parseInt(itemselected);
                    switch (num) {
                        case 0:
                            try {
                                valeurs = bd.recupdonne(pas, tranche);
                            } catch (SQLException ex) {
                                Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        default:
                            try {
                                valeurs = bd.rechcamion(pas, tranche, num);
                            } catch (SQLException ex) {
                                Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                    camion prev = histogramme;
                    histogramme = new camion("Histogramme", "Poids", "Nombres d'occurences", valeurs, Color.red, l2, l1, true);

                    pan.remove(prev);
                    pan.add(histogramme);

                    fenetre.repaint();
                    fenetre.pack();
                    break;
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        SITCOM sit = new SITCOM();
    }
}
