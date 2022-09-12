class Solution:
    def generateParenthesis(self, n: int, memo=None):
        queue = [('', n, n)]
        res = []

        while queue:
            s, open_, close = queue.pop()
            if open_ == 0 and close == 1:
                res.append(s+')')
            else:
                if open_ > 0:
                    queue.append((s+'(', open_-1, close))
                if close > open_:
                    queue.append((s+')', open_, close-1))

        return res
