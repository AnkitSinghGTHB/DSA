class Solution {
public:
    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {
        // Initially, person 0 and firstPerson know the secret
        vector<bool> knows(n, false);
        knows[0] = true;
        knows[firstPerson] = true;

        // Sort meetings by time uh
        sort(meetings.begin(), meetings.end(),
             [](const vector<int>& a, const vector<int>& b) {
                 return a[2] < b[2];
             });

        int m = meetings.size();
        int i = 0;
        while (i < m) {
            int currentTime = meetings[i][2];
            vector<vector<int>> sameTime;
            // Group all meetings at currentTime
            while (i < m && meetings[i][2] == currentTime) {
                sameTime.push_back(meetings[i]);
                ++i;
            }

            // Collect all participants in this time group
            unordered_set<int> participants;
            unordered_map<int, vector<int>> adj;
            for (const auto& meet : sameTime) {
                int u = meet[0], v = meet[1];
                participants.insert(u);
                participants.insert(v);
                adj[u].push_back(v);
                adj[v].push_back(u);
            }

            // BFS starting from all participants who already know the secret
            queue<int> q;
            for (int p : participants) {
                if (knows[p]) {
                    q.push(p);
                }
            }
            while (!q.empty()) {
                int cur = q.front(); q.pop();
                for (int nb : adj[cur]) {
                    if (!knows[nb]) {
                        knows[nb] = true;
                        q.push(nb);
                    }
                }
            }
        }

        // Collect all people who know the secret lol
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (knows[i]) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};
