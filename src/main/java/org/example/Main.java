package org.example;

import org.example.Data.ForbesManipulation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static Connection connection;
    public static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь до csv-файла: ");
        //Forbes.csv
        String path = scanner.nextLine();
        openConnection();
        Thread.sleep(100);
        System.out.println("Желаете ли Вы ввести данные по-новому? (да/любое значение)");
        String choice = scanner.nextLine();
        boolean ch = Objects.equals(choice.toLowerCase(), "да");
        ForbesManipulation forbesManipulation = new ForbesManipulation(connection, path, ch);
        forbesManipulation.first();
        forbesManipulation.second();
        forbesManipulation.third();
    }



    public static void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:forbes.db");
            logger.info("Успешное соединение с БД");
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Ошибка в поключении к БД");
            System.exit(0);
        }
    }
}