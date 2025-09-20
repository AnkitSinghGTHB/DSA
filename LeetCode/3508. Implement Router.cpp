struct Packet {
    int s, d, t;
    bool operator==(const Packet& o) const { return s == o.s && d == o.d && t == o.t; }
};

struct PacketHash {
    size_t operator()(const Packet& p) const {
        // Splitmix-like combine for better distribution
        auto h1 = std::hash<int>{}(p.s);
        auto h2 = std::hash<int>{}(p.d);
        auto h3 = std::hash<int>{}(p.t);
        auto mix = [](size_t x) {
            x += 0x9e3779b97f4a7c15ULL;
            x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9ULL;
            x = (x ^ (x >> 27)) * 0x94d049bb133111ebULL;
            return x ^ (x >> 31);
        };
        return mix(h1) ^ (mix(h2) << 1) ^ (mix(h3) << 2);
    }
};
//i used help from pplxt
class Router {
private:
    size_t memoryLimit;
    queue<Packet> q;                                   // FIFO
    unordered_set<Packet, PacketHash> inRouter;        // duplicate check
    unordered_map<int, vector<int>> destTimes;         // per-destination sorted timestamps
    unordered_map<int, int> headIdx;                   // lazy front removal index

    void advanceHead(int d) {
        int& h = headIdx[d];
        auto& v = destTimes[d];
        if (h < (int)v.size()) ++h;
        if (h >= (int)v.size()) {
            // Everything consumed; reclaim memory
            v.clear();
            v.shrink_to_fit();
            h = 0;
        } else if (h >= 1024 && h * 2 >= (int)v.size()) {
            // Periodically compact to avoid large unused prefix
            v.erase(v.begin(), v.begin() + h);
            h = 0;
            v.shrink_to_fit();
        }
    }

public:
    Router(int memoryLimit) : memoryLimit(memoryLimit) {}

    bool addPacket(int source, int destination, int timestamp) {
        Packet pkt{source, destination, timestamp};
        if (inRouter.find(pkt) != inRouter.end()) return false;  // duplicate

        if (q.size() >= memoryLimit) {
            // Evict oldest
            Packet old = q.front();
            q.pop();
            inRouter.erase(old);
            advanceHead(old.d);
        }

        q.push(pkt);
        inRouter.insert(pkt);
        destTimes[destination].push_back(timestamp);   // timestamps stay sorted per destination
        return true;
    }

    vector<int> forwardPacket() {
        if (q.empty()) return {};
        Packet pkt = q.front();
        q.pop();
        inRouter.erase(pkt);
        advanceHead(pkt.d);
        return {pkt.s, pkt.d, pkt.t};
    }

    int getCount(int destination, int startTime, int endTime) {
        auto it = destTimes.find(destination);
        if (it == destTimes.end()) return 0;
        auto& v = it->second;
        int h = headIdx[destination];
        if (h >= (int)v.size()) return 0;
        auto lb = lower_bound(v.begin() + h, v.end(), startTime);
        auto ub = upper_bound(v.begin() + h, v.end(), endTime);
        return int(ub - lb);
    }
};
