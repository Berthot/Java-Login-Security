package view;

import java.util.Scanner;

public class View {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int acc = 0;
        while (true){
            System.out.println("sou sua thread.." + acc);
            acc++;
        }
    }

    static void menu(){
        System.out.println("[0] - Criar Conta:        ");
        System.out.println("[1] - Acessar sua conta:  ");
        System.out.println("[2] - Recuperar sua senha:");
        System.out.println();
    }


}
