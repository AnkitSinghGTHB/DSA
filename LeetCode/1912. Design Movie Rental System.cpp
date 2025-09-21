class MovieRentingSystem {
private:
    unordered_map<int, set<pair<int, int>>> unrented;
    set<tuple<int, int, int>> rented;
    map<pair<int, int>, int> price_map;

public:
    //erm
    MovieRentingSystem(int n, vector<vector<int>>& entries) {
        for (auto& entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];
            price_map[{shop, movie}] = price;
            unrented[movie].insert({price, shop});
        }
    }
    
    vector<int> search(int movie) {
        vector<int> result;
        if (unrented.find(movie) == unrented.end()) {
            return result;
        }
        int count = 0;
        for (auto& p : unrented[movie]) {
            result.push_back(p.second);
            count++;
            if (count == 5) break;
        }
        return result;
    }
    
    void rent(int shop, int movie) {
        int price = price_map[{shop, movie}];
        unrented[movie].erase({price, shop});
        rented.insert({price, shop, movie});
    }
    
    void drop(int shop, int movie) {
        int price = price_map[{shop, movie}];
        rented.erase({price, shop, movie});
        unrented[movie].insert({price, shop});
    }
    
    vector<vector<int>> report() {
        vector<vector<int>> result;
        int count = 0;
        for (auto& t : rented) {
            result.push_back({get<1>(t), get<2>(t)});
            count++;
            if (count == 5) break;
        }
        return result;
    }
};

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem* obj = new MovieRentingSystem(n, entries);
 * vector<int> param_1 = obj->search(movie);
 * obj->rent(shop,movie);
 * obj->drop(shop,movie);
 * vector<vector<int>> param_4 = obj->report();
 */
