class Solution:
    def islandPerimeter(self, grid) -> int:

        m = len(grid)
        n = len(grid[0])
        visited = set()

        def dfs(i, j):
            if (i, j) in visited:
                return 0
            if i < 0 or i >= m or j < 0 or j >= n or grid[i][j] == 0:
                return 1
            visited.add((i, j))
            return dfs(i+1, j)+dfs(i-1, j)+dfs(i, j+1)+dfs(i, j-1)

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    return dfs(i, j)
        return 0
