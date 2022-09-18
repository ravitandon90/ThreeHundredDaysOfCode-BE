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
        testCase = line
        pos = len(line) - 1
        while line[pos] != ',':
            pos -= 1
        extraStudents = int(line[pos + 1:].lstrip())
        classes = []
        splitArrays = line[:pos].rstrip()[1:-1].strip()[:-1].split(']')
        for array in splitArrays:
            pos = 0
            while array[pos] != '[':
                pos += 1
            classes.append(
                list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.maxAverageRatio(classes, extraStudents)
    else:
        expectedOutput = '{0:.5f}'.format(float(line))
        actualOutput = '{0:.5f}'.format(actualOutput)
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
