class Solution:
    def countBits(self, n):
        list = [0, 1, 1]
        if n < 2:
            return list[:n+1]
        while len(list) <= n:
            list_generator = []
            for num in list[1:-1]:
                num += 1
                list_generator.append(num)
            list_generator.append(1)
            list += list_generator

        return list[:n+1]
