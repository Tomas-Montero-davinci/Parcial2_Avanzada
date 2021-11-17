package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class MyJDBC02 {

    public static void main(String[] args) {
        Connection connection = null;

        try{

            // No le puse password porque mi conexion no lleva contraseña
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc","root", "");

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            String sqlTabla = "SELECT * FROM estudiante";

            //CONSULTA PREPARADA

            PreparedStatement consultaPreparada = connection.prepareStatement("INSERT INTO estudiante (dni, nombre, apellido) VALUES (?, ?, ?);");


            consultaPreparada.setInt(1,43040689);
            consultaPreparada.setString(2,"Tomas");
            consultaPreparada.setString(3,"Montero");
            consultaPreparada.executeUpdate();

            ResultSet resultSet = consultaPreparada.executeQuery("SELECT * FROM estudiante");
            connection.commit();

            while(resultSet.next()){
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("nombre") + " " + resultSet.getString("apellido") + " " + resultSet.getString("dni"));
            }

        }catch (SQLException sqlException){

            System.out.println(sqlException);
            if(connection != null){
                try{
                    connection.rollback();
                } catch (SQLException sqlException1){
                    System.out.println(sqlException1);
                }
            }

        }finally{

            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException sqlException){
                System.out.println(sqlException);
            }

        }
    }



}
