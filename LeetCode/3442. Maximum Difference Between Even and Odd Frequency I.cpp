class Solution {
public:
    int maxDifference(string s) {
        //this aint easy lil bro (acc to the acceptance rate)
        //we can make a freq map then get the max_odd and max_eve and return it ig
        //yeah now i get where people make the mistakes, they asked max diff
        //so like lowest odd and max eve or maybe min eve and max odd
        int n=s.size();
        unordered_map<char,int> f;
        for(int i=0;i<n;i++){
            f[s[i]]++;
        }
        int max_odd=INT_MIN;
        int min_eve=INT_MAX;
        for(auto i:f){
            if (i.second%2==1){
                max_odd=max(max_odd,i.second);
            }
            else{
                min_eve=min(min_eve,i.second);
            }
        }
        if(max_odd ==INT_MIN || min_eve==INT_MAX){
            return 0;
        }
        return max_odd-min_eve;
    }
};
