class Solution {
public:
    int largestPathValue(string colors, vector<vector<int>>& edges) {
        //started with being sane, finally went insane T-T
        int n=colors.size();
        vector<vector<int>> graph(n);
        vector<int> indegree(n, 0);
        for (auto& edge:edges){//first we build the graph and in-degree count
            graph[edge[0]].push_back(edge[1]);
            indegree[edge[1]]++;
        }
        queue<int> q;//for topo sorting proabably kahn's algo idk
        for (int i=0;i<n;++i){
            if (indegree[i]==0) q.push(i);
        }
        //there are 26 lowercase letters
        //color_count[node][color]=max freq of that color at that node
        vector<vector<int>> color_count(n, vector<int>(26, 0));
        int processed=0;
        int result=0;
        while (!q.empty()){
            int node=q.front();
            q.pop();
            processed++;
            int color=colors[node]-'a';//updating the curr node's color count
            color_count[node][color]++;
            result = max(result,color_count[node][color]);
            for (int neighbor:graph[node]) {//then we traverse neighbors
                for (int c=0; c<26; ++c) {
                    color_count[neighbor][c]=max(color_count[neighbor][c],color_count[node][c]); //ik this looks weird on leetcode
                }
                if (--indegree[neighbor]==0){//decrease in-degree and enqueue if zero
                    q.push(neighbor);
                }
            }
        }
        //if we didnt process all nodes, there is a cycle
        return processed==n?result:-1;
    }
};
