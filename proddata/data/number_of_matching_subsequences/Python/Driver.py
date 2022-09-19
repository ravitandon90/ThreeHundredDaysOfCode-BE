from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
Count = 0
status = 0
obj = Solution()
for line in Lines:
    if line.strip() == 'true':
        line = 'True'
    if line.strip() == 'false':
        line = 'False'
    if (Count % 2 == 0):
        a = line.split()[0]
        b = list(map(str, line.split()[1:]))
        actual_output = obj.numMatchingSubseq(a, b)
    else:
        expected_output = int(line.strip())
        if (expected_output != actual_output):
            print('Result: Failed')
            print("Input: s= {}, words= {}".format(a, b))
            print("Expected Output:", expected_output)
            print("Actual Outuput:", actual_output)
            status = 1
            break
    Count += 1
if status == 0:
    print('Result: Success')
