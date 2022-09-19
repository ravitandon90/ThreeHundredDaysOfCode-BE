class Solution:
    def findItinerary(self, tickets):
        # * Sort in lexical order.
        tickets.sort()
        graph = {src: [] for src, _ in tickets}
        res = ["JFK"]

        def build_graph():
            for src, dest in tickets:
                graph[src].append(dest)

        def findItineraryHelper(src):
            if len(res) == len(tickets) + 1:
                return True
            if src not in graph:
                return False

            for idx, dest in enumerate(graph[src]):
                if dest == "*":
                    continue

                graph[src][idx] = "*"
                res.append(dest)
                if findItineraryHelper(dest):
                    return True

                graph[src][idx] = dest
                res.pop()
            return False

        build_graph()
        findItineraryHelper("JFK")
        return res
