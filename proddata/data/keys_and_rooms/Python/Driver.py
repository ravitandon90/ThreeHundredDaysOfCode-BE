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
        input = []
        splitArrays = line[1:-1].rstrip()[:-1].split(']')
        for array in splitArrays:
            pos = 0
            while array[pos] != '[':
                pos += 1
            if array[pos + 1:].strip() == "":
                input.append([])
            else:
                input.append(
                    list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.canVisitAllRooms(input)
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
