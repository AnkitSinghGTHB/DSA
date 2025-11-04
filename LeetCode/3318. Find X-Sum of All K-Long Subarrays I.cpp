class Solution {
public:
    vector<int> findXSum(vector<int>& nums, int k, int x) {
        int n = nums.size();
        vector<int> result;
        
        // Frequency array (values from 1 to 50)
        vector<int> freq(51, 0);
        
        // Initialize first window
        for (int i = 0; i < k; i++) {
            freq[nums[i]]++;
        }
        
        // Process each window
        for (int i = 0; i <= n - k; i++) {
            // Calculate x-sum for current window
            result.push_back(calculateXSum(freq, x));
            
            // Move window: remove left element, add right element
            if (i < n - k) {
                freq[nums[i]]--;
                freq[nums[i + k]]++;
            }
        }
        
        return result;
    }
    
    int calculateXSum(vector<int>& freq, int x) {
        // Create pairs of (value, frequency) for non-zero frequencies
        vector<pair<int, int>> elements;
        for (int val = 1; val <= 50; val++) {
            if (freq[val] > 0) {
                elements.push_back({val, freq[val]});
            }
        }
        
        // Sort by frequency (descending), then by value (descending)
        sort(elements.begin(), elements.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            if (a.second != b.second) {
                return a.second > b.second; // Higher frequency first
            }
            return a.first > b.first; // Higher value first for same frequency
        });
        
        // Take top x elements and sum all their occurrences
        int sum = 0;
        int count = min(x, (int)elements.size());
        for (int i = 0; i < count; i++) {
            sum += elements[i].first * elements[i].second;
        }
        
        return sum;
    }
};
