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
        input = line[1:-1].strip()
        actualOutput = solution.frequencySort(input)
    else:
        expectedOutput = line[1:-1].strip()
        prevFreq, currFreq = len(actualOutput), 1
        for i in range(1, len(actualOutput)):
            if actualOutput[i] != actualOutput[i - 1]:
                prevFreq = currFreq
                currFreq = 1
            else:
                currFreq += 1
                if currFreq > prevFreq:
                    isSolutionWrong = True
                    break
        if (isSolutionWrong):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
