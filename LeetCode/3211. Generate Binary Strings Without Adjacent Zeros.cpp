class Solution {
public:
    vector<string> validStrings(int n) {
        //heres my strat, i will start with an empty string, 
        //then at each step will add 0 or 1, if prev is not 0 then 
        //add 0 and recurse until length n is reached
        vector<string> result;
        function<void(string)> backtrack=[&](string current) {
            if (current.size()==n){
                result.push_back(current);
                return;
            }
            backtrack(current+'1');
            if (current.empty() || current.back()!='0') {
                backtrack(current+'0');
            }
        };
        backtrack("");
        return result;
    }
};
