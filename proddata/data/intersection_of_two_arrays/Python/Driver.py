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
        line = line.strip()
        pos = 0
        while not line[pos] == ']':
            pos += 1
        array1 = list(map(int, [num.strip()
                                for num in line[1:pos].split(",")]))
        while not line[pos] == '[':
            pos += 1
        array2 = list(map(int, [num.strip()
                                for num in line[pos + 1: -1].split(",")]))
        actualOutput = solution.intersection(array1, array2)
    else:
        expectedOutput = list(
            map(int, [num.strip() for num in line.strip()[1:-1].split(",")]))
        actualOutput.sort()
        expectedOutput.sort()
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
