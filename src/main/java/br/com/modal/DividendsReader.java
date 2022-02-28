package br.com.modal;

public class DividendsReader {
    public static void main(String[] args) {
        String test = "R$ 27.13 R$ 14.00 R$ 10.00";
        System.out.println(test.replace("R$ ", "\n"));
    }
}
