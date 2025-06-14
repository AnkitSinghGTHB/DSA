class Solution:
    def minMaxDifference(self, num: int) -> int:
        #i can try bruteforce lol, but that would give an issue for big nums
        #what i can do is get max num by replacing one digit with 9
        #and getting min num by replacing one digit with 0
        #another thing, i would try to exclude first digit for min
        #nvm i should just shift to python
        s = str(num)
        mi = int(s.replace(s[0], '0'))
        for c in s:
            if c != '9':
                return int(s.replace(c, '9')) - mi
        return num - mi
