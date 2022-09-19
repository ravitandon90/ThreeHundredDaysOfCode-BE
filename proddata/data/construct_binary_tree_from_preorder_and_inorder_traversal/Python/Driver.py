from Solution import Solution, TreeNode
from collections import deque
import sys
import os


def createTree(nodes):
    root = TreeNode(int(nodes[0]))
    pos, q = 1, deque([root])
    while len(q) > 0:
        size = len(q)
        while size > 0:
            node = q.popleft()
            if pos == len(nodes):
                q = []
                break
            if nodes[pos] != "null":
                node.left = TreeNode(int(nodes[pos]))
                q.append(node.left)
            pos += 1
            if pos == len(nodes):
                q = []
                break
            if nodes[pos] != "null":
                node.right = TreeNode(int(nodes[pos]))
                q.append(node.right)
            pos += 1
            size -= 1
    return root


def areTreesTheSame(root1, root2):
    if not root1 and not root2:
        return True
    if not root1 or not root2:
        return False
    return root1.val == root2.val and areTreesTheSame(root1.left, root2.left) and areTreesTheSame(root1.right, root2.right)


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
        preorder = list(map(int, [s.strip() for s in line[1:pos].split(',')]))
        while line[pos] != '[':
            pos += 1
        inorder = list(map(int, [s.strip()
                       for s in line[pos + 1: -1].split(',')]))
        actualOutput = solution.buildTree(preorder, inorder)
    else:
        nodes = [s.strip() for s in line[1:-1].split(',')]
        expectedOutput = createTree(nodes)
        if not areTreesTheSame(actualOutput, expectedOutput):
            print("Result: Failed for test case: " + testCase)
            print("Actual Output: ", actualOutput)
            print("Expected Output: ", expectedOutput)
            isSolutionWrong = True
            break
    lineNumber += 1

if not isSolutionWrong:
    print("Result: Success")
