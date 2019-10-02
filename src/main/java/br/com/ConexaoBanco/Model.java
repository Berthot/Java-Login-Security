package br.com.ConexaoBanco;

import java.sql.*;
import java.util.Objects;

public class Model {
    private static String status = "Não conectou...";

//    public static void main(String[] args) throws SQLException {
//        Connection conn = getConexaoMySQL();
//        System.out.println(statusConection());
//
//        ResultSet x = getInfo("berthott");
//        x.next();
//        System.out.println(x.getInt("user_id"));
//    }


    public ResultSet setUserInfo(String firstName, String lastName, String byrth, char sex, String login, String pass) throws SQLException {
        String selectSql = "insert into public_users (default,?, ?, ?, ?, ?, ?, default);";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, byrth);
            statement.setString(4, String.valueOf(sex));
            statement.setString(5, login);
            statement.setString(6, pass);
            return statement.executeQuery();
        }
        return null;
    }

    public ResultSet getIdFromLogin(String login) throws SQLException {

        String selectSql = "select user_id from public_users where user_login = ?;";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, login);
            return statement.executeQuery();
        }
        return null;
    }

    public ResultSet getPassFromId(String id) throws SQLException {

        String selectSql = "select user_pass from public_users where user_id = ?;";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, id);
            return statement.executeQuery();
        }
        return null;
    }

    public ResultSet getUserInfo(int id) throws SQLException {
        String selectSql = "select * from public_users where user_id = ?);";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setInt(1, id);
            return statement.executeQuery();
        }
        return null;
    }

    private static void FecharConexao() {
        try {
            Objects.requireNonNull(getConexaoMySQL()).close();

        } catch (SQLException ignored) {

        }
    }

    private static String statusConection() {
        return status;
    }

    private static java.sql.Connection getConexaoMySQL() {
        Connection connection = null;          //atributo do tipo Connection
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String serverName = "loginsystempuc.mysql.uhserver.com";    //caminho do servidor do BD
            String mydatabase = "loginsystempuc";        //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "bertho";        //nome de um usuário de seu BD
            String password = "@pucpr2019";      //sua senha de acesso

            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado

            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }

    private static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return getConexaoMySQL();
    }



}
