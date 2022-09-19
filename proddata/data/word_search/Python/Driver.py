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
        pos = len(line) - 1
        while line[pos] != ',':
            pos -= 1
        word = line[pos + 1:].lstrip()[1:-1].strip()
        board = []
        splitArrays = line[:pos].rstrip()[1:-1].strip()[:-1].split(']')
        for array in splitArrays:
            pos = 0
            while array[pos] != '[':
                pos += 1
            board.append([s.strip()[1:-1].strip()
                         for s in array[pos + 1:].split(',')])
        actualOutput = solution.exist(board, word)
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
