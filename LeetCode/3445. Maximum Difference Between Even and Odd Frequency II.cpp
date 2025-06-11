class Solution {
public:
    int maxDifference(string s, int k) {
        //i knew this was coming up T_T
        //ok so i can try all pairs of distinct chars
        //for each such pair, i get substrings of length >=k
        //  count freq[a] and freq[b]
        //  if f[a] is odd, and f[b] is eve, get diff
        //track max such valid diff
        //im trying to do sliding window
        /*int n =s.size();
        int res=INT_MIN;
        for (char a='0';a<='9';++a){
            for (char b='0';b<='9';++b){
                if(a==b){continue;}
                int minD[2][2];
                for (int i=0;i<2;i++){
                    for (int j=0;j<2;j++){
                        minD[i][j]=INT_MAX/2;
                    }
                }
                int cA=0,cB=0,preA=0,preB=0;
                int l=-1;
                for (int r=0;r<n;++r){
                    if (s[r]==a) cA++;
                    if(s[r]==b) cB++;
                    while(r-l+1>=k && cA-preA>0 && cB-preB>0){
                        int pA =preA & 1,pB=preB & 1;
                        minD[pA][pB]=min(minD[pA][pB], preA-preB);
                        l++;//lol
                        if(s[l]==a) preA++;
                        if(s[l]==b) preB++;
                    }
                    int currSize=r-l;
                    int pA=cA &1, pB=cB &1; //these are parity checkers
                    if (currSize >= k && (pA == 1) && (pB == 0) && cA > preA && cB > preB) {
                        res = max(res, cA - cB - minD[pA ^ 1][pB]);
                    }
                }
            }
        }
        return res == INT_MIN ?-1:res;
        //uh, nah man if they ask these things in coding interviews then i am cooked
        //688/690, bro i am leaving ts and asking chatgpt T_T
        */
        int n = s.size(), ans = INT_MIN;
        for (char a = '0'; a <= '4'; ++a) {
            for (char b = '0'; b <= '4'; ++b) {
                if (a == b) continue;
                int minDiff[2][2];
                for (int i = 0; i < 2; ++i)
                    for (int j = 0; j < 2; ++j)
                        minDiff[i][j] = INT_MAX/2;
                vector<int> prefA(n+1, 0), prefB(n+1, 0);
                for (int i = 0;i<n;++i) {
                    prefA[i+1]=prefA[i]+(s[i]==a);
                    prefB[i+1]=prefB[i]+(s[i]==b);
                }
                int l = 0;
                for (int r=0;r<n;++r) {
                    while (r - l + 1 >= k &&
                           prefA[l] < prefA[r+1] &&
                           prefB[l] < prefB[r+1]) {
                        int pa = prefA[l] % 2, pb = prefB[l] % 2;
                        minDiff[pa][pb] = min(minDiff[pa][pb], prefA[l] - prefB[l]);
                        ++l;
                    }
                    int paR = prefA[r+1] % 2, pbR = prefB[r+1] % 2;
                    int target = minDiff[1 - paR][pbR];
                    if (target < INT_MAX/2) {
                        ans = max(ans, prefA[r+1] - prefB[r+1] - target);
                    }
                }
            }
        }
        return ans == INT_MIN ? -1 : ans;
        //yes this code was edited by chatgpt to solve the issue
    }
};
