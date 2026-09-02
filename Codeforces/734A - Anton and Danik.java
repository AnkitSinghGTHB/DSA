import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = fs.nextInt();
        String s = fs.next();
        int cntA = 0, cntD = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'A') cntA++;
            else cntD++;
        }
    if (cntA > cntD) {
        out.println("Anton");
    } else if (cntA < cntD) {
    out.println("Danik");
} else {
out.println("Friendship");
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
