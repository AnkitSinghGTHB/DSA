class Solution {
  public:
    vector<double> getMedian(vector<int> &arr) {
        //dynamic median ok lol
        //ok i jsut realised i was finding mean
        vector<double> medians;
        priority_queue<int> maxHeap;// stores the smaller half
        priority_queue<int, vector<int>, greater<int>> minHeap;// stores the larger half
        for (int num : arr) {
            if (maxHeap.empty() || num <= maxHeap.top()) {
                maxHeap.push(num);
            }
            else {
                minHeap.push(num);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.push(maxHeap.top());
                maxHeap.pop();
            }
            else if (minHeap.size() > maxHeap.size()) {
                maxHeap.push(minHeap.top());
                minHeap.pop();
            }
            if (maxHeap.size() == minHeap.size()) {
                medians.push_back((maxHeap.top() + minHeap.top()) / 2.0);
            }
            else {
                medians.push_back(maxHeap.top());
            }
        }
        return medians;
    }
};
