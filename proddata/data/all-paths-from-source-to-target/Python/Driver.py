from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
input = []
actual_output = []
expected_output = []
status = 0
obj = Solution()
for line in Lines:
    if (line.strip() == "input"):
        status = 1
        continue
    if (line.strip() == "output"):
        status = 0
        continue
    if (line.strip() == "check"):
        actual_output = obj.allPathsSourceTarget(input)
        actual_output.sort()
        expected_output.sort()
        if (expected_output != actual_output):
            print("Result: Failed")
            print('Actual Output:', actual_output)
            print()
            print('Expected Output:', expected_output)
            status = 1
            break
        input.clear()
        expected_output.clear()
    if (status == 1 and line.strip() != "check"):
        new_vec = list(map(int, line.split()))
        input.append(new_vec)

    if(status == 0 and line.strip() != "check"):
        new_vec = list(map(int, line.split()))
        expected_output.append(new_vec)


if status == 0:
    print('Result: Success')
