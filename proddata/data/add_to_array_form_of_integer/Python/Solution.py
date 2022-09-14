class Solution:
    def addToArrayForm(self, num, k):
        return [int(x) for x in str(int("".join([str(x) for x in num])) + k)]
