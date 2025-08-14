class Solution {
public:
    string largestGoodInteger(string num) {
        //idk maybe sliding window?
        //nvm the window is 3 digits only, so maybe just check if those things exist
        //this might be the crappiest dsa solution i ever wrote
        map<string, int> f = {
            {"000", 1}, {"111", 1}, {"222", 1},
            {"333", 1}, {"444", 1}, {"555", 1},
            {"666", 1}, {"777", 1}, {"888", 1}, {"999", 1}
        };
        set<string> temp={};
        int n=num.size();
        for (int i=0;i<n-2;i++){
            string triplet = string() + num[i] + num[i+1] + num[i+2];
            if(f[triplet]==1){
                temp.insert(triplet);
            }
        }
        return temp.empty() ? "" : *temp.rbegin();
    }
};
