class Solution {
public:
    string findLexSmallestString(string s, int a, int b) {
        // Why would you want me to bruteforce?
        string smallest = s;
        unordered_set<string> visited;
        
        function<void(string)> dfs = [&](string current) {a
            if (visited.count(current)) return;
            visited.insert(current);
            
            if (current < smallest) {
                smallest = current;
            }
            
            // Operation 1: Add to odd indices
            string addStr = current;
            for (int i = 1; i < current.length(); i += 2) {
                int digit = addStr[i] - '0';
                digit = (digit + a) % 10;
                addStr[i] = digit + '0';
            }
            dfs(addStr);
            
            // Operation 2: Rotate
            string rotateStr = current.substr(current.length() - b) + 
                              current.substr(0, current.length() - b);
            dfs(rotateStr);
        };
        
        dfs(s);
        return smallest;
    }
};
