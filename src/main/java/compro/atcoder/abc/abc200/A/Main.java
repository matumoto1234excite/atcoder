package compro.atcoder.abc.abc200.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int ceiled = (n+(100-1))/100;
        System.out.println(ceiled);
    }
}
