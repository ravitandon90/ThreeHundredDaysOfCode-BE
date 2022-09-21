class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        for i in s:
            # Find position of i in s, we will use this create our slices for evaluation
            if t.find(i) < 0:
                # If the find operation can't find i it returns -1
                return False
            else:
                position = t.find(i)  # location of i
                t = t[position+1:]
        return True
