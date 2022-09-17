from Solution import Solution
import sys
import os

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    if lineNumber % 2 == 1:
        input = [c.strip()[1:-1] for c in line.strip()[1:-1].split(',')]
        actualOutput = solution.reverseString(input)
    else:
        expectedOutput = [c.strip()[1:-1]
                          for c in line.strip()[1:-1].split(',')]
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
