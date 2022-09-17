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
        pos = len(line) - 1
        while line[pos] != '[':
            pos -= 1
        indices = list(map(int, [s.strip()
                       for s in line[pos + 1: -1].split(',')]))
        while line[pos] != ',':
            pos -= 1
        string = line[:pos].strip()[1:-1].strip()
        actualOutput = solution.restoreString(string, indices)
    else:
        expectedOutput = line[1:-1].strip()
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
