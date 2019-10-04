package br.com.ConexaoBanco;
import controller.CreateHash;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TreatmentModel extends Model{

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
        ResultSet x = getUserInfo(login);
        return infoArray(x);
    }

    public static List<String> getInfos(short id) throws SQLException {
        ResultSet x = getUserInfo(id);
        return infoArray(x);
    }

    public int getIdFromLogin(String login) throws SQLException {
        ResultSet user = this.getIdFromLoginDb(login);
        if(user.next()){
            return user.getInt("user_id");
        }
        return 0;


    }

    public Boolean matchPassword(int id, String hash) throws SQLException {
        ResultSet pass = getPassFromId(id);
        pass.next();
        String db_pass = pass.getString("user_pass");
        return hash.equals(db_pass);

    }

}
