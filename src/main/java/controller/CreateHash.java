package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class CreateHash {
    private String hash;


    CreateHash(String password){
        hash = stringHexa(Objects.requireNonNull(gerarHash(password)));
    }

    public static void main(String[] args) {
        CreateHash x = new CreateHash("AbbA");
        System.out.println(x.criaHash("BBB"));
    }

    public String GetHash(){
        return hash;
    }

    private static byte[] bytes;

    private static String stringHexa(byte[] bytes) {
        CreateHash.bytes = bytes;
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            int parteAlta = ((aByte >> 4) & 0xf) << 4;
            int parteBaixa = aByte & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }


    private static byte[] gerarHash(String frase) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String criaHash(String frase){
        return stringHexa(Objects.requireNonNull(gerarHash(frase)));
    }


}
