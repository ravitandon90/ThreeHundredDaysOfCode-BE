from Solution import Solution
import sys
import os
import copy

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        s1, s2 = [s.strip()[1:-1].strip() for s in line.split(',')]
        actualOutput = solution.uncommonFromSentences(s1, s2)
    else:
        expectedOutput = [s.strip()[1:-1].strip()
                          for s in line[1:-1].strip().split(',')]
        copyOfActualOutput = copy.deepcopy(actualOutput)
        copyOfActualOutput.sort()
        expectedOutput.sort()
        if (copyOfActualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
