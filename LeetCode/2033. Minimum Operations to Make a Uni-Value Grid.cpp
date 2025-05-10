class Solution {
public:
    int minOperations(vector<vector<int>>& grid, int x) {
        vector<int> arr;
        for(vector<int>& r: grid){ //flattening
            arr.insert(arr.end(), r.begin(), r.end());
        }
        int base =arr[0];
        for(int num: arr) {
            if((num-base)%x!=0){ //each number in the grid should be reachable
                                  //from any other number by adding or subtracting x multiple times
            return -1; // Not possible
            }
        }
        nth_element(arr.begin(),arr.begin()+arr.size()/2,arr.end()); //finding the avg
        int median =arr[arr.size()/2];
        int operations =0;
        for(int num: arr){ //operation count
            operations+=abs(num-median)/x;
        }
        return operations;
    }
};
