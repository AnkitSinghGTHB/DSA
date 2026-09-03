import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String s = fs.next();
        int cnt = 1;         
        boolean danger = false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
                if (cnt >= 7) {
                    danger = true;
                    break;
                }
        } else {
        cnt = 1;      
    }
}
out.println(danger ? "YES" : "NO");


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
