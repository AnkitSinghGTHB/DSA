class Solution {
public:
//ai generated solution/ please ignore this as my skill
struct BBox {
    int min_i = INT_MAX, max_i = -1;
    int min_j = INT_MAX, max_j = -1;
    void add(int i, int j) {
        min_i = min(min_i, i);
        max_i = max(max_i, i);
        min_j = min(min_j, j);
        max_j = max(max_j, j);
    }
    int area() {
        if (min_i == INT_MAX) return 0;
        return (max_i - min_i + 1) * (max_j - min_j + 1);
    }
};

    int minimumSum(vector<vector<int>>& grid) {
        //ts enum? i dont have the guts to solve this all by myself
        int m = grid.size();
        int n = grid[0].size();
        vector<pair<int, int>> ones;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ones.push_back({i, j});
                }
            }
        }
        
        int ans = INT_MAX;
        
        // Case 1: horizontal strips
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (r <= i) b1.add(r, c);
                    else if (r <= j) b2.add(r, c);
                    else b3.add(r, c);
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        // Case 2: vertical strips
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (c <= i) b1.add(r, c);
                    else if (c <= j) b2.add(r, c);
                    else b3.add(r, c);
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        // Case 3: first horizontal then vertical on bottom
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (r <= i) b1.add(r, c);
                    else {
                        if (c <= j) b2.add(r, c);
                        else b3.add(r, c);
                    }
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        // Case 4: first horizontal then vertical on top
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (r > i) b3.add(r, c);
                    else {
                        if (c <= j) b1.add(r, c);
                        else b2.add(r, c);
                    }
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        // Case 5: first vertical then horizontal on right
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (c <= j) b1.add(r, c);
                    else {
                        if (r <= i) b2.add(r, c);
                        else b3.add(r, c);
                    }
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        // Case 6: first vertical then horizontal on left
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                BBox b1, b2, b3;
                for (auto [r, c] : ones) {
                    if (c > j) b3.add(r, c);
                    else {
                        if (r <= i) b1.add(r, c);
                        else b2.add(r, c);
                    }
                }
                ans = min(ans, b1.area() + b2.area() + b3.area());
            }
        }
        
        return ans;
    }
};
