class Solution:
    def countSubstrings(self, s: str) -> int:
        count = 0
        for begin in range(len(s)):
            for i in range(begin+1, len(s) + 1):
                current_substr = s[begin:i]
                if current_substr == current_substr[::-1]:
                    count += 1
        return count
