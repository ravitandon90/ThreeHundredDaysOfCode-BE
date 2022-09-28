from Solution import MyQueue
from proddata.data.implement_queue_using_stacks.Python.Solution import MyQueue
from proddata.python.helpers import createInputForDesignProblems, displayErrorMessage
import sys
import os

# Test case:
# 1
# ["MyQueue", "push", "push", "peek", "pop", "empty"], [[], [1], [2], [], [], []]
# [null, null, null, 1, 1, false]

file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
queue = None
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
            if instruction == "MyQueue":
                queue = MyQueue()
                actualOutput.append("null")
            elif instruction == "push":
                output = queue.push(int(param[1:-1].strip()))
                if output is not None:
                    isSolutionWrong = True
                    actualOutput.append(str(output))
                else:
                    actualOutput.append("null")
            elif instruction == "pop" or instruction == "peek":
                if instruction == "pop":
                    output = queue.pop()
                else:
                    output = queue.peek()
                if output != int(expectedOutput[pos]):
                    isSolutionWrong = True
                if output is None:
                    actualOutput.append("null")
                else:
                    actualOutput.append(output)
            elif instruction == "empty":
                output = queue.empty()
                expected = True if expectedOutput[pos] == "true" else False
                if output != expected:
                    isSolutionWrong = True
                if output is None:
                    actualOutput.append("null")
                else:
                    actualOutput.append(output)
            pos += 1

        if isSolutionWrong:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            break

    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
