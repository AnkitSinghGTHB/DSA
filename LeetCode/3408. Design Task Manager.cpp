class TaskManager {
    using Entry = pair<int,int>; // {priority, taskId}
    priority_queue<Entry> pq;    // max-heap by priority, then taskId
    unordered_map<int, pair<int,int>> info; // taskId -> {userId, priority}

    void push_entry(int taskId) {
        auto it = info.find(taskId);
        if (it != info.end()) {
            pq.emplace(it->second.second, taskId);
        }
    }

public:
    TaskManager(vector<vector<int>>& tasks) {
        for (auto &t : tasks) {
            // t = {userId, taskId, priority}
            add(t[0], t[1], t[2]);
        }
    }

    void add(int userId, int taskId, int priority) {
        info[taskId] = {userId, priority};
        pq.emplace(priority, taskId);
    }

    void edit(int taskId, int newPriority) {
        auto it = info.find(taskId);
        if (it == info.end()) return; // guaranteed by problem, defensive
        it->second.second = newPriority;
        pq.emplace(newPriority, taskId); // old entries become stale
    }

    void rmv(int taskId) {
        info.erase(taskId); // heap entries for taskId become stale
    }

    int execTop() {
        while (!pq.empty()) {
            auto [pri, tid] = pq.top();
            auto it = info.find(tid);
            // valid if task still exists and priority matches current
            if (it != info.end() && it->second.second == pri) {
                int uid = it->second.first;
                pq.pop();
                info.erase(it);
                return uid;
            }
            pq.pop(); // discard stale entry
        }
        return -1;
    }
};

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager* obj = new TaskManager(tasks);
 * obj->add(userId,taskId,priority);
 * obj->edit(taskId,newPriority);
 * obj->rmv(taskId);
 * int param_4 = obj->execTop();
 */
