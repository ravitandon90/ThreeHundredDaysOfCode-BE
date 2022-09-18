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
        while line[pos] != '[':
            pos -= 1
        interval = list(map(int, [s.strip()
                        for s in line[pos:][1:-1].split(',')]))
        listOfIntervals = []
        while line[pos] != ',':
            pos -= 1
        arrayAfterRemovingBraces = line[:pos].rstrip()[1:-1].strip()
        if arrayAfterRemovingBraces != "":
            splitArrays = arrayAfterRemovingBraces[:-1].split(']')
            for array in splitArrays:
                pos = 0
                while array[pos] != '[':
                    pos += 1
                listOfIntervals.append(
                    list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.insert(listOfIntervals, interval)
    else:
        expectedOutput = []
        splitArrays = line[1:-1].strip()[:-1].split(']')
        for array in splitArrays:
            pos = 0
            while array[pos] != '[':
                pos += 1
            expectedOutput.append(
                list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
