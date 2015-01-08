/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Graphic;

import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Toshiba
 */
public class camembert extends JPanel {
    
    public camembert(List<Float> valeurs)
    {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for(int i=0;i<valeurs.size();i++)
        {
            pieDataset.setValue(""+(i+1), valeurs.get(i));
        }
        
        
        final JFreeChart pieChart = ChartFactory.createPieChart("Repartition des camions", pieDataset, true, false, false);
        final ChartPanel cPanel = new ChartPanel(pieChart);
        add(cPanel);


    }
}
