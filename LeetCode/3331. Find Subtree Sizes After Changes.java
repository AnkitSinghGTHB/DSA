//i couldnt read properly, used deepseek intead
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree children
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] finalParent = parent.clone();
        int[] prevSame = new int[n];
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Iterative DFS on original tree to find nearest ancestor with same character.
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int code = stack.pop();

            if (code >= 0) {
                int u = code;
                int c = s.charAt(u) - 'a';

                int ancestor = last[c];
                prevSame[u] = ancestor;

                if (ancestor != -1) {
                    finalParent[u] = ancestor;
                }

                last[c] = u;

                stack.push(~u); // exit event

                for (int v : children[u]) {
                    stack.push(v);
                }
            } else {
                int u = ~code;
                int c = s.charAt(u) - 'a';
                last[c] = prevSame[u];
            }
        }

        // Build final tree
        List<Integer>[] finalChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) finalChildren[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            finalChildren[finalParent[i]].add(i);
        }

        // Get traversal order of final tree
        int[] order = new int[n];
        int idx = 0;
        stack.clear();
        stack.push(0);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[idx++] = u;
            for (int v : finalChildren[u]) {
                stack.push(v);
            }
        }

        // Compute subtree sizes bottom-up
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[finalParent[u]] += size[u];
        }

        return size;
    }
}
