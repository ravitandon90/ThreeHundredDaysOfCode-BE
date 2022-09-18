from Solution import Solution
import sys
import os

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
isInput, expectedOutput = True, []
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if isInput:
        pos = len(line) - 1
        while line[pos] != ',':
            pos -= 1
        maxWidth = int(line[pos + 1:].strip())
        words = [s.strip()[1:-1].strip() for s in line[:pos][1:-1].split(',')]
        actualOutput = solution.fullJustify(words, maxWidth)
        isInput = False
    else:
        if line == "[":
            continue
        if line == "]":
            isInput = True
            if (actualOutput != expectedOutput):
                print("Result: Failed")
                print("Actual Output: ", actualOutput)
                print("Expected Output: ", expectedOutput)
                isSolutionWrong = True
                break
            expectedOutput = []
        elif line[-1] == ',':
            expectedOutput.append(line[1:-1].rstrip()[:-1])
        else:
            expectedOutput.append(line[1:-1])

if not isSolutionWrong:
    print("Result: Success")
