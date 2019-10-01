package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUsers extends br.com.ConexaoBanco.ConexaoMySQL{
    private String login, pass, firstName, lastName, byrth,sex;

    DbUsers(){
        getConexaoMySQL();
    }

    public static void main(String[] args) {
        Connection conn = getConexaoMySQL();
    }

    public ResultSet submit(String firstName, String lastName, String byrth, String sex, String login, String pass) throws SQLException {

        String selectSql = "insert into public_users (default,?, ?, ?, ?, ?, ?, default);";
        Connection connSubmit = getConexaoMySQL();
        if (connSubmit != null) {
            PreparedStatement statement = connSubmit.prepareStatement(selectSql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, byrth);
            statement.setString(4, sex);
            statement.setString(5, login);
            statement.setString(6, pass);
            statement.setString(7, pass);
            return statement.executeQuery();
        }
        return null;
    }


    public ResultSet getInfo(String select, String where, String same) throws SQLException {
        // ex: select user_id from user_login where user_login = berthott
        Connection conn = getConexaoMySQL();
        String selectSql = "select ? from ? where ?=?";
        if (conn != null) {
            PreparedStatement statement = conn.prepareStatement(selectSql);
            statement.setString(1, select);
            statement.setString(2, "public_user");
            statement.setString(3, where);
            statement.setString(3, same);
            return statement.executeQuery();
        }
        return null;
    }

//    public ResultSet getIdFromUser(String param) throws SQLException {
//        String selectSql = "select ? from ? where ? = ?";
//        if (tconn != null) {
//            PreparedStatement statement = this.conn.prepareStatement(selectSql);
//            statement.setString(1, "user_id");
//            statement.setString(2, "public_users");
//            statement.setString(3, "user_login");
//            statement.setString(4, param);
//            return statement.executeQuery();
//        }
//        return null;
//    }
//



}
