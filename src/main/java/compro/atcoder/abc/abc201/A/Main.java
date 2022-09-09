package compro.atcoder.abc.abc201.A;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final Scanner stdin = new Scanner(System.in);

        final List<Integer> as = Arrays
                .stream(
                        stdin
                                .nextLine()
                                .split(" ")
                ).mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        as.sort(Integer::compareTo);

        int a0 = as.get(0);
        int a1 = as.get(1);
        int a2 = as.get(2);

        System.out.println(a0 + a2 == a1 + a1 ? "Yes" : "No");
    }
}
