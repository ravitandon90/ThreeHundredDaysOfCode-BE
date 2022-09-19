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
        pos = 0
        while line[pos] != ',':
            pos += 1
        s = line[:pos].rstrip()[1:-1].strip()
        wordDict = [s.strip()[1:-1].strip()
                    for s in line[pos + 1:].lstrip()[1:-1].split(',')]
        actualOutput = solution.wordBreak(s, wordDict)
    else:
        expectedOutput = [] if line[1:-1].strip() == "" else [s.strip()[1:-1].strip()
                                                              for s in line[1:-1].split(',')]
        actualOutput.sort()
        expectedOutput.sort()
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
