package br.com.ConexaoBanco;


import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {
    private static String status = "Não conectou...";

    static ResultSet getUserInfo(short id) throws SQLException {
        String selectSql = "select * from public_users where user_id = ?";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setInt(1, id);
            return statement.executeQuery();
        }
        return null;
    }

    static ResultSet getUserInfo(String login) throws SQLException {
        String selectSql = "select * from public_users where user_login = ?";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, login);
            return statement.executeQuery();
        }
        return null;
    }

    static void setUserInfo(String firstName, String lastName, String byrth, Character sex, String login, String pass) throws SQLException {
        String selectSql = "insert into public_users values (default,?, ?, ?, ?, ?, ?, default);";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, firstName);
            statement.setObject(2, lastName, Types.VARCHAR); // null
            statement.setObject(3, byrth, Types.DATE);

            if (sex != null){  // evita null pointer exeption
                statement.setObject(4, Character.toString(sex), Types.CHAR);
            } else {
                statement.setObject(4, sex, Types.CHAR);
            }

            statement.setString(5, login);
            statement.setString(6, pass);
            statement.executeUpdate();
        }
    }

    static ResultSet getIdFromLoginDb(String login) throws SQLException {

        String selectSql = "select user_id from public_users where user_login = ?;";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, login);
            return statement.executeQuery();
        }
        return null;
    }

    static ResultSet getPassFromId(int id) throws SQLException {

        String selectSql = "select user_password from public_users where user_id = ?;";
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

    private static String statusConection() {
        return status;
    }

    private static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return getConexaoMySQL();
    }

    public static void updatePassword(String pass, int id) throws SQLException {
//        update public_users # atualizar linha
//        set user_password = ? # seleciona oque deve mudar
//        where user_id = ?; # aonde deve alterar
//
        String selectSql = "update public_users set user_password = ? where user_id = ?;";
        Connection connSubmit = getConexaoMySQL();

        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, pass);
            statement.setObject(2, id);
            statement.executeUpdate();
        }
    }


}
