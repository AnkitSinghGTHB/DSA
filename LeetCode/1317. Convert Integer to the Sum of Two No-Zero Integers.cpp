class Solution {
public:
    vector<int> getNoZeroIntegers(int n) {
        //uh? alright shold be no 0 in the val
        for(int i=1;i<n;i++){
            if (checkNoZero(i) && checkNoZero(n-i)){
                return vector<int>{i,n-i};
            }
        }
        return vector<int>{n-1,1}; //idk case
    }

    bool checkNoZero(int i){
        while (i){  //let 1234
            int rem=i%10; // 1234%10=4, 1234/10=123
            i/=10;

            if(rem==0){
                return false;
            }
        }
        return true;
    }
};
