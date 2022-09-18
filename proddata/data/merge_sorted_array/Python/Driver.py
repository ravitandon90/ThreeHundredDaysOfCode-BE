from Solution import Solution
import sys
import os


def createListFromString(line):
    array = [s.strip()
             for s in line.strip()[1:-1].split(",")]
    return [] if array[0] == "" else list(
        map(int, array))


def helper(line):
    pos = len(line) - 1
    while line[pos] != ',':
        pos -= 1
    length = int(line[pos + 1:].lstrip())
    arr = createListFromString(line[:pos])
    return (arr, length)


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        arr1, arr2 = [], []
        pos = len(line) - 1
        while line[pos] != '[':
            pos -= 1
        arr2, n = helper(line[pos:])
        while line[pos] != ',':
            pos -= 1
        arr1, m = helper(line[:pos].rstrip())
        solution.merge(arr1, m, arr2, n)
    else:
        expectedOutput = createListFromString(line)
        if (arr1 != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", arr1)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
