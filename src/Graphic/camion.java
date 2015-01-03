/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Toshiba
 */
public class camion extends JPanel {

    /**
     * titre : Le titre du graphique affiché en haut
     */
    private String titre;
    /**
     * ordonnee : le nom de l'axe des ordonnées
     */
    private String ordonnee;
    /**
     * abscisses : le nom de l'axe des abscisses
     */
    private String abscisse;
    /**
     * valeurs : les valeurs à afficher, elles sont triées par séries et par catégories
     */
    private List<Float> valeurs;
    /**
     * series : la liste des séries
     */
    private List<String> series;
    /**
     * categories : la liste des categories
     */
    private List<String> categories;
    /**
     * legende : booleen vrai si on affiche la légende
     */
    private boolean legende;
    /**
     * couleurFond : la couleur du fond
     */
    private Color couleurFond;
    /**
     * couleurBarres : les couleurs appliquées aux barres
     */
    private Color[] couleursBarres = {Color.cyan.darker(),
        Color.red, Color.green, Color.cyan, Color.magenta,
        Color.yellow, Color.pink, Color.darkGray, Color.orange};

    public camion(String titre, String abscisse, String ordonnee, List<Float> valeurs, Color fond, List<String> listeSeries, List<String> listeCategory, boolean legende) {

        this.titre = titre;
        this.ordonnee = ordonnee;
        this.abscisse = abscisse;
        this.valeurs = valeurs;
        this.series = listeSeries;
        this.categories = listeCategory;
        this.legende = legende;
        this.couleurFond = fond;
        initialiser();
    }

    private void initialiser() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int k = 0;
        for (int j = 0; j < categories.size(); j++) {
            for (int i = 0; i < series.size(); i++) {
                dataset.addValue(valeurs.get(k), series.get(i), categories.get(j));
                k++;
            }

        }
        JFreeChart chart = ChartFactory.createBarChart(
                titre, // chart title
                abscisse, // domain axis label
                ordonnee, // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                legende, // include legend
                true, // tooltips
                false // URL
        );

		// definition de la couleur de fond
        //chart.setBackgroundPaint(couleurFond);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        //valeur comprise entre 0 et 1 transparence de la zone graphique
        plot.setBackgroundAlpha(0.9f);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

		// pour la couleur des barres pour chaque serie
        for (int s = 0; s < series.size(); s++) {
            GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, couleursBarres[s],
                    0.0f, 0.0f, new Color(0, 40, 70));
            renderer.setSeriesPaint(s, gp0);

        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);

        add(chartPanel);
    }

}
