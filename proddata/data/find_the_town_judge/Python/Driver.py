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
        pos = 0
        while line[pos] != ',':
            pos += 1
        n = int(line[:pos].rstrip())
        trust = []
        arrayAfterRemovingBraces = line[pos + 1:].lstrip()[1:-1].strip()
        if arrayAfterRemovingBraces != "":
            splitArrays = arrayAfterRemovingBraces[:-1].split(']')
            for array in splitArrays:
                pos = 0
                while array[pos] != '[':
                    pos += 1
                trust.append(
                    list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
        actualOutput = solution.findJudge(n, trust)
    else:
        expectedOutput = int(line)
        if (actualOutput != expectedOutput):
            print("Result: Failed")
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
