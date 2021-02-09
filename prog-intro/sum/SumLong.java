public class SumLongSpace {
    public static void main(String[] args) {
        Long a = 0L;
        String s = "";
        for (int i = 0; i < args.length; i++) {
            String c = args[i];
            for (int j = 0; j < c.length(); j++) {
                s = "";
                while ((Character.isDigit(c.charAt(j))) || Character.valueOf(c.charAt(j)) == '-') {
                    s = s + c.charAt(j);
                    if (j < c.length() - 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                if (s.length() != 0) {
                    a += Long.parseLong(s);
                }
            }
        }
        System.out.println(a);
    }
}