class Solution(object):
    def addBinary(self, a, b):
        num1, num2, ans = int(a), int(b), ""
        if num1 == 0 and num2 == 0:
            return "0"

        def deci(num):
            sum, i = 0, 0
            while num > 0:
                rem = num % 10
                sum += rem*pow(2, i)
                num //= 10
                i += 1
            return sum
        ans1 = deci(num1)
        ans2 = deci(num2)
        result = ans1+ans2
        while result > 0:
            rem2 = result % 2
            result = result//2
            ans += str(rem2)
        return ans[::-1]
