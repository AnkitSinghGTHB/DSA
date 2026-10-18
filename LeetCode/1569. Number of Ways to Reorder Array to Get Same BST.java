class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;
    //deepseek helped me
    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute binomial coefficients
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        // Build the original BST
        Node root = null;
        for (int x : nums) {
            root = insert(root, x);
        }

        long[] result = dfs(root);
        long totalWays = result[0];

        // Exclude the original order
        return (int) ((totalWays - 1 + MOD) % MOD);
    }

    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);

        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    // returns [number of ways, subtree size]
    private long[] dfs(Node node) {
        if (node == null) {
            return new long[]{1, 0};
        }

        long[] left = dfs(node.left);
        long[] right = dfs(node.right);

        int leftSize = (int) left[1];
        int rightSize = (int) right[1];

        long ways = comb[leftSize + rightSize][leftSize];
        ways = ways * left[0] % MOD;
        ways = ways * right[0] % MOD;

        return new long[]{ways, 1 + leftSize + rightSize};
    }

    private static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }
}
