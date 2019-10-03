package br.com.ConexaoBanco;
import br.com.ConexaoBanco.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class User {
    private  short id;
    private String name, last_name, birt, login, password;
    private char sex;

    public static void main(String[] args) throws SQLException {
        User allan = new User("batata", "Braun", null, "all", "1234", 'M');
        allan.save();
    }

    public User(String name, String last_name, String birt, String login, String password, char sex) {
        this.name = name;
        this.last_name = last_name; //pode ser nulo
        this.birt = birt;  //pode ser nulo
        this.login = login;
        this.password = password;
        this.sex = sex;  //pode ser nulo
    }

    public boolean save() {
        try {
            Model.setUserInfo(name, last_name, birt, sex, login, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static User get_user(short id) throws SQLException {
        ResultSet row = Model.getUserInfo(id);
        row.getString("user_firstName");

    }






}
