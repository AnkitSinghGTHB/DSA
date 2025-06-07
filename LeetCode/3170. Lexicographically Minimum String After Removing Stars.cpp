class Solution {
public:
    string clearStars(string s) {
        //i want to cry T_T
        //this has no hints to help me :(
        //how about i use a stack to keep strack of non-stars
        //and when *, remove smallest from stack
        //but stacks dont allow easy access of small elements
        //instead of always poping hte last char, i can remv the
        //smallest char to left of the *
        //maybe to do it better i can use a priority q
        //after debugging alot, man i want to cry
        vector<char> res;
        array<stack<int>,26> char_pos;
        for (char c:s){
            if (c=='*'){
                for(int i=0;i<26;++i){
                    if(!char_pos[i].empty()){
                        int idx=char_pos[i].top();
                        char_pos[i].pop();
                        res[idx]='#';//del
                        break;
                    }
                }
            }
            else{
                res.push_back(c);
                char_pos[c-'a'].push(res.size()-1);
            }
        }
        string result;
        for (char c:res){
            if (c!='#'){
                result+=c;
            }
        }
        return result;
        //i hope this works, post submission- it worked!
    }
};
