public class SumLongSpace {
    public static void main(String[] args) {
        Long a = 0L;
        StringBuilder s = new StringBuilder();
        for (String argu : args) {
			argu = argu + ' ';
            for (char c : argu.toCharArray()) {
			    if (Character.getType(c) != Character.SPACE_SEPARATOR){
					s.append(c);
				}else if (s.length()!= 0){
					a += Long.parseLong(s.substring(0));
					s.setLength(0);
				}
			}
		}
		System.out.println(a);
	}
}