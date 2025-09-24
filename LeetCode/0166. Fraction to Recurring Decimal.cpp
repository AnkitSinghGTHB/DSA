class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        string res;
        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) res += '-';
        // Use long long to avoid overflow
        long long n = abs((long long)numerator);
        long long d = abs((long long)denominator);
        // Append integer part
        res += to_string(n / d);
        long long remainder = n % d;
        if (remainder == 0) return res;
        res += '.';
        // Map to store the remainder and its index in the result string
        unordered_map<long long, int> remainder_map;
        while (remainder != 0) {
            if (remainder_map.find(remainder) != remainder_map.end()) {
                // Insert '(' at the index where this remainder first appeared
                res.insert(remainder_map[remainder], 1, '(');
                res += ')';
                break;
            }
            remainder_map[remainder] = res.size();
            remainder *= 10;
            res += to_string(remainder / d);
            remainder %= d;
        }
        return res;
    }
};
