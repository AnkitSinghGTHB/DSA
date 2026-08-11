import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder ans = new StringBuilder();
        String vowels = "AOYEUI";
        for (char c : s.toCharArray()) {
            char upper = Character.toUpperCase(c);
            if (vowels.indexOf(upper) != -1) continue;
            ans.append('.').append(Character.toLowerCase(c));
        }
        System.out.println(ans);
    }
}
