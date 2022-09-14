from collections import defaultdict


class Solution:
    def __init__(self):
        self.accountConnections = defaultdict(set)
        self.emailToName = dict()
        self.seen = set()

    def buildConnections(self, accounts):
        for account in accounts:
            name = account[0]
            key = account[1]
            for i in range(1, len(account)):
                self.accountConnections[key].add(account[i])
                self.accountConnections[account[i]].add(key)
                self.emailToName[account[i]] = name

    def walkAccountNode(self, accountNode):
        if accountNode in self.seen:
            return []

        self.seen.add(accountNode)
        connections = self.accountConnections[accountNode]
        result = [accountNode]

        for connection in connections:
            result += self.walkAccountNode(connection)

        return result

    def accountsMerge(self, accounts):
        self.buildConnections(accounts)
        mergedAccounts = []

        for account in self.accountConnections:
            localMerge = self.walkAccountNode(account)
            name = self.emailToName[account]
            if localMerge:
                localMerge.sort()
                mergedAccounts.append([name] + localMerge)

        return mergedAccounts
