package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.*;

public class CreateHash {

    private String hash;

    public static String hash(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(pass.getBytes(), 0, pass.length());
        return new BigInteger(1, m.digest()).toString(16);

    }

    public static String getHash(String pass) throws NoSuchAlgorithmException {
        System.out.println(CreateHash.hash(pass));
        return CreateHash.hash(pass);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
       String pass = "cook";
       CreateHash.getHash(pass);
    }


}
