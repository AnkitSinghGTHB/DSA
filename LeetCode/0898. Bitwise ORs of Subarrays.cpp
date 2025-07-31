class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        //a dp ques in this economy???
        //i need to count the number of unique values
        unordered_set<int> result;
        unordered_set<int> current;
        unordered_set<int> next;
        for (int num : arr) {
            next.clear();
            next.insert(num);
            for (int val : current) {
                next.insert(val | num);
            }
            current = next;
            for (int val : current) {
                result.insert(val);
            }
        }
        return result.size();
    }
};
