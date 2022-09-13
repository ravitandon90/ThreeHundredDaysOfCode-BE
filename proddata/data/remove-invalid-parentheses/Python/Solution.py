class Solution(object):

    def removeInvalidParentheses(self, s):
        self.max_len = 0
        self.res = set()

        def backtrack(indx, left, right, temp):
            if right > left:
                return
            if indx == len(s):
                if left == right and self.max_len <= left:
                    self.max_len = left
                    self.res.add("".join(temp))
                return

            if s[indx] == "(":
                backtrack(indx+1, left + 1, right, temp + ["("])
                backtrack(indx+1, left, right, temp)
            elif s[indx] == ")":
                backtrack(indx+1, left, right + 1, temp + [")"])
                backtrack(indx+1, left, right, temp)
            else:
                backtrack(indx+1, left, right, temp + [s[indx]])

        backtrack(0, 0, 0, [])
        return list(self.res)
