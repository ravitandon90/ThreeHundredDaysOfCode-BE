from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
nums = []
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
        res = obj.groupAnagrams(nums)
        for i in res:
            i.sort()
            actual_output.append(i)
        actual_output.sort()
        expected_output.sort()
        if (expected_output != actual_output):
            print("Result: Failed")
            print("Input: strs= {}".format(nums))
            print('Expected Output:', expected_output)
            print('Actual Output:', actual_output)
            status = 1
            break
        expected_output.clear()
        actual_output.clear()
    if (status == 1 and line.strip() != "check"):
        nums = list(map(str, line.split()))

    if(status == 0 and line.strip() != "check"):
        new_vec = list(map(str, line.split()))
        expected_output.append(new_vec)


if status == 0:
    print('Result: Success')
