import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = 1;
        // t = fs.nextInt();
        while (t-- > 0) {
            int x = fs.nextInt();
            //basically, i need to factorize
            int steps=0;
            if (x >= 5){
                steps += x/5;
                x %= 5;
            }
            if (x >= 4){
                steps += x/4;
                x %= 4;
            }
            if (x >= 3){
                steps += x/3;
                x %= 3;
            }
            if (x >= 2){
                steps += x/2;
                x %= 2;
            }
            if (x >= 1){
                steps += 1;
                x %= 1;
            }
            out.println(steps);
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
