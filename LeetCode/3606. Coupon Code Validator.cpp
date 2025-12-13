class Solution {
public:
    vector<string> validateCoupons(vector<string>& code, vector<string>& businessLine, vector<bool>& isActive) {
        vector<pair<int, string>> validCoupons; // pair of businessLine order and code
        vector<string> result;        
        //define businessLine order
        vector<string> order = {"electronics", "grocery", "pharmacy", "restaurant"};
        for (int i = 0; i < code.size(); i++) {
            //check isActive
            if (!isActive[i]) continue;            
            //check code is non-empty and contains only alphanumeric and underscore
            if (code[i].empty()) continue;
            bool validCode = true;
            for (char c : code[i]) {
                if (!isalnum(c) && c != '_') {
                    validCode = false;
                    break;
                }
            }
            if (!validCode) continue;            
            //check businessLine is valid
            if (businessLine[i] != "electronics" && businessLine[i] != "grocery" && 
                businessLine[i] != "pharmacy" && businessLine[i] != "restaurant") {
                continue;
            }            
            //find index of businessLine in order
            int index = 0;
            while (index < 4 && order[index] != businessLine[i]) index++;            
            validCoupons.push_back({index, code[i]});
        }        
        //sort by businessLine order, then by code
        sort(validCoupons.begin(), validCoupons.end(), 
             [](const pair<int, string>& a, const pair<int, string>& b) {
                 if (a.first != b.first) return a.first < b.first;
                 return a.second < b.second;
             });
        //extract codes
        for (const auto& p : validCoupons) {
            result.push_back(p.second);
        }        
        return result;
    }
};
