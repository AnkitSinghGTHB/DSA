class Solution {
public:
    int findLucky(vector<int>& arr) {
        //ok i can use a freq map and just i.first == i.second, and then find the max i
        map<int,int> f;
        int n=arr.size();
        for (int i=0;i<n;i++){
            f[arr[i]]++;
        }
        for (auto i = f.rbegin(); i!=f.rend(); ++i){
            if (i->first==i->second){
                return i->second;
            }
        }
        return -1;
    }
};
