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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Toshiba
 */
public class Poids extends JPanel {
    XYSeriesCollection dataset;
    JFreeChart diagramme;
    
    public Poids(List<Float> valeurs, List<String> date)
    {
        dataset = new XYSeriesCollection();
        XYSeries serie= new XYSeries("Courbe");
        for (int i = 0; i < valeurs.size(); i++) {
            serie.add(i,valeurs.get(i));
        }
        dataset.addSeries(serie);
        diagramme = ChartFactory.createXYLineChart(
				"Graphe de Poids",
				"Date",
				"Poids",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
        
        ChartPanel chartPanel = new ChartPanel(diagramme);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(1200, 600));
        add(chartPanel);
    }
}
