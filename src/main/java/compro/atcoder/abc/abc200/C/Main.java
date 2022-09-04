package compro.atcoder.abc.abc200.C;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static <T extends Long> long binomial(long a, long b) {
        long result = 1;
        for (long i = 0; i < b; i++) {
            result *= a - i;
            result /= i + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = Integer.parseInt(stdin.nextLine());
        List<Integer> as = Arrays.stream(stdin.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> asMod200 = as
                .stream().map(a -> a % 200)
                .collect(Collectors.toList());

        TreeMap<Integer, Integer> counter = new TreeMap<Integer, Integer>();
        asMod200.forEach(a -> {
            counter.merge(a, /*default=*/1, Integer::sum);
        });

//        TreeMap<Integer, Integer> counter = asMod200.stream().collect(Collectors.groupingBy(), (map, a) -> map.merge(a, 1, (key, count) -> count + 1), TreeMap::putAll);

        long ans = counter
                .values()
                .stream()
                .map((count) -> binomial(count, 2))
                .mapToLong(Long::valueOf)
                .reduce(0L, Long::sum);

        System.out.println(ans);
    }
}
