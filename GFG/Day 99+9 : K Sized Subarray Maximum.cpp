class Solution {
  public:
    vector<int> maxOfSubarrays(vector<int>& arr, int k) {
        //lmfao this looks like a sliding windows ques
        //but what if i start with subarr of elements k
        //then count the first max, then deque the first
        //element and enqueue the next element, but i
        //compare it with the max, and then do the same
        //till i reach the end
        //ok my approach fails for certain cases
        //rebuiling it using deque
        vector<int> result;
        deque<int> dq;
        int n = arr.size();
        for (int i = 0; i < n; ++i) {
            while (!dq.empty() && dq.front() <= i - k) {
                dq.pop_front();
            }
            
            while (!dq.empty() && arr[dq.back()] < arr[i]) {
                dq.pop_back();
            }
            dq.push_back(i);
            if (i >= k - 1) {
                result.push_back(arr[dq.front()]);
            }
        }
        return result;
    }
};
