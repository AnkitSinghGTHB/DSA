class Solution {
public:
    int minimumTeachings(int n, vector<vector<int>>& languages, vector<vector<int>>& friendships) {
        //its so over bro
        int m = languages.size();
        vector<unordered_set<int>> userLang(m + 1);
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                userLang[i + 1].insert(lang);
            }
        }
        vector<bool> satisfied(friendships.size(), false);
        for (int i = 0; i < friendships.size(); i++) {
            int u = friendships[i][0];
            int v = friendships[i][1];
            for (int l : userLang[u]) {
                if (userLang[v].count(l)) {
                    satisfied[i] = true;
                    break;
                }
            }
        }
        int ans = m;
        for (int l = 1; l <= n; l++) {
            unordered_set<int> taught;
            for (int i = 0; i < friendships.size(); i++) {
                if (satisfied[i]) continue;
                int u = friendships[i][0];
                int v = friendships[i][1];
                if (!userLang[u].count(l)) taught.insert(u);
                if (!userLang[v].count(l)) taught.insert(v);
            }
            ans = min(ans, (int)taught.size());
        }
        return ans;
    }
};
