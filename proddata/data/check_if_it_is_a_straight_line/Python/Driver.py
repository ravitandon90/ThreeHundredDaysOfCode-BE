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
        listSplitByComma = [s.strip() for s in line[1:-1].split(',')]
        input = []
        for s in listSplitByComma:
            if s[0] == '[':
                input.append([int(s[1:].lstrip())])
            else:
                input[-1].append(int(s[:-1].rstrip()))
        actualOutput = solution.checkStraightLine(input)
    else:
        expectedOutput = True if line == "true" else False
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
