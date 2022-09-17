from Solution import Solution
import sys
import os


def createListFromString(line):
    array = [s.strip()
             for s in line.strip()[1:-1].split(",")]
    return [] if array[0] == "" else list(
        map(int, array))


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        pos = 0
        while line[pos] != ']':
            pos += 1
        nums = list(map(int, [s.strip() for s in line[1:pos].split(",")]))
        stringFormOfSecondArray = line[pos + 1:].lstrip()[1:].lstrip()
        indices = list(
            map(int, [s.strip() for s in stringFormOfSecondArray[1:-1].split(",")]))
        actualOutput = solution.createTargetArray(nums, indices)
    else:
        expectedOutput = list(map(int, [s.strip()
                              for s in line[1:-1].split(",")]))
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
