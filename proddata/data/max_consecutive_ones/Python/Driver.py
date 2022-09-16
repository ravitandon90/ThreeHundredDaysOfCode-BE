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
    if lineNumber % 2 == 1:
        input = createListFromString(line)
        actualOutput = solution.findMaxConsecutiveOnes(input)
    else:
        expectedOutput = int(line.strip())
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
