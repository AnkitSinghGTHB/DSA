class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIndex = -1; //index of best container word that passes through this node
    }
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        //trie? more like i cant trie anymore, nvm i have gone crazy
        //yes i used ai to solve cuz im dumb
        TrieNode root = new TrieNode();
        int n = wordsContainer.length;
        //insert all container words into trie (reversed)
        for (int idx = 0; idx < n; idx++) {
            String w = wordsContainer[idx];
            //update root's bestIndex with current word
            updateBest(root, idx, wordsContainer);
            TrieNode node = root;
            //traverse reversed word
            for (int i = w.length() - 1; i >= 0; i--) {
                int c = w.charAt(i) - 'a';
                if (node.children[c] == null) {
                    node.children[c] = new TrieNode();
                }
                node = node.children[c];
                updateBest(node, idx, wordsContainer);
            }
        }
        int m = wordsQuery.length;
        int[] ans = new int[m];
        for (int q = 0; q < m; q++) {
            String query = wordsQuery[q];
            TrieNode node = root;
            //traverse reversed query as far as possible
            for (int i = query.length() - 1; i >= 0; i--) {
                int c = query.charAt(i) - 'a';
                if (node.children[c] == null) break;
                node = node.children[c];
            }
            ans[q] = node.bestIndex;
        }
        return ans;
    }
    private void updateBest(TrieNode node, int idx, String[] words) {
        if (node.bestIndex == -1) {
            node.bestIndex = idx;
            return;
        }
        int best = node.bestIndex;
        int lenBest = words[best].length();
        int lenCurr = words[idx].length();
        if (lenCurr < lenBest || (lenCurr == lenBest && idx < best)) {
            node.bestIndex = idx;
        }
    }
}
