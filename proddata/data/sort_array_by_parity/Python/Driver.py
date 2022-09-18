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
        input = list(map(int, [s.strip() for s in line[1:-1].split(',')]))
        actualOutput = solution.sortArrayByParity(input)
    else:
        expectedOutput = list(map(int, [s.strip()
                              for s in line[1:-1].split(',')]))
        oddStarted = False
        for num in actualOutput:
            if num % 2 == 0 and oddStarted:
                isSolutionWrong = True
                break
            if num % 2 == 1:
                oddStarted = True
        if (isSolutionWrong):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
