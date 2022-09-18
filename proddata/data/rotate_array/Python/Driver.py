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
        while line[pos] != ',':
            pos -= 1
        k = int(line[pos + 1:].lstrip())
        nums = list(map(int, [s.strip()
                              for s in line[:pos].rstrip()[1:-1].split(',')]))
        solution.rotate(nums, k)
    else:
        expectedOutput = list(map(int, [s.strip()
                              for s in line[1:-1].split(',')]))
        if (nums != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", nums)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
