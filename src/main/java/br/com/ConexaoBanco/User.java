package br.com.ConexaoBanco;
import br.com.ConexaoBanco.Model;

import java.sql.SQLException;

public class User {
    private  short id;
    private String name, last_name, birt, login, password;
    private char sex;

    public static void main(String[] args) throws SQLException {
        User allan = new User("allan", "Braun", null, "all", "1234", 'M');
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

    public boolean save() throws SQLException {

        Model.setUserInfo(name, last_name, birt, sex, login, password);
        return true;
    }




}
