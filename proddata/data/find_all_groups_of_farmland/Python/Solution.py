class Solution:
    def findFarmland(self, land):
        ROWS, COLS, ans = len(land), len(land[0]), []

        def dfs(i, j, v):
            v[0] = max(v[0], i)
            v[1] = max(v[1], j)
            land[i][j] = 0
            if i + 1 != ROWS and land[i+1][j] == 1:
                dfs(i+1, j, v)
            if j + 1 != COLS and land[i][j+1] == 1:
                dfs(i, j+1, v)

        for i in range(ROWS):
            for j in range(COLS):
                if land[i][j] == 1:
                    v = [i, j]
                    dfs(i, j, v)
                    ans.append([i, j]+v)

        return ans
