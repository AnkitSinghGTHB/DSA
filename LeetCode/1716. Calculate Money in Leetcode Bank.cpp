class Solution {
public:
    int totalMoney(int n) {
        int fullWeeks = n / 7;
        int remainingDays = n % 7;

        // Sum for complete weeks: week1 + week2 + ... + week_k
        // week_i sum = 7*(i) + 28
        int weeksSum = fullWeeks * 28 + 7 * (fullWeeks * (fullWeeks - 1)) / 2;

        // Sum for remaining days
        int monday = 1 + fullWeeks;
        int remainingSum =
            remainingDays * monday + (remainingDays * (remainingDays - 1)) / 2;

        return weeksSum + remainingSum;
    }
};
