from Solution import Solution, ListNode
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
        pos = 0
        while line[pos] != ']':
            pos += 1
        if line[1:pos].strip() == "":
            actualOutput = solution.hasCycle(None)
            continue
        values = list(map(int, [s.strip() for s in line[1:pos].split(',')]))
        head = ListNode(values[0])
        prev = head
        mapNodeToIndex = {0: head}
        for i in range(1, len(values)):
            prev.next = ListNode(values[i])
            prev = prev.next
            mapNodeToIndex[i] = prev
        pos = len(line) - 1
        while line[pos] != ',':
            pos -= 1
        indexOfCycle = int(line[pos + 1:].lstrip())
        if indexOfCycle != -1:
            prev.next = mapNodeToIndex[indexOfCycle]
        actualOutput = solution.hasCycle(head)
    else:
        expectedOutput = True if line == "true" else False
        if (actualOutput != expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
