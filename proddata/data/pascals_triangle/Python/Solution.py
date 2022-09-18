class Solution:
    def generate(self, numRows):
        arr = [[0 for _ in range(i)] for i in range(1, numRows + 1)]

        for row in range(numRows):
            for i in range(row + 1):
                if i == 0 or i == row:
                    arr[row][i] = 1
                else:
                    arr[row][i] = arr[row - 1][i - 1] + arr[row - 1][i]
        return arr
