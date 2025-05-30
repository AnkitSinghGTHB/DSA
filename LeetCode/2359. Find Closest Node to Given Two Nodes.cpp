class Solution {
public:
    //helper fn
    void dfs(int node, vector<int>& edges, vector<int>& dist) {
        int distance = 0;
        while (node != -1 && dist[node] == -1) {
            dist[node] = distance++;
            node = edges[node];
        }
    }
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        //uk i dont like graphs and trees :(
        int n = edges.size();
        vector<int> dist1(n, -1), dist2(n, -1);
        dfs(node1, edges, dist1);//get dists from node1 and node2
        dfs(node2, edges, dist2);
        int minDist = INT_MAX, result = -1;
        for (int i = 0; i < n; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxDist = max(dist1[i], dist2[i]);
                if (maxDist < minDist) {
                    minDist = maxDist;
                    result = i;
                }
            }
        }
        return result;
    }
};
