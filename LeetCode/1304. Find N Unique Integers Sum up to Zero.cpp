class Solution {
public:
    vector<int> sumZero(int n) {
        //so.. wait -2,+2 allowed, hmm, alr
        int x=n/2; //let n=5, x=2
        vector<int> res;
        for(int i=1;i<=x;i++){ // [-1,1,-2,2]
            res.push_back(-i);
            res.push_back(i);
        }
        if(n%2!=0){
            res.push_back(0); //[-1,1,-2,2,0]
        }

        //lets check n=8
        //x=4, res=[-1,1,-2,2,-3,3,-4,4] //valid only if the ques allows -i as unique

        return res;
    }
};
