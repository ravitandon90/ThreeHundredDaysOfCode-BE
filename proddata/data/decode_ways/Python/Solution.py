class Solution:
    def numDecodings(self, s: str) -> int:
        def dp(i):
            if i >= len(s):
                return 1
            if s[i] == '0':
                return 0
            singleDigit = dp(i+1)
            if i >= len(s)-1:
                return singleDigit
            doubleDigit = 0
            if int(s[i]+s[i+1]) <= 26:
                doubleDigit = dp(i+2)
            return singleDigit + doubleDigit

        return dp(0)
