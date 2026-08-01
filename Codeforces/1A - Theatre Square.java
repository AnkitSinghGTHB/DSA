import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = 1;
        // t = fs.nextInt();
        while (t-- > 0) {
            //here
            // 664 -> 4, 36, 16
            // 464 -> 2, 24, 16
            // positive correlation?
            long n = fs.nextLong();
            long m = fs.nextLong();
            long a = fs.nextLong();

            long rows = (n + a - 1) / a; //ceil of n/a
            long cols = (m + a - 1) / a;
            long ans = rows * cols;

            out.println(ans);

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
