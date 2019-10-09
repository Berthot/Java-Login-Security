package br.com.ConexaoBanco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreatmentModel extends Model{

    private static ArrayList<String> getPasswords() throws SQLException {
        ResultSet result = getAll();
        ArrayList<String> passwords = new ArrayList<>();

        while(result.next()) {
            passwords.add(result.getString("user_password"));
        }
        return passwords;
    }

    public static String getPassFromId(short id) throws SQLException {
        ResultSet x = getUserInfo(id);
        ArrayList array = (ArrayList) infoArray(x);
        return (String) array.get(6);
    }

    private static List<String> infoArray(ResultSet x) throws SQLException {
        Objects.requireNonNull(x).next();
        List<String> lista = IntStream.range(1,9).mapToObj(n -> {
            try {
                return x.getString(n);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        return lista;
    }

    public static List<String> getInfos(String login) throws SQLException {
        // Array = id[0], name[1], last_name[2], birth[3], sex[4], login[5], password[6], timestamp[7]
        ResultSet x = getUserInfo(login);
        return infoArray(x);
    }

    public static List<String> getInfos(short id) throws SQLException {
        ResultSet x = getUserInfo(id);
        return infoArray(x);
    }

    public static int getIdFromLoginUser(String login) throws SQLException {
        ResultSet user = getIdFromLoginDb(login);
        if(user.next()){
            return user.getInt("user_id");
        }
        return 0;
    }

    public static Boolean verifyFirstName(String login, String firstName) throws SQLException{
        String firstNameDb = getInfos(login).get(1);
        return firstName.equals(firstNameDb);

    }

    public static Boolean matchPassword(int id, String userPassword) throws SQLException {
        ResultSet pass = getPassFromId(id);
        if(pass.next()) {
            String db_pass = pass.getString("user_password");
            return userPassword.equals(db_pass);
        }
        return false;
    }

    public static void setNewPassword(String pass, int id) throws SQLException {
        updatePassword(pass, id);
    }




}
