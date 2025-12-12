class Solution {
public:
    vector<int> countMentions(int numberOfUsers, vector<vector<string>>& events) {
        vector<int> mentions(numberOfUsers, 0);
        vector<int> offlineUntil(numberOfUsers, 0); // initially 0, meaning online
        //prep events for sorting: timestamp, priority (0 for OFFLINE, 1 for MESSAGE), data, type
        vector<tuple<int, int, string, string>> sortedEvents;
        for (const auto& e : events) {
            string type = e[0];
            int timestamp = stoi(e[1]);
            string data = e[2];
            int priority = (type == "OFFLINE") ? 0 : 1;
            sortedEvents.emplace_back(timestamp, priority, data, type);
        }        
        //sort by timestamp, then by priority (OFFLINE before MESSAGE)
        sort(sortedEvents.begin(), sortedEvents.end());
        for (const auto& [timestamp, priority, data, type] : sortedEvents) {
            if (type == "OFFLINE") {
                int user = stoi(data);
                offlineUntil[user] = timestamp + 60;
            } 
            else { //MESSAGE
                if (data == "ALL") {
                    for (int i = 0; i < numberOfUsers; ++i) {
                        ++mentions[i];
                    }
                } 
                else if (data == "HERE") {
                    for (int i = 0; i < numberOfUsers; ++i) {
                        if (timestamp >= offlineUntil[i]) {
                            ++mentions[i];
                        }
                    }
                } 
                else {
                    //split the string by spaces; each token is "idX"
                    istringstream iss(data);
                    string token;
                    while (iss >> token) {
                        //token format: "idX", extract X
                        int user = stoi(token.substr(2));
                        ++mentions[user];
                    }
                }
            }
        }        
        return mentions;
    }
};
