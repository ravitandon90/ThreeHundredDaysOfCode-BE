from collections import defaultdict


class Solution:
    def groupAnagrams(self, strs):
        hashed = defaultdict(list)

        for ana in strs:
            key = ''.join(sorted(ana))
            hashed[key].append(ana)

        return list(hashed.values())
