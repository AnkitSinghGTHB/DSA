class Solution {
public:
    int countTrapezoids(vector<vector<int>>& points) {
        int n = points.size();
        
        // 1. Group segments by Slope -> Intercept -> List of Segments
        // Key: {dy, dx} -> {b_num, b_den} -> count of segments
        map<pair<int,int>, map<pair<long long, long long>, int>> slopeToLines;
        
        // 2. Group segments by Vector -> Line -> Count
        // Key: {dy, dx} (raw) -> {b_num, b_den} -> count of segments
        map<pair<int,int>, map<pair<long long, long long>, int>> vectorGroups;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // 1. Process for Parallelogram (Vector) Groups
                // Normalize vector direction to point "up/right" to group parallel vectors together
                int v_dx = dx;
                int v_dy = dy;
                if (v_dx < 0 || (v_dx == 0 && v_dy < 0)) {
                    v_dx = -v_dx;
                    v_dy = -v_dy;
                }
                
                // Calculate intercept for vector group
                // We need to use a normalized slope for the intercept calculation to ensure consistent keys
                int g_v = gcd(abs(v_dx), abs(v_dy));
                int norm_v_dx = v_dx / g_v;
                int norm_v_dy = v_dy / g_v;
                
                pair<long long, long long> v_intercept;
                if (norm_v_dx == 0) {
                    v_intercept = {points[i][0], 1};
                } else {
                    // b = y - (dy/dx)x => b*dx = y*dx - dy*x
                    // Use normalized slope for stability
                    long long bNum = (long long)points[i][1] * norm_v_dx - (long long)norm_v_dy * points[i][0];
                    long long bDen = norm_v_dx;
                    v_intercept = {bNum, bDen};
                }
                vectorGroups[{v_dy, v_dx}][v_intercept]++;

                // 2. Process for Trapezoid (Slope) Groups
                // Fully normalize slope
                int g = gcd(abs(dx), abs(dy));
                dx /= g;
                dy /= g;
                
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                
                pair<long long, long long> intercept;
                if (dx == 0) {
                    intercept = {points[i][0], 1};
                } else {
                    long long bNum = (long long)points[i][1] * dx - (long long)dy * points[i][0];
                    long long bDen = dx;
                    intercept = {bNum, bDen}; 
                }
                
                slopeToLines[{dy, dx}][intercept]++;
            }
        }
        
        long long totalTrapezoids = 0;
        
        // Calculate Total Trapezoids (includes double-counted parallelograms)
        for (auto& [slope, lines] : slopeToLines) {
            long long sumCounts = 0;
            long long sumSqCounts = 0;
            for (auto& [intercept, count] : lines) {
                sumCounts += count;
                sumSqCounts += (long long)count * count;
            }
            totalTrapezoids += (sumCounts * sumCounts - sumSqCounts) / 2;
        }
        
        long long totalParallelogramSides = 0;
        
        // Calculate Total Parallelogram Side Pairs
        for (auto& [vec, lines] : vectorGroups) {
            long long sumCounts = 0;
            long long sumSqCounts = 0;
            for (auto& [intercept, count] : lines) {
                sumCounts += count;
                sumSqCounts += (long long)count * count;
            }
            totalParallelogramSides += (sumCounts * sumCounts - sumSqCounts) / 2;
        }
        
        // Each parallelogram is counted twice in totalParallelogramSides (once for each pair of parallel sides)
        // So the number of parallelograms is totalParallelogramSides / 2
        // Each parallelogram was counted twice in totalTrapezoids
        // Each pure trapezoid was counted once in totalTrapezoids
        // We want: (Pure) + (Parallelograms)
        // totalTrapezoids = (Pure) + 2 * (Parallelograms)
        // We need to subtract (Parallelograms)
        // (Parallelograms) = totalParallelogramSides / 2
        
        return totalTrapezoids - (totalParallelogramSides / 2);
    }

private:
    int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
};
//ps. i solved it using ai
