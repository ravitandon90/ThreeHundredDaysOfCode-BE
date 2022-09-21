from Solution import MinStack
from proddata.python.helpers import createInputForDesignProblems, displayErrorMessage
import sys
import os

# Test case:
# 1
# ["MinStack","push","push","push","getMin","pop","top","getMin"], [[],[-2],[0],[-3],[],[],[],[]]
# [null,null,null,null,-3,null,0,-2]

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
minStack = None
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        input = createInputForDesignProblems(line)
    else:
        actualOutput = []
        expectedOutput = [s.strip() for s in line[1:-1].split(',')]
        pos = 0
        for instruction, param in input:
            if instruction == "MinStack":
                minStack = MinStack()
                actualOutput.append("null")
            elif instruction == "push" or instruction == "pop":
                if instruction == "push":
                    output = minStack.push(int(param[1:-1].strip()))
                else:
                    output = minStack.pop()
                if output is not None:
                    isSolutionWrong = True
                    actualOutput.append(str(output))
                else:
                    actualOutput.append("null")
            elif instruction == "getMin" or instruction == "top":
                if instruction == "getMin":
                    output = minStack.getMin()
                else:
                    output = minStack.top()
                if output != int(expectedOutput[pos]):
                    isSolutionWrong = True
                if output is None:
                    actualOutput.append("null")
                else:
                    actualOutput.append(output)
            pos += 1

        if isSolutionWrong:
            # The error message will show the user's output for each of the instructions
            # It will also show the corresponding expected (correct) values
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            break

    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
