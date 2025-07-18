package com.example.peliculas;

import java.sql.*;
import java.util.Properties;
import java.util.function.Consumer;

public class ConexionBD {
    private static final String DB_URL =
            "jdbc:oracle:thin:@s2k0s51zfpky302k_high?TNS_ADMIN=/Users/fabianmartinez/Downloads/Wallet_S2K0S51ZFPKY302K";
    private static final String DB_USER = "ADMIN";
    private static final String DB_PASSWORD = "Marvin212006-";

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", DB_USER);
        props.setProperty("password", DB_PASSWORD);
        props.setProperty("oracle.net.ssl_server_dn_match", "true");
        props.setProperty("oracle.net.ssl_version", "1.2");

        return DriverManager.getConnection(DB_URL, props);
    }

    public static void consultarID(Consumer<String> callback) {
        ejecutarConsulta("SELECT ID_PELICULA FROM PELICULAS", "ID_PELICULA", callback);
    }

    public static void consultarNombres(Consumer<String> callback) {
        ejecutarConsulta("SELECT NOMBRE FROM PELICULAS", "NOMBRE", callback);
    }

    public static void consultarGenero(Consumer<String> callback) {
        ejecutarConsulta("SELECT GENERO FROM PELICULAS", "GENERO", callback);
    }

    public static void consultarAnio(Consumer<String> callback) {
        ejecutarConsulta("SELECT ANIO FROM PELICULAS", "ANIO", callback);
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

