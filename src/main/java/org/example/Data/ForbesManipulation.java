package org.example.Data;

import com.opencsv.bean.CsvToBeanBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ForbesManipulation {

    private final Connection connection;
    private final Logger logger = Logger.getLogger(ForbesManipulation.class.getName());

    public ForbesManipulation(Connection connection, String path, boolean insertValues){
        this.connection = connection;
        if (insertValues){
            createTable();
            readCSV(path);
        }
    }

    private void createTable(){
        String testQuery = "DROP TABLE IF EXISTS forbes";
        String query = "CREATE TABLE IF NOT EXISTS forbes(\n" +
                "id INT AUTO_INCREMENT,\n" +
                "rank INT,\n" +
                "name_forbes VARCHAR(250),\n" +
                "networth DOUBLE,\n" +
                "age INT,\n" +
                "country VARCHAR(250),\n" +
                "source_forbes VARCHAR(250),\n" +
                "indusrty VARCHAR(250)\n" +
                ");";
        try{
            Statement statement = connection.createStatement();
            statement.execute(testQuery);
            statement.execute(query);
            statement.close();
            logger.info("Таблица успешно создана");
        } catch (SQLException e) {
            logger.info("Ошибка при создании таблицы");
        }
    }

    private void readCSV(String path){
        try {
            List<Forbes> beans = new CsvToBeanBuilder(new FileReader(path))
                    .withSkipLines(1)
                    .withType(Forbes.class)
                    .build()
                    .parse();
            for (var forbes : beans){
                insertValues(forbes);
            }
            logger.info("Данные успешно добавлены");
        } catch (FileNotFoundException e) {
            logger.info("Ошибка в прочтении файла");
        }
    }

    private void insertValues(Forbes forbes){
        String query = "INSERT INTO forbes (rank, name_forbes, networth, age, country, source_forbes, indusrty) " +
                "VALUES ("+forbes.getRank()+", '"+forbes.getName_forbes().replace("'", "")+"', " +
                ""+forbes.getNetworth()+", "+forbes.getAge()+", '"+forbes.getCountry().replace("'", "")+"', " +
                "'"+forbes.getSource_forbes().replace("'", "")+"'," +
                " '"+forbes.getIndusrty().replace("'", "").trim()+"')";
        try{
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
            System.out.println(forbes.toString());
        } catch (SQLException e) {
            logger.info("Ошибка при добавлении записи: " + forbes.toString());
        }
    }

    public void first(){
        Map<String, Double> map = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT country as 'country', SUM(networth) as 'networth' " +
                    "FROM forbes " +
                    "GROUP BY country");
            while (rs.next()) {
                map.put(rs.getString("country"), rs.getDouble("networth"));
            }
            statement.close();
        } catch (SQLException e) {
            logger.info("Ошибка в построении графика");
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var entry : map.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), "Страна");
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Суммарная стоимость компаний участников форбс по странам",
                "Страна",
                "Суммарная стоимость компаний участников форбс по странам",
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        JFrame frame =
                new JFrame("Первое задание");
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

    public void second(){
        int minValue = Integer.MAX_VALUE;
        String name = "";
        String query = "SELECT name_forbes as 'name', age as 'age' FROM forbes " +
                "WHERE forbes.country = 'France' AND forbes.networth > 10";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                if (rs.getInt("age") < minValue){
                    name = rs.getString("name");
                    minValue = rs.getInt("age");
                }
            }
            System.out.println("2) Name: " + name);
        } catch (SQLException e) {
            logger.info("Ошибка во втором задании.");
        }

    }

    public void third(){

        double maxValue = Double.MIN_VALUE;
        String name = "";
        String company = "";
        String query = "SELECT name_forbes, source_forbes, networth FROM forbes " +
                "WHERE forbes.country = 'United States' AND forbes.indusrty = 'Energy'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                if (rs.getDouble("networth") > maxValue){
                    maxValue = rs.getDouble("networth");
                    name = rs.getString("name_forbes");
                    company = rs.getString("source_forbes");
                }
            }
            System.out.println("3) Name: " + name + ", company: " + company);
        } catch (SQLException e) {
            logger.info("Ошибка в третьем задании");
        }

    }



}
