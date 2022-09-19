class Solution:
    def isPerfectSquare(self, num: int) -> bool:
        if num == 1:
            return True
        low, high = 1, num//2
        while low <= high:
            m = (low+high)//2
            if m*m == num:
                return True
            elif m*m < num:
                low = m+1
            else:
                high = m-1
        return False
