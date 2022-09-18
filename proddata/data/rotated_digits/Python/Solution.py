class Solution:
    def rotatedDigits(self, n: int) -> int:
        count = 0
        for k in range(1, n+1):
            if any(m in str(k) for m in '347'):
                continue
            if any(m in str(k) for m in '2569'):
                count += 1
        return count
