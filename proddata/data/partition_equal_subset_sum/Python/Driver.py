from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
lineNo = 0
status = 0
obj = Solution()
for line in Lines:
    if (lineNo % 2 == 0):
        input = list(map(int, line.split()))
        actual_output = obj.canPartition(input)
    else:
        expected_output = line.strip()
        if (expected_output != str(actual_output).lower()):
            print("Result: Failed")
            print('Actual Output:', str(actual_output).lower())
            print('Expected Output:', expected_output)
            status = 1
            break
    lineNo += 1
if status == 0:
    print('Result: Success')
