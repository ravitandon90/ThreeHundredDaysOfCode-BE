class Solution:
    def maximumWealth(self, accounts) -> int:
        return max(map(sum, accounts))
