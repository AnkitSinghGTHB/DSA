class Solution {
public:
    int mostBooked(int n, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());
        priority_queue<int, vector<int>, greater<int>> free_rooms;
        for (int i = 0; i < n; i++) {
            free_rooms.push(i);
        }
        priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<>> busy_rooms;
        vector<long long> count(n, 0);

        for (auto& m : meetings) {
            long long s = m[0];
            long long e = m[1];

            while (!busy_rooms.empty() && busy_rooms.top().first <= s) {
                int room = busy_rooms.top().second;
                busy_rooms.pop();
                free_rooms.push(room);
            }

            if (!free_rooms.empty()) {
                int room = free_rooms.top();
                free_rooms.pop();
                busy_rooms.push({e, room});
                count[room]++;
            } else {
                auto [end_time, room] = busy_rooms.top();
                busy_rooms.pop();
                long long duration = e - s;
                long long new_end = end_time + duration;
                busy_rooms.push({new_end, room});
                count[room]++;
            }
        }

        int max_count = 0;
        int result_room = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > max_count) {
                max_count = count[i];
                result_room = i;
            }
        }
        return result_room;
    }
};
