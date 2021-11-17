package jdbc;

import java.sql.*;

public class MyJDBC01 {

    public static void main(String[] args) {

//        Connection connection = null;

        try{
            // No le puse password porque mi conexion no lleva contraseña
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc","root", "");

            // CONEXION A BASE DE DATOS Y QUERY

           Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM estudiante");


            while(resultSet.next()){
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("nombre") + " " + resultSet.getString("apellido") + " " + resultSet.getString("dni"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}