class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        i = len(s)-1
        j = len(t)-1
        c = 0
        ct = 0
        a = ""
        b = ""
        while i >= 0:
            if s[i] == "#":
                c += 1
            elif c > 0:
                c -= 1
            else:
                a += s[i]
            i -= 1
        while j >= 0:
            if t[j] == "#":
                ct += 1
            elif ct > 0:
                ct -= 1
            else:
                b += t[j]
            j -= 1
        if a == b:
            return True
        else:
            return False
