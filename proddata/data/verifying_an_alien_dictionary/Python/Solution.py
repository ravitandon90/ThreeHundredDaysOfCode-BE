import collections


class Solution(object):
    def isAlienSorted(self, words, order):
        dic = collections.defaultdict(int)
        for index, char in enumerate(order):
            dic[char] = index

        cur = []
        for word in words:
            temp = []
            for char in word:
                temp.append(dic[char])
            cur.append(temp)

        find = False
        for index in range(1, len(cur)):
            length = min(len(cur[index]), len(cur[index-1]))
            if cur[index][:length] == cur[index-1][:length]:
                if len(cur[index]) < len(cur[index-1]):
                    return False
                continue
            for i in range(length):
                if cur[index][i] > cur[index-1][i]:
                    find = True
                    break
                elif cur[index][i] < cur[index-1][i]:
                    return False

            if not find:
                return False
        return True
