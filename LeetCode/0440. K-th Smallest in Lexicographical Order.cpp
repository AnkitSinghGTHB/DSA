class Solution {
private:
    //custom helper fn
    long countSteps(long n, long curr, long next){
        long steps=0;
        while(curr<=n){
            steps+=min(n+1,next)-curr;
            curr*=10;
            next*=10;
        }
        return steps;
    }
public:
    int findKthNumber(int n, int k) {
        //i am done bro leave me alone T_T
        //also what the heck is 'Trie'
        //my soln will face issues with big n val
        /*vector<string> s;
        for(int i=1;i<=n;i++){
            s.push_back(to_string(i));
        }
        sort(s.begin(),s.end());
        return stoi(s[k-1]);*/
        //ok so i learned about 'trie', its basically a decimal tree
        //the root starts at 1 to 9 and each node branches into 
        //10*curr to 10*curr+9
        //so i wont make the whole list
        //instead i would just count how many nums exist b/w a prefx
        //and the next, and skip subtrees accordinly
        long curr=1;
        k--;//0indx
        while(k>0){
            long steps=countSteps(n,curr,curr+1);//need to make custom fn
            if(steps<=k){
                curr++;//next
                k-=steps;//skip subtree
            }
            else{
                curr*=10;//go deeper
                k--;//count this num
            }
        }
        return curr; //and this should be O(log n)
    }
};
