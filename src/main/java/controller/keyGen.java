package controller;
import java.util.Random;


public class keyGen {


    private char[] array;
    public keyGen(){

    }

    public static void main(String[] args) {
        System.out.println(getChar(4));
    }

    public static int criaNumero(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    public static String getChar(int qntChar){
        char[] array = new char[qntChar];

        for(int i = 0; i < qntChar; i++){
            array[i] = (char) criaNumero(65,122);
        }

        String str = new String(array);

        return str;
    }


}