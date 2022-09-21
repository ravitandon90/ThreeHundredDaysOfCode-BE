from Solution import Solution
from proddata.python.helpers import createTreeFromArrayInput, createArrayFromTreeInput, getSubtreeFromRootValue, displayErrorMessage
import os
import sys


file = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
lines = file.readlines()

isSolutionWrong = False
lineNumber = 1
solution = Solution()
for line in lines[1:]:
    line = line.strip()
    if lineNumber % 2 == 1:
        testCase = line
        ind = line.find(']')
        tree = createTreeFromArrayInput(line[: ind + 1])
        while line[ind] != ',':
            ind += 1
        rootOfSubtree1, rootOfSubtree2 = list(
            map(int, [s.strip() for s in line[ind + 1:].split(',')]))
        subtree1 = getSubtreeFromRootValue(tree, rootOfSubtree1)
        subtree2 = getSubtreeFromRootValue(tree, rootOfSubtree2)
        actualOutput = solution.lowestCommonAncestor(
            tree, subtree1, subtree2)
    else:
        rootOfLCA = int(line)
        expectedOutput = createArrayFromTreeInput(
            getSubtreeFromRootValue(tree, rootOfLCA))
        actualOutput = createArrayFromTreeInput(actualOutput)
        if actualOutput != expectedOutput:
            displayErrorMessage(testCase, actualOutput, expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
