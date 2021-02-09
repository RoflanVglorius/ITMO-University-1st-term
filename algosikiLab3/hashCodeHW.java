import java.util.*;

public class hashCodeHW {

    private static final String[] base = new String[]{"Da", "EB", "F#"};
    public static int count = 0;
    private static final char[] symb = new char[]{'D', 'a', 'E', 'B', 'F', '#'};

    public static List<String> generateN(int n, long k) {
        if (n <= 0) {
            return null;
        }
        int count = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list = generateOne(list, k, n);
            if (count == k)
                break;
        }

        return list;
    }


    public static List<String> generateOne(List<String> strList, long k, int n) {
        if ((null == strList) || (0 == strList.size())) {
            strList = new ArrayList<>();
            strList.addAll(Arrays.asList(base));

            return strList;
        }

        List<String> result = new ArrayList<>();

        a:
        for (String s : base) {
            for (String str : strList) {
                result.add(s + str);
                if (str.length() == 2 * n - 2)
                    count++;
                if (count == k)
                    break a;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        HashSet<String> coll = new HashSet<>();
        HashSet<String> rand = new HashSet<>();
        List<String> collusion = generateN(2, 10000000);
        List<String> random = generateRand(2, collusion.size());
        for (String s : collusion) {
            System.out.println(s + " hashCode: " + s.hashCode());
        }
        for (String s : random){
            System.out.println(s + " hashCode: " + s.hashCode());
        }
        long m = System.currentTimeMillis();
            for (String s : collusion){
                coll.add(s);
            }
        System.out.println((double) (System.currentTimeMillis() - m));
        m = System.currentTimeMillis();
        for (String s : random){
            rand.add(s);
        }
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    private static List<String> generateRand(int i, int i1) {
        Random random = new Random();
        List<String> res = new ArrayList<>();
        int count = 0;
        while (count < i1){
            StringBuilder string = new StringBuilder();
            for (int j = 0; j < 2 * i; j++){
                string.append(symb[random.nextInt(symb.length)]);
            }
            count++;
            res.add(string.toString());
        }
        return res;
    }
}