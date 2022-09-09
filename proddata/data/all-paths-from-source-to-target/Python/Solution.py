class Solution:
    def allPathsSourceTarget(self, graph):

        paths = []

        visited = []

        def dfs(source, target, visited):
            visited.append(source)
            if source == target:
                paths.append(visited.copy())

            for neighbour in graph[source]:
                dfs(neighbour, target, visited)

            visited.pop()
            return paths

        return dfs(0, len(graph)-1, visited)
