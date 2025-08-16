class Solution {
public:
    int maximum69Number (int num) {
        //this is a NICE question
        //basically i need to swap the first occuring 6 with a 9
        //swapping 9 with 6 makes no good to the soln
        //im going to write a big space soln
        vector<int> N;
        while(num){
            int i=num%10;
            N.push_back(i);
            num/=10;
        }
        reverse(N.begin(), N.end());
        int count=0,res=0,n=N.size()-1,j=0;
        for(int i:N){
            if (i==6 && count==0){
                res+=9*pow(10,n-j);
                count++;
            }
            else{
                res+=i*pow(10,n-j);
            }
            j++;
        }
        return res;
    }
};
