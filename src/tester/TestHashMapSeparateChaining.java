package tester;

import maps.*;

import java.util.*;

public class TestHashMapSeparateChaining {
    public static void main(String[] args) {
        HashMapSeparateChaining<Integer, String> H = new HashMapSeparateChaining<>();
        H.put(40, "Bob");
        H.put(92, "Eric");
        H.put(36, "Rose");
        H.put(62, "Donald");
        H.put(23, "Alice");
        H.put(96, "Jim");
        H.put(112, "Peter");
        H.put(41, "Matthew");
        H.put(22, "Charles");
        H.put(99, "Jack");
        H.put(10, "Tom");
        H.put(11, "Dorothy");
        System.out.println(H);
        System.out.println(H.loadFactor());

        H.put(31, "Toby");
        System.out.println(H);

        System.out.println(H.loadFactor());

        H.put(12, "Kim");
        H.put(33, "Debbie");

        for (var r : H)
            System.out.print(r + " ");

        System.out.println(H.get(36));

        ArrayList<String> A = new ArrayList<>();
        H.getAllValues(A);

        for (var s : A)
            System.out.print(s + " ");

        ArrayList<Integer> B = new ArrayList<>();
        H.getAllKeys(B);

        System.out.println();

        for (var s : B)
            System.out.print(s + " ");

        System.out.println(H.remove(11));

        H.clear();

//           for(int n = 10; n <= 10000000; n = n * 10) {
//               System.out.print("$"+ n + "$ & ");
//               HashMap<Integer, String> M = new HashMap<>();
//               long start = System.currentTimeMillis();
//               for (int i = 0; i < n; i++)
//                   M.put(i, "");
//               System.out.printf(" $%d$", (System.currentTimeMillis() - start));
//
//               TreeMap<Integer, String> T = new TreeMap<>();
//               start = System.currentTimeMillis();
//               for (int i = 0; i < n; i++)
//                   T.put(i, "");
//               System.out.printf(" & $%d$\n", (System.currentTimeMillis() - start));

    }
}
