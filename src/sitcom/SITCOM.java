
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
import org.jfree.text.TextBox;
import org.jfree.ui.action.ActionButton;

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
    GridBagConstraints c;
    TextField week1;
    TextField week2;
    JComboBox macombo ;
    JComboBox macombo2 ;
           
    public SITCOM() throws SQLException {
        fenetre = new JFrame();
        macombo = new JComboBox();
        macombo2 = new JComboBox();
        bd = new BDD();
        pan = new JPanel();
        c = new GridBagConstraints();
        week1=new TextField();
        week2=new TextField();
        construireFenetre();

        //bd.donneemployee();
        l2.add("cat");
        //fenetreemployee();
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

        JLabel semaine1=new JLabel("Date de début :");
 
        JLabel semaine2=new JLabel("Date de fin :");
        JLabel poids=new JLabel("Poids :");
        JLabel cam=new JLabel("Camion :");
        JButton valide=new JButton("Valider");

        GridBagLayout grid= new GridBagLayout();
        pan.setLayout(grid);

       /* macombo.addActionListener(this);
        macombo.setActionCommand(COMMANDE_POIDS);
        macombo2.addActionListener(this);
        macombo2.setActionCommand(COMMANDE_NUMERO);*/

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
        c.gridwidth=1;
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setBounds(10, 10, 500, 500);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pan.add(semaine1,c);
        c.gridx = 1;
        c.gridy = 0;
        pan.add(week1,c);
        c.gridx = 0;
        c.gridy = 1;
        pan.add(semaine2,c);
        c.gridx = 1;
        c.gridy = 1;
        pan.add(week2,c);
        c.gridx = 0;
        c.gridy = 2;
        pan.add(poids,c);
        c.gridx = 1;
        c.gridy = 2;
        pan.add(macombo,c);
        c.gridx = 0;
        c.gridy = 3;
        pan.add(cam,c);
        
        c.gridx = 1;
        c.gridy = 3;
        pan.add(macombo2,c);
        valide.addActionListener(this);
        histogramme = new camion("Histogramme", "Poids", "Nombres d'occurences", valeurs, Color.red, l2, l1, true);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth=2;
        pan.add(histogramme,c);        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth=1;
        pan.add(valide,c);

        fenetre.add(pan);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

            int num;

            String itemselected = (String) macombo.getSelectedItem();

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
                    

                    /*try {
                     valeurs = bd.recupdonne(pas, tranche);
                     } catch (SQLException ex) {
                     Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                     }*/
                   
                    }

                   itemselected=(String)macombo2.getSelectedItem();
                    num = Integer.parseInt(itemselected);
                    switch (num) {
                        case 0:
                            try {
                                valeurs = bd.recupdonne(pas, tranche,week1.getText(),week2.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        default:
                            try {
                                valeurs = bd.rechcamion(pas, tranche, num,week1.getText(),week2.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(SITCOM.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                    //camion prev = histogramme;
                    //histogramme = new camion("Histogramme", "Poids", "Nombres d'occurences", valeurs, Color.red, l2, l1, true);

                    //pan.remove(prev);
                    //pan.add(histogramme);

                    camion prev = histogramme;
                    histogramme = new camion("Histogramme", "Poids", "Nombres d'occurences", valeurs, Color.red, l2, l1, true);
                    c.gridx = 0;
                    c.gridy = 4;
                    c.gridwidth=2;
                    pan.remove(prev);
                    pan.add(histogramme,c);
                    fenetre.repaint();
                    fenetre.pack();
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        SITCOM sit = new SITCOM();
    }
}
