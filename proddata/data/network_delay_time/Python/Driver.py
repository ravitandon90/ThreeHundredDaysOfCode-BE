from Solution import Solution
import sys
import os

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        ind1 = len(line) - 1
        while line[ind1] != ',':
            ind1 -= 1
        k = int(line[ind1 + 1:].lstrip())
        ind2 = ind1 - 1
        while line[ind2] != ',':
            ind2 -= 1
        n = int(line[ind2 + 1: ind1].strip())
        times = []
        splitArrays = line[1:ind2].rstrip()[:-1].rstrip()[:-1].split(']')
        for array in splitArrays:
            pos = 0
            while array[pos] != '[':
                pos += 1
            times.append(
                list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.networkDelayTime(times, n, k)
    else:
        expectedOutput = int(line)
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
