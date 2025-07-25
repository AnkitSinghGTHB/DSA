class Solution:
    def maxSum(self, nums: List[int]) -> int:
        if max(nums) < 0:
            return max(nums)
        return sum(set(x for x in nums if x >= 0))
