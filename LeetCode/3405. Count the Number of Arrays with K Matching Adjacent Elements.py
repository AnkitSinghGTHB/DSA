MOD = 10**9 + 7

class Solution:
    
    def countGoodArrays(self, n: int, m: int, k: int) -> int:
        #tf is a good array again?
        #the 1st pos arr[0] has m choices
        #for each of rem n-1 indices, 0<1<n, we select k pos frm lft to rght
        #and set arr[i]=arr[i-1]
        #for all otherindices, we set arr[i]!=arr[i-1] with m-1 choices for 
        #each of the n-1-k pos
        # bro check this out, i found on the internet :skull:
        return m * pow(m - 1, n - k - 1, MOD) * math.comb(n - 1, k) % MOD
        
