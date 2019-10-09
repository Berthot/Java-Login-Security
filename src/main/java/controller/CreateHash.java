package controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateHash {
    long endTIme = System.currentTimeMillis();
    long startTime = System.currentTimeMillis();

    public static String hash(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(pass.getBytes(), 0, pass.length());
        return new BigInteger(1, m.digest()).toString(64);

    }

    public static String getHash(String pass) throws NoSuchAlgorithmException {
//        pass = "aa" + pass + "bb";
//        System.out.println(CreateHash.hash(pass));
        return CreateHash.hash(pass);
    }



    static void breakHash(String Hash) throws NoSuchAlgorithmException {
        String findHash;
        String result = null;
        do{
            findHash = getHash(keyGen.getChar(4));
            if(findHash.equals(Hash)){
                result = findHash;
            }

        }while(findHash != Hash);
        System.out.println(result);
    }

}
