class Solution:
    def letterCasePermutation(self, s: str):

        output = []

        def recursion(i, permutedString):
            if i >= len(s):
                output.append("".join(permutedString))
                return
            recursion(i + 1, permutedString)

            if permutedString[i].isalpha():
                permutedString[i] = permutedString[i].swapcase()
                recursion(i + 1, permutedString)
                permutedString[i] = permutedString[i].swapcase()

        recursion(0, list(s))
        return output
