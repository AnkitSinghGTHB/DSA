class Solution {
public:
    int maxDistance(string s, int k) {
        //if ts is related wit boms then im ded bruv
        //ok, hashtable i can do ig
        //ok i can try all combs w/ dfs, but limit it to k changes
        //so i define delta for each dir
        //then at each char, do nothing (og dir), or if k>0 try changing dir
        //finally track max abs(x)+abs(y)
        auto count = [](int a, int b, int t) {
            return abs(a - b) + 2 * t;
        };
        int ans = 0, N = 0, S = 0, E = 0, W = 0;
        for (char i : s) {
            if (i == 'N') N++;
            if (i == 'S') S++;
            if (i == 'E') E++;
            if (i == 'W') W++;
            int modV = min({k, N, S});
            int modH = min({k - modV, E, W});
            int vDist = count(N, S, modV);
            int hDist = count(E, W, modH);
            ans = max(ans, vDist + hDist);
        }
        return ans;
    }
};
