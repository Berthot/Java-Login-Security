package br.com.ConexaoBanco;
import controller.CreateHash;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreatmentModel extends Model{

    public static List<String> getInfos(short id) throws SQLException {
        ResultSet x = getUserInfo(id);
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

    public int getIdFromLogin(String login) throws SQLException {
        ResultSet user = this.getIdFromLoginDb(login);
        if(user.next()){
            return user.getInt("user_id");
        }
        return 0;


    }

    public void addNewUser(String firstName, String lastName, String byrth, char sex, String login, String pass) throws SQLException {
        setUserInfo(firstName, lastName, byrth, sex, login, pass);
    }

    public Boolean matchPassword(int id, String hash) throws SQLException {
        ResultSet pass = getPassFromId(id);
        pass.next();
        String db_pass = pass.getString("user_pass");
        return hash.equals(db_pass);

    }

}
