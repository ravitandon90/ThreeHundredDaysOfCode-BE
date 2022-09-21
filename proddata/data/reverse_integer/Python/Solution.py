class Solution:
    def reverse_helper(self, num_q):
        num_str = ''
        while num_q > 0:
            rem = num_q % 10
            num_q = int(num_q/10)
            num_str += str(rem)

        return num_str

    def reverse(self, x: int) -> int:

        if x > 0:
            res = int(self.reverse_helper(x))
            if res > pow(2, 31):
                return 0
            return res
        elif x == 0:
            return 0
        else:
            x = -1 * x
            res = -1 * int(self.reverse_helper(x))

            if res <= -1 * pow(2, 31):
                return 0
        return res
