struct TrieNode {
    string name;
    bool isEnd;
    map<string, TrieNode*> children;
    string repr;
    bool marked;

    TrieNode(string n = "") : name(n), isEnd(false), marked(false) {}
};

class Solution {
public:
    vector<vector<string>> deleteDuplicateFolder(vector<vector<string>>& paths) {
        //i think i needd to use trie here
        //let me define trienode as a struct
        TrieNode* root = new TrieNode();
        for (vector<string>& path : paths) {
            TrieNode* node = root;
            for (string& s : path) {
                if (node->children.find(s) == node->children.end()) {
                    node->children[s] = new TrieNode(s);
                }
                node = node->children[s];
            }
            node->isEnd = true;
        }
        vector<TrieNode*> allNodes;
        queue<TrieNode*> q;
        q.push(root);
        while (!q.empty()) {
            TrieNode* node = q.front(); q.pop();
            allNodes.push_back(node);
            for (auto& p : node->children) {
                q.push(p.second);
            }
        }
        reverse(allNodes.begin(), allNodes.end());
        unordered_map<string, int> reprCount;
        for (TrieNode* node : allNodes) {
            vector<string> parts;
            for (auto& p : node->children) {
                parts.push_back(p.first + "(" + p.second->repr + ")");
            }
            sort(parts.begin(), parts.end());
            string r = "";
            for (string& s : parts) r += s;
            node->repr = r;
            if (node != root) {
                reprCount[r]++;
            }
        }
        for (TrieNode* node : allNodes) {
            if (node == root) continue;
            if (!node->repr.empty() && reprCount[node->repr] >= 2) {
                node->marked = true;
            }
        }
        vector<vector<string>> result;
        stack<pair<TrieNode*, vector<string>>> st;
        st.push({root, {}});

        while (!st.empty()) {
            auto [node, current] = st.top();
            st.pop();
            if (node->marked) {
                continue;
            }
            if (node != root) {
                vector<string> newCurrent = current;
                newCurrent.push_back(node->name);
                if (node->isEnd) {
                    result.push_back(newCurrent);
                }
                for (auto it = node->children.rbegin(); it != node->children.rend(); ++it) {
                    st.push({it->second, newCurrent});
                }
            } else {
                for (auto it = root->children.rbegin(); it != root->children.rend(); ++it) {
                    st.push({it->second, current});
                }
            }
        }
        return result;
    }
};
