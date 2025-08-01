class FindSumPairs:
    #my life is a suffering, thankfully i know python
    def __init__(self, nums1: List[int], nums2: List[int]):
        self.nums1=nums1
        self.nums2=nums2
        self.count2=collections.Counter(nums2)

    def add(self, index: int, val: int) -> None:
        self.count2[self.nums2[index]]-=1
        self.nums2[index]+=val
        self.count2[self.nums2[index]]+=1

    def count(self, tot: int) -> int:
        ans=0
        for num in self.nums1:
            ans+=self.count2[tot-num]
        return ans


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)
