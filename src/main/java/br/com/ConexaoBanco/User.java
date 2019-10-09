package br.com.ConexaoBanco;
import java.sql.SQLException;
import java.util.List;

public class User {
    private  short id;
    private String name, last_name, birth, login, password, timeStamp;
    private Character sex;


    public User(String name, String last_name, String birth, Character sex, String login, String password) {
        this.id = -1;
        this.name = name;
        this.last_name = last_name; //pode ser nulo
        this.birth = birth;  //pode ser nulo
        this.login = login;
        this.password = password;
        this.sex = sex;  //pode ser nulo
    }

    // construtor com id e timestamp e time opcional, polimorfismo
    public User(short id, String name, String last_name, String birth, Character sex, String login,
                String password, String timeStamp) {
        this.id = id;
        this.name = name;
        this.last_name = last_name; //pode ser nulo
        this.birth = birth;  //pode ser nulo
        this.login = login;
        this.password = password;
        this.sex = sex;  //pode ser nulo
        this.timeStamp = timeStamp;
    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirth() {
        return birth;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Character getSex() {
        return sex;
    }

    public boolean save() {
        try {
            Model.setUserInfo(name, last_name, birth, sex, login, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User get_user(short id)  {
        try {
            List<String> user_info = TreatmentModel.getInfos(id);
            return new User(Short.parseShort(user_info.get(0)), user_info.get(1), user_info.get(2),
                    user_info.get(3), user_info.get(4).charAt(0), user_info.get(5), user_info.get(6), user_info.get(7));

        } catch (SQLException e) {
            return null;
        }
    }

    public static User get_user(String login)  {
        try {
            List<String> user_info = TreatmentModel.getInfos(login);
            return new User(Short.parseShort(user_info.get(0)), user_info.get(1), user_info.get(2),
                    user_info.get(3), user_info.get(4).charAt(0), user_info.get(5), user_info.get(6), user_info.get(7));

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Usuario [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth='" + birth + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", sex=" + sex +
                ']';
    }
}
