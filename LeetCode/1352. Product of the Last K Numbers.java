import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    // prefix[i] = product of all numbers since the last zero (inclusive),
    // with prefix[0] = 1 as a sentinel.
    private List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            // A zero resets the product chain.
            prefix.clear();
            prefix.add(1);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        int size = prefix.size();
        // If k is larger than the numbers since the last zero,
        // the product must include a zero.
        if (k > size - 1) {
            return 0;
        }
        return prefix.get(size - 1) / prefix.get(size - 1 - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
