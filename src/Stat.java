package src;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stat {
    public static void statistic(Connection conn, String xLabel1, String yLabel1, String yLabel2, String yLabel3, String yLabel4) {
        try {
            // Query to retrieve the data
            String query = "SELECT " + xLabel1 + ", " + yLabel1 + ", " + yLabel2 +","+ yLabel3 + ","+ yLabel4 +" FROM FloatingCircles";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Create datasets for the charts
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
            DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
            DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();

            // Populate the datasets with data from the result set
            while (rs.next()) {
                String xValue = rs.getString(xLabel1);
                double yValue1 = rs.getDouble(yLabel1);
                double yValue2 = rs.getDouble(yLabel2);
                double yValue3 = rs.getDouble(yLabel3);
                double yValue4 = rs.getDouble(yLabel4);

                dataset1.addValue(yValue1, "Dataset 1", xValue);
                dataset2.addValue(yValue2, "Dataset 2", xValue);
                dataset3.addValue(yValue3, "Dataset 3", xValue);
                dataset4.addValue(yValue4, "Dataset 4", xValue);
            }

            // Create the chart for dataset 1
            JFreeChart chart1 = ChartFactory.createLineChart("Chart 1", xLabel1, yLabel1, dataset1);
            CategoryAxis xAxis1 = (CategoryAxis) chart1.getCategoryPlot().getDomainAxis();
            xAxis1.setLowerMargin(0.0);
            xAxis1.setUpperMargin(0.0);

            // Create the chart for dataset 2
            JFreeChart chart2 = ChartFactory.createLineChart("Chart 2", xLabel1, yLabel2, dataset2);
            CategoryAxis xAxis2 = (CategoryAxis) chart2.getCategoryPlot().getDomainAxis();
            xAxis2.setLowerMargin(0.0);
            xAxis2.setUpperMargin(0.0);

            // Create the chart for dataset 3
            JFreeChart chart3 = ChartFactory.createLineChart("Chart 3", xLabel1, yLabel3, dataset3);
            CategoryAxis xAxis3 = (CategoryAxis) chart3.getCategoryPlot().getDomainAxis();
            xAxis3.setLowerMargin(0.0);
            xAxis3.setUpperMargin(0.0);

             // Create the chart for dataset 1
            JFreeChart chart4 = ChartFactory.createLineChart("Chart 4", xLabel1, yLabel4, dataset4);
            CategoryAxis xAxis4 = (CategoryAxis) chart4.getCategoryPlot().getDomainAxis();
            xAxis4.setLowerMargin(0.0);
            xAxis4.setUpperMargin(0.0);

            // Create the combined chart
            CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot();
            combinedPlot.setGap(10.0);
            combinedPlot.add(chart1.getCategoryPlot());
            combinedPlot.add(chart2.getCategoryPlot());
            combinedPlot.add(chart3.getCategoryPlot());
            combinedPlot.add(chart4.getCategoryPlot());
            JFreeChart combinedChart = new JFreeChart("Combined Chart", combinedPlot);

            // Display the combined chart in a chart frame
            ChartFrame frame = new ChartFrame("Combined Chart", combinedChart);
            frame.pack();
            frame.setVisible(true);

            // Close the result set and statement
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
