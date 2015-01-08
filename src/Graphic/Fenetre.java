
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import Bdd.BDD;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.*;
import static java.awt.GridBagConstraints.CENTER;
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
public class Fenetre extends JFrame implements ActionListener {

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
    //JFrame fenetre;
    camion histogramme;
    Employee emp;
    Poids pds;
    camembert cam;
    GridBagConstraints c;
    TextField week1;
    TextField week2;
    JComboBox macombo ;
    JComboBox macombo2 ;
    JButton valide;
    JButton validePoids;
    JButton valideemployee;
    JButton validecam;
    JMenuBar menubar;
    JLabel image;
           
    public Fenetre() throws SQLException {
        //fenetre = new JFrame();
        macombo = new JComboBox();
        macombo2 = new JComboBox();
        bd = new BDD();
        pan = new JPanel();
        GridBagLayout grid= new GridBagLayout();
        pan.setLayout(grid);
        c = new GridBagConstraints();
        week1=new TextField();
        week2=new TextField();
        menubar=new JMenuBar();
        image = new JLabel( new ImageIcon( "logo.jpg"));
        construireFenetre();
        
        //bd.donneemployee();
        l2.add("cat");
        //fenetreemployee();
    }
    
    private void ajoutmenu()
    {
        JMenu menu=new JMenu("Menu");
        
        JMenuItem camion = new JMenuItem("Gestion des camions");
        camion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                pan.removeAll();
                fenetrecamion();
            }
        });
        menu.add(camion);
        JMenuItem employee = new JMenuItem("Gestion des employées");
        
        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    pan.removeAll();
                    fenetreemployee();
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu.add(employee);
        JMenuItem tournee = new JMenuItem("Gestion des tournées");
        tournee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    pan.removeAll();
                    fenetrepoids();
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu.add(tournee);
        
        JMenuItem repart = new JMenuItem("Utilisation des camions");
        repart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   pan.removeAll();
                   fenetrecam();
               }
               catch(SQLException ex){
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        });
        menu.add(repart);
        menubar.add(menu);
        pan.add(menubar);
        
        setJMenuBar(menubar);
        add(pan);
        
    }
    
    private void ajoutLogo()
    {
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx = 1;
        c.gridy = 1;
        pan.add(image,c);
    }

    private void construireFenetre() throws SQLException {

        //fenetreemployee();
        //testFenetre();
        //fenetrepoids();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(0, 0, 1000, 1000);
        ajoutmenu();
        ajoutLogo();
        
        this.pack();
        //this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);

    }
        private void fenetrepoids() throws SQLException {
        JLabel jour=new JLabel("Jour de la semaine :");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridx = 0;
        c.gridy = 0;
        pan.add(jour,c);
        c.gridx = 1;
        c.gridy = 0;      
        pan.add(week1,c);

        pds = new Poids(valeurs, l1);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=2;
        pan.add(pds,c);
        validePoids=new JButton("Valider");
        validePoids.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth=1;
        pan.add(validePoids,c);
        add(pan);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(10, 10, 500, 500);
        pack();
        setVisible(true);
    }
        
    private void fenetreemployee() throws SQLException {
        valeurs.clear();
        JLabel semaine1=new JLabel("Date de début :");
 
        JLabel semaine2=new JLabel("Date de fin :");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
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
        emp = new Employee(valeurs, l1);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth=2;
        pan.add(emp,c);
        
        valideemployee=new JButton("Valider");
        valideemployee.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth=1;
        pan.add(valideemployee,c);
        add(pan);
        pack();
        setVisible(true);
    }
    
    public void fenetrecam() throws SQLException{
        valeurs.clear();
        
        JLabel semaine1=new JLabel("Date de début :");
 
        JLabel semaine2=new JLabel("Date de fin :");
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
        cam=new camembert(valeurs);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth=2;
        pan.add(cam,c);
        validecam=new JButton("Valider");
        validecam.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth=1;
        pan.add(validecam,c);
        add(pan);
        pack();
        setVisible(true);
    }

    public void fenetrecamion() {
     
        JLabel semaine1=new JLabel("Date de début :");
 
        JLabel semaine2=new JLabel("Date de fin :");
        JLabel poids=new JLabel("Poids :");
        JLabel cam=new JLabel("Camion :");
        valide=new JButton("Valider");
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
        
        add(pan);
        
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == valide)
        {
        int num;
        
        
        valeurs.clear();
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
                     Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                     }*/
                   
                    }

                   itemselected=(String)macombo2.getSelectedItem();
                    num = Integer.parseInt(itemselected);
                    switch (num) {
                        case 0:
                            try {
                                valeurs = bd.recupdonne(pas, tranche,week1.getText(),week2.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        default:
                            
                            try {
                                valeurs = bd.rechcamion(pas, tranche, num,week1.getText(),week2.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
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
                    repaint();
                    pack();
        }
        else if (e.getSource()==valideemployee)
        {
            valeurs.clear();
            l1.clear();
            try {
                bd.donneemployee(valeurs, l1,week1.getText(),week2.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            Employee prev=emp;
            
            emp = new Employee(valeurs, l1);
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth=2;
            pan.remove(prev);
            pan.add(emp,c); 
            repaint();
            pack();
        }
        else if(e.getSource()== validePoids)
        {
            valeurs.clear();
            l1.clear();
            try {
                bd.poidsparjour(valeurs, l1,week1.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            Poids prev=pds;
            pds = new Poids(valeurs, l1);
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth=2;
            pan.remove(prev);
            pan.add(pds,c); 
            repaint();
            pack();
        }
        
        else if (e.getSource()==validecam)
        {
            valeurs.clear();
            try {
                bd.repart(valeurs,week1.getText() ,week2.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            camembert prev=cam;
            cam=new camembert(valeurs);
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth=2;
            pan.remove(prev);
            pan.add(cam,c); 
            repaint();
            pack();
            
        }
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Fenetre sit = new Fenetre();
    }
}
