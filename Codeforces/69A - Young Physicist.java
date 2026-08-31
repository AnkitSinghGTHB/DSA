import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        //okay my phys isnt good then
        //something with magnitude and cos theta
        //but i forgot all that cuz i did it 4 years ago
        int n = fs.nextInt();
        int sumX = 0, sumY = 0, sumZ = 0;
        for (int i = 0; i < n; i++) {
            sumX += fs.nextInt();
            sumY += fs.nextInt();
            sumZ += fs.nextInt();
        }
    if (sumX == 0 && sumY == 0 && sumZ == 0)
    out.println("YES");
    else
    out.println("NO");

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
