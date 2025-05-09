using ll = long long;
const int MX = 80;
const int MOD = 1e9 + 7;

ll c[MX][MX]; // binomial coeffs

//init binomial coeffs
auto init = [] {
    c[0][0] = 1;
    for (int i = 1; i < MX; ++i) {
        c[i][0] = 1;
        for (int j = 1; j <= i; ++j) {
            c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
        }
    }
    return 0;
}();

class Solution {
public:
    int countBalancedPermutations(string num) {
        //lets understand the hints first
        /*  Count freq of each char in str
            DP
            states are chars, sum of even indx nums, and the num of digs used
            calc the sum of odd indx nums w/o using a state for it
        */
        /*
        int mod=1000000007;
        unordered_map<char, int> d={};
        for (char s:num){
            d[s]++;
        }//got freq

        int n=num.size();
        map<vector<int>, int> memo; 
        // key: freq of digs + even sum + used count, val: number of ways

        vector<int> digits(10, 0);
        for (auto &[ch, cnt] : d) {
            digits[ch-'0'] = cnt;
        }

        //recursively try each digit at each index
        function<int(vector<int>&, int, int)> dfs = [&](vector<int> &freq, int idx, int evenSum) -> int {
            if (idx == n) {//base case: all used
                int oddSum=accumulate(num.begin(),num.end(),0,[&](int acc,char c){
                    return acc+(c-'0');
                })-evenSum;
                return evenSum==oddSum;
            }
            vector<int> key=freq;
            key.push_back(idx);
            key.push_back(evenSum);
            if (memo.count(key)) return memo[key];
            int ans = 0;
            for (int dig=0;dig<=9;dig++) {
                if (freq[dig]>0) {
                    freq[dig]--;
                    if (idx%2==0)
                        ans= (ans+dfs(freq,idx+1,evenSum+dig))%mod;
                    else
                        ans= (ans+dfs(freq,idx+1,evenSum))%mod;
                    freq[dig]++;
                }
            }
            return memo[key]=ans;
        };
        return dfs(digits,0,0);
        //getting tle
        */
        /*
        int mod = 1e9 + 7;
        vector<int> freq(10, 0); // count digits
        for (char c:num) freq[c-'0']++;
        int totalSum=0;
        for (int i=0;i<10;++i) totalSum += i*freq[i];

        using Key = tuple<vector<int>,int,int>;

        struct KeyHash {
            size_t operator()(const Key& k) const {
                const auto &[vec, idx, sum] = k;
                size_t h = hash<int>()(idx) ^ hash<int>()(sum);
                for (int x:vec) h ^= hash<int>()(x)+0x9e3779b9 +(h<< 6) + (h >> 2);
                return h;//lol
            }
        };
        unordered_map<Key, int, KeyHash> memo;
        function<int(vector<int>&,int,int)>dfs=[&](vector<int>& f,int idx,int evenSum)-> int {
            if (idx==num.size()){
                int oddSum=totalSum-evenSum;
                return evenSum==oddSum;
            }

            Key state={f,idx,evenSum};
            if (memo.count(state)) return memo[state];

            int ans=0;
            for (int d=0;d<10;++d){
                if (f[d]==0) continue;
                if (idx==0 && d==0) continue; // skip leading 0

                f[d]--;
                if (idx%2==0)
                    ans =(ans+ dfs(f,idx+1,evenSum+d))%mod;
                else
                    ans =(ans+ dfs(f,idx+1,evenSum))%mod;
                f[d]++;
            }
            return memo[state]=ans;
        };
        return dfs(freq,0,0);
        */

        //also,yeah i had to finally ask chatgpt after my soln was getting tle
        // count each digit freq
        int cnt[10]{};
        int s = 0;
        for (char& c : num) {
            ++cnt[c - '0'];
            s += c - '0';
        }
        if (s % 2) {
            return 0;
        }
        int n = num.size();
        int m = n / 2 + 1;
        int f[10][s / 2 + 1][m][m + 1];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int j, int a, int b) -> int {
            if (i > 9) {
                return ((j | a | b) == 0 ? 1 : 0);
            }
            if (a == 0 && j) {
                return 0;
            }
            if (f[i][j][a][b] != -1) {
                return f[i][j][a][b];
            }
            int ans = 0;
            for (int l = 0; l <= min(cnt[i], a); ++l) {
                int r = cnt[i] - l;
                if (r >= 0 && r <= b && l * i <= j) {
                    int t = c[a][l] * c[b][r] % MOD * dfs(i + 1, j - l * i, a - l, b - r) % MOD;
                    ans = (ans + t) % MOD;
                }
            }
            return f[i][j][a][b] = ans;
        };
        return dfs(0, s / 2, n / 2, (n + 1) / 2);
    }
};
