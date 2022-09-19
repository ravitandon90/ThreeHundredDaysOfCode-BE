from Solution import Solution
import sys
import os

file1 = open(os.path.join(sys.path[0], '../testcases.txt'), 'r')
Lines = file1.readlines()
input = []
input1 = []
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
        sr, sc, color = input.pop()
        input1.pop()
        actual_output = obj.floodFill(input, sr, sc, color)
        actual_output.sort()
        expected_output.sort()
        if (expected_output != actual_output):
            print('Result: Failed')
            print("Input: image= {}, sr= {}, sc= {}, color= {}".format(
                input1, sr, sc, color))
            print("Expected Output:", expected_output)
            print("Actual Outuput:", actual_output)
            status = 1
            break
        input.clear()
        input1.clear()
        expected_output.clear()
    if (status == 1 and line.strip() != "check"):
        new_vec = list(map(int, line.split()))
        input.append(new_vec)
        input1.append(new_vec.copy())

    if(status == 0 and line.strip() != "check"):
        new_vec = list(map(int, line.split()))
        expected_output.append(new_vec)


if status == 0:
    print('Result: Success')
