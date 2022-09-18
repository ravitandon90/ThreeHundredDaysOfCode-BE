class Solution:
    def romanToInt(self, s: str) -> int:
        romans = {'I': 1, 'V': 5, 'X': 10,
                  'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        res = romans[s[0]]
        for i in range(1, len(s)):
            j = i-1
            if romans[s[j]] < romans[s[i]]:
                res -= romans[s[j]]
                res += (romans[s[i]]-romans[s[j]])
            else:
                res += romans[s[i]]
        return res
