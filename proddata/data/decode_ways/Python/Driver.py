from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
lineNo = 0
status = 0
obj = Solution()
for line in Lines:
    if lineNo == 0:
        lineNo += 1
        continue
    if (lineNo % 2 != 0):
        actual_output = obj.numDecodings(line.strip())
    else:
        expected_output = line.strip()
        if (expected_output != str(actual_output)):
            print('Result: Failed')
            print('Actual Output:', actual_output)
            print('Expected Output:', expected_output)
            status = 1
            break
    lineNo += 1
if status == 0:
    print('Result: Success')
