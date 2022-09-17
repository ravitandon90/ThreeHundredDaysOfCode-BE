class Solution:
    def twoEggDrop(self, n: int) -> int:
        dp=[]
        c=0
        while n>0:
            c+=1
            dp.append(n-c)
            n-=c
            if dp[-1]<=0:
                break
        return(len(dp))