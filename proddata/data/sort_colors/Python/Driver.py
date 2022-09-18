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
        input = list(map(int, [s.strip()
                     for s in line.strip()[1:-1].split(',')]))
        solution.sortColors(input)
    else:
        expectedOutput = list(map(int, [s.strip()
                              for s in line.strip()[1:-1].split(',')]))
        if (input != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", input)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
