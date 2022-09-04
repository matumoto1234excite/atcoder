package compro.atcoder.abc.abc200.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
class ComparablePair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<ComparablePair<T1, T2>> {
    public T1 first;
    public T2 second;

    public ComparablePair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int result = /*random prime=*/17;
        result = /*random prime=*/31 * result + (first != null ? first.hashCode() : 0);
        result = /*random prime=*/31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ComparablePair<?, ?>)) {
            return false;
        }

        ComparablePair<T1, T2> p = (ComparablePair<T1, T2>) o;
        return p.first.equals(this.first) && p.second.equals(this.second);
    }

    @Override
    public String toString() {
        return first.toString() + " " + second.toString();
    }

    @Override
    public int compareTo(ComparablePair<T1, T2> rhs) {
        if (!this.first.equals(rhs)) {
            return this.first.compareTo(rhs.first);
        }
        return this.second.compareTo(this.second);
    }
}

@SuppressWarnings("unchecked")
class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        int result = /*random prime=*/17;
        result = /*random prime=*/31 * result + (first != null ? first.hashCode() : 0);
        result = /*random prime=*/31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Pair<?, ?>)) {
            return false;
        }

        Pair<T1, T2> p = (Pair<T1, T2>) o;
        return p.first.equals(this.first) && p.second.equals(this.second);
    }

    @Override
    public String toString() {
        return first.toString() + " " + second.toString();
    }
}

public class Main {
    public static List<Integer> bitToList(Integer bit, Integer len) {
        List<Integer> result = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            result.add((bit >> i) & 1);
        }
        return result;
    }

    public static Pair<List<Integer>, List<Integer>> solve(int n, List<Integer> as) {
        TreeMap<Integer, Integer> sumToBit = new TreeMap<>();

        final int len = Math.min(n, 8);
        for (int bit = 1; bit < (1 << len); bit++) {
            int sumMod200 = 0;
            for (int j = 0; j < len; j++) {
                boolean is1 = ((bit >> j) & 1) == 1;
                if (is1) {
                    sumMod200 += as.get(j);
                    sumMod200 %= 200;
                }
            }

            if (sumToBit.containsKey(sumMod200)) {
                Integer bit1 = sumToBit.get(sumMod200);
                Integer bit2 = bit;

                return new Pair<>(bitToList(bit1, len), bitToList(bit2, len));
            }

            sumToBit.put(sumMod200, bit);
        }

        // not found
        return null;
    }

    public static List<Integer> getIndexesOf1BitPosition(List<Integer> bit) {
        List<Integer> result = new ArrayList<>(bit.size());
        for (int i = 0; i < bit.size(); i++) {
            if (bit.get(i) == 1) {
                result.add(i +/*1-indexed*/1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = Integer.parseInt(stdin.nextLine());
        List<Integer> as = Arrays.stream(stdin.nextLine().split(" "))
                .map(Integer::parseInt)
                .map(a -> a % 200)
                .collect(Collectors.toList());


        Pair<List<Integer>, List<Integer>> bits = solve(n, as);
        if (bits == null) {
            System.out.println("No");
            return;
        }

        List<Integer> bs = getIndexesOf1BitPosition(bits.first);
        List<Integer> cs = getIndexesOf1BitPosition(bits.second);

        String strBs = String.join(" ", bs.stream().map(Object::toString).collect(Collectors.toList()));
        String strCs = String.join(" ", cs.stream().map(Object::toString).collect(Collectors.toList()));

        System.out.println("Yes");
        System.out.printf("%d %s\n", bs.size(), strBs);
        System.out.printf("%d %s\n", cs.size(), strCs);
    }
}
