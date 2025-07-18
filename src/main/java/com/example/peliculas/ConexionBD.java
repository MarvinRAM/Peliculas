package com.example.peliculas;

import java.sql.*;
import java.util.Properties;
import java.util.function.Consumer;

public class ConexionBD {
    private static final String DB_URL =
            "jdbc:oracle:thin:@s2k0s51zfpky302k_high?TNS_ADMIN=/Users/fabianmartinez/Downloads/Wallet_S2K0S51ZFPKY302K";
    private static final String DB_USER = "ADMIN";
    private static final String DB_PASSWORD = "Marvin212006-";

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", DB_USER);
        props.setProperty("password", DB_PASSWORD);
        props.setProperty("oracle.net.ssl_server_dn_match", "true");
        props.setProperty("oracle.net.ssl_version", "1.2");

        return DriverManager.getConnection(DB_URL, props);
    }

    // Método principal que consulta todos los datos de las películas
    public static void consultarPeliculas(Consumer<String> callback) {
        String sql = "SELECT ID_PELICULA, NOMBRE, GENERO, ANIO FROM PELICULAS";
        new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("ID_PELICULA");
                    String nombre = rs.getString("NOMBRE");
                    String genero = rs.getString("GENERO");
                    int anio = rs.getInt("ANIO");

                    sb.append("ID: ").append(id)
                            .append(" | Nombre: ").append(nombre)
                            .append(" | Género: ").append(genero)
                            .append(" | Año: ").append(anio)
                            .append("\n");
                }
                callback.accept(sb.toString());

            } catch (SQLException e) {
                e.printStackTrace();
                callback.accept("Error: " + e.getMessage());
            }
        }).start();
    }

    private static void ejecutarConsulta(String sql, String columna, Consumer<String> callback) {
        new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    sb.append(rs.getString(columna)).append("\n");
                }
                callback.accept(sb.toString());

            } catch (SQLException e) {
                e.printStackTrace();
                callback.accept("Error: " + e.getMessage());
            }
        }).start();
    }
}


