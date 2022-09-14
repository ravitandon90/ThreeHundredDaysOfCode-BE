class Solution:
    def addStrings(self, num1: str, num2: str) -> str:
        res = ""
        reminder = 0
        if len(num1) > len(num2):
            temp = num1
            num1 = num2
            num2 = temp

        i = len(num1) - 1
        j = len(num2) - 1
        while i >= 0:

            sum_ = (ord(num1[i]) - ord('0')) + \
                (ord(num2[j]) - ord('0')) + reminder
            reminder = sum_ // 10
            res = chr(sum_ % 10 + ord('0')) + res
            i -= 1
            j -= 1

        while j >= 0:
            sum_ = (ord(num2[j]) - ord('0')) + reminder
            reminder = sum_ // 10
            res = chr(sum_ % 10 + ord('0')) + res
            j -= 1

        if reminder != 0:
            res = chr(reminder + ord('0')) + res

        return res
