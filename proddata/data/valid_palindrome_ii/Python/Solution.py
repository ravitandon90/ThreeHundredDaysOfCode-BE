class Solution:
    def validPalindrome(self, s: str) -> bool:
        a = s[::-1]
        i = 0
        j = len(s)-1
        if(s == a):
            return True
        else:
            while(i < j):
                if(s[i] == s[j]):
                    i = i+1
                    j = j-1
                elif(s[i+1:j+1] == s[i+1:j+1][::-1]):
                    return True
                elif(s[i:j] == s[i:j][::-1]):
                    return True
                else:
                    return False
            return False
