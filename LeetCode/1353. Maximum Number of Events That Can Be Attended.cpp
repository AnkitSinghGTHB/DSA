class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        int n = events.size();
        if (n == 0) return 0;
        sort(events.begin(), events.end());
        priority_queue<int, vector<int>, greater<int>> pq;
        int index = 0;
        int count = 0;
        int max_day = 0;
        for (const auto& e : events) {
            if (e[1] > max_day) {
                max_day = e[1];
            }
        }
        //i expected tle, but lmao nice
        for (int d = 1; d <= max_day; d++) {
            while (index < n && events[index][0] <= d) {
                pq.push(events[index][1]);
                index++;
            }
            while (!pq.empty() && pq.top() < d) {
                pq.pop();
            }
            if (!pq.empty()) {
                pq.pop();
                count++;
            }
        }
        return count;
    }
};
