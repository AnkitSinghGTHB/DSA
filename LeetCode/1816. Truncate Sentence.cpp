class Solution {
public:
    string truncateSentence(string s, int k) {
        //uh well...
        int i=0,n=s.size();
        string res="";
        while(k>0 && i<n){
            if(s[i]==' '){
                k--;
            }
            if(k!=0){
                res+=s[i];
            }
            i++;
        }
        return res;
    }
};
