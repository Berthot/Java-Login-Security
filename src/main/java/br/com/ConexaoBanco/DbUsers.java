package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUsers extends br.com.ConexaoBanco.ConexaoMySQL{
    private String login, pass, firstName, lastName, byrth,sex;
    private final Connection conn = getConexaoMySQL();

    DbUsers(){
        getConexaoMySQL();
    }

    public static void main(String[] args) {

    }

    public ResultSet submit(String select, String where, String param) throws SQLException {
        String selectSql = "select " + select + " from user_ where " + where + " = ?";
        if (this.conn != null) {
            PreparedStatement statement = this.conn.prepareStatement(selectSql);
            statement.setString(1, param);
            return statement.executeQuery();
        }
        return null;
    }


    public ResultSet getInfo(String select, String from, String where) throws SQLException {

        // ex: select user_id from user_login

        String selectSql = "select ? from ? where ";
        if (this.conn != null) {
            PreparedStatement statement = this.conn.prepareStatement(selectSql);
            statement.setString(1, select);
            statement.setString(2, "public_user");
            statement.setString(3, where);
            return statement.executeQuery();
        }
        return null;
    }

    public ResultSet getIdFromUser(String param) throws SQLException {
        String selectSql = "select ? from ? where ? = ?";
        if (this.conn != null) {
            PreparedStatement statement = this.conn.prepareStatement(selectSql);
            statement.setString(1, "user_id");
            statement.setString(2, "public_users");
            statement.setString(3, "user_login");
            statement.setString(4, param);
            return statement.executeQuery();
        }
        return null;
    }




}
