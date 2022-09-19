from Solution import Solution
import sys
import os


def createMatrixFromString(s):
    l = []
    if s[1:-1].strip() == "":
        return l
    splitArrays = s[1:-1].strip()[:-1].split(']')
    for array in splitArrays:
        pos = 0
        while array[pos] != '[':
            pos += 1
        l.append(
            list(map(int, [s.strip() for s in array[pos + 1:].strip().split(',')])))
    return l


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        balanceOfParentheses = pos = 1
        while balanceOfParentheses != 0:
            if line[pos] == '[':
                balanceOfParentheses += 1
            elif line[pos] == ']':
                balanceOfParentheses -= 1
            pos += 1
        l1 = createMatrixFromString(line[:pos])
        while line[pos] != '[':
            pos += 1
        l2 = createMatrixFromString(line[pos:])
        actualOutput = solution.intervalIntersection(l1, l2)
    else:
        expectedOutput = createMatrixFromString(line)
        actualOutput.sort()
        expectedOutput.sort()
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: ", testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
