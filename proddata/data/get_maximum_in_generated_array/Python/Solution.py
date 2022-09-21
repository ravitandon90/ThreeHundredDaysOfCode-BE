class Solution:

    def getMaximumGenerated(self, n: int) -> int:

        if n == 0 or n == 1:
            return n

        arr = [0]*(n+1)
        arr[1] = 1
        largest = 1

        for i in range(2, n+1):
            if i % 2 == 0:
                arr[i] = arr[i//2]
            else:
                arr[i] = arr[i//2]+arr[i//2+1]

            if arr[i] > largest:
                largest = arr[i]

        return largest
