import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = 1;
        // t = fs.nextInt();
        while (t-- > 0) {
            String s = fs.next();
            String r = fs.next();
            if (s.length()!=r.length()) {
                System.out.println("NO");
                break;
            }
            boolean x = false;
            for (int i=0;i<s.length();i++){
                if (s.charAt(i)!=r.charAt(s.length()-i-1)){
                    x=true;
                    System.out.println("NO");
                    break;
                }
            }
            if(x==false) System.out.println("YES");
        }

        out.flush();
    }

    static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
