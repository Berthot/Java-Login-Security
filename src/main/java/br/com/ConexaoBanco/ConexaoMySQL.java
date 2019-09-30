package br.com.ConexaoBanco;


import java.sql.*;

public class ConexaoMySQL {

    public static String status = "Não conectou...";

    public ConexaoMySQL() {

    }

//    getConexaoMySQL();

    public static void main(String[] args) {
        getConexaoMySQL();
        System.out.println(statusConection());
    }



    static ResultSet selectTudo() throws SQLException {
        String selectSql = "select * from users";
        Connection conn = getConexaoMySQL();
        if (conn != null) {
            PreparedStatement statement = conn.prepareStatement(selectSql);
            return statement.executeQuery();
        }

        return null;
    }


    static ResultSet getNameFromId(int id, String select) throws SQLException {
        String selectSql = "select " + select + " from users where user_id = ?";
        Connection conn = getConexaoMySQL();
        if (conn != null) {
            PreparedStatement statement = conn.prepareStatement(selectSql);
            statement.setInt(1, id);
            return statement.executeQuery();
        }
        return null;
    }


    public static java.sql.Connection getConexaoMySQL() {
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


    static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConexaoMySQL.getConexaoMySQL().close();
            return true;

        } catch (SQLException e) {

            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
    }
}
