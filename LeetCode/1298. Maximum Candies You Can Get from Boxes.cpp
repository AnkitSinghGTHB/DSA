class Solution {
public:
    int maxCandies(vector<int>& status, vector<int>& candies, vector<vector<int>>& keys, vector<vector<int>>& containedBoxes, vector<int>& initialBoxes) {
        //another bfs question? bruh why
        //so what i would do is use a queue to manage which boxes i can try to open
        //then i might use set or boolean arrays to track the boxes i already have,
        //i have opened, keys i have and whether something changed for iterating again
        int n =status.size();
        vector<bool> hasBox(n, false), hasKey(n, false), opened(n,false);
        queue<int> q;
        int totalCandies=0;
        for(int box: initialBoxes){
            hasBox[box]=true;
            if(status[box]==1){
                q.push(box);
                opened[box]=true;
            }
        }
        while (!q.empty()){
            int curr=q.front();
            q.pop();
            totalCandies += candies[curr];
            for(int k:keys[curr]){
                hasKey[k]=true;
                if (hasBox[k] && !opened[k]){
                    q.push(k);
                    opened[k]=true;
                }
            }
            for(int b: containedBoxes[curr]){
                hasBox[b]=true;
                if(status[b]==1 ||hasKey[b]){
                    if (!opened[b]){
                        q.push(b);
                        opened[b]=true;
                    }
                }
            }
        }
        return totalCandies;
    }
};
