/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Toshiba
 */
public class Employee extends JPanel {
    
    DefaultCategoryDataset dataset;
    JFreeChart diagramme;
    
    public Employee(List<Float> valeurs, List<String> nom) {        
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < valeurs.size(); i++) {
            dataset.addValue(valeurs.get(i), "heure travaillée", nom.get(i));
        }
        
        diagramme = ChartFactory.createBarChart("Temps travaillé par les chauffeurs", "Chauffeur", "Heure travaillée", dataset,PlotOrientation.HORIZONTAL,true,true,false);
        
        ChartPanel chartPanel = new ChartPanel(diagramme);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(1200, 600));
        add(chartPanel);
        
    }
    
}
