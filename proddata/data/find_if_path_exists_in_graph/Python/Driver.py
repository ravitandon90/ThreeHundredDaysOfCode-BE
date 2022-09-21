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
        testCase = line
        ind1 = 0
        while line[ind1] != ',':
            ind1 += 1
        n = int(line[:ind1].rstrip())
        ind2 = len(line) - 1
        while line[ind2] != ',':
            ind2 -= 1
        end = int(line[ind2 + 1:].lstrip())
        ind = ind2
        ind2 -= 1
        while line[ind2] != ',':
            ind2 -= 1
        start = int(line[ind2 + 1: ind].strip())
        edges = []
        matrixWithNoOuterBraces = line[ind1 + 1: ind2].strip()[1:-1].strip()
        if matrixWithNoOuterBraces != "":
            splitArrays = matrixWithNoOuterBraces[:-1].split(']')
            for array in splitArrays:
                pos = 0
                while array[pos] != '[':
                    pos += 1
                edges.append(
                    list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.validPath(n, edges, start, end)
    else:
        expectedOutput = True if line == "true" else False
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
