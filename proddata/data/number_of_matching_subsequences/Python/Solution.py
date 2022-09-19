class Solution:
    def numMatchingSubseq(self, s: str, words) -> int:
        seenWr = {}

        def findSubSeq(wr):
            p1 = p2 = 0

            if wr in seenWr:
                return seenWr[wr]

            while(p1 < len(s)):
                if p2 == len(wr):
                    res = True
                    seenWr[wr] = res
                    return res

                if s[p1] == wr[p2]:
                    p1 += 1
                    p2 += 1
                else:
                    p1 += 1
            res = False if p2 != len(wr) else True
            seenWr[wr] = res
            return res

        res = 0
        for wr in words:
            res += findSubSeq(wr)

        return res
