class Solution {
public:
    string robotWithString(string s) {
        //tf is this title
        //ok i might be able to do this, so what i will do is
        //create a freq map for chars in s
        //then use stack to simulate the robot's holdin buffr
        //iterate trhoufh s at each step
        //    wile iterating, push curr char to t
        //    decrease its count in the map
        //    while the top of t is less than or equal to the
        //    smallest remaining char in s, we pop and append
        //    to p
        vector<int> count(26,0);
        for(char c:s){
            count[c-'a']++;
        }
        string result,stack;
        int i=0;
        for(char c:s){
            stack.push_back(c);
            count[c-'a']--;
            char smallest='a';
            while(smallest <='z'&& count[smallest-'a']==0){
                smallest++;
            }
            while(!stack.empty() && (stack.back()<=smallest)){
                result.push_back(stack.back());
                stack.pop_back();
            }
        }
        while(!stack.empty()){
            result.push_back(stack.back());
            stack.pop_back();
        }
        return result;
    }
};
