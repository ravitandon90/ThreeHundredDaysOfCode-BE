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
        input = list(map(int, [s.strip() for s in line[1:-1].split(',')]))
        solution.removeDuplicates(input)
    else:
        pos = 0
        while line[pos] != ',':
            pos += 1
        length = int(line[:pos].rstrip())
        ind = pos + 1
        while line[pos] != '_' and line[pos] != ']':
            pos += 1
        arrayAsString = line[ind:pos].strip(
        )[1:] if line[pos] == ']' else line[ind:pos].strip()[1:-1]
        expectedOutput = list(map(int, [s.strip()
                              for s in arrayAsString.split(',')]))
        if length > len(input) or input[:length] != expectedOutput:
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", input)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
