class Solution {
public:
    double maxAverageRatio(vector<vector<int>>& classes, int extraStudents) {
        //wait a pq ques in this econ?
        auto improvement = [](int pass, int total) {
            return (double)(pass + 1) / (total + 1) - (double)pass / total;
        };
        priority_queue<pair<double, pair<int, int>>> pq;
        for (auto& c : classes) {
            int pass = c[0];
            int total = c[1];
            double imp = improvement(pass, total);
            pq.push({imp, {pass, total}});
        }
        while (extraStudents--) {
            auto [current_imp, class_info] = pq.top();
            pq.pop();
            int pass = class_info.first;
            int total = class_info.second;
            pass++;
            total++;
            double new_imp = improvement(pass, total);
            pq.push({new_imp, {pass, total}});
        }
        double total_ratio = 0.0;
        while (!pq.empty()) {
            auto [imp, class_info] = pq.top();
            pq.pop();
            int pass = class_info.first;
            int total = class_info.second;
            total_ratio += (double)pass / total;
        }        
        return total_ratio / classes.size();
    }
};
