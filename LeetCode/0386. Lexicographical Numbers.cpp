class Solution {
public:
    vector<int> lexicalOrder(int n) {
        //ok so i take n, generate numbers, then convert them to strings and compare
        vector<string> f;
        for(int i=1;i<=n;i++){
            f.push_back(to_string(i));
        }
        sort(f.begin(),f.end());
        vector<int> r;
        for (auto s:f){
            r.push_back(stoi(s));
        }
        return r;
    }
};
