from collections import deque


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def createTreeFromArrayInput(arrayInput):
    root = None
    inputWithNoParentheses = arrayInput[1:-1].strip()
    if inputWithNoParentheses == "":
        return root
    stringNodes = [stringFormOfNode.strip()
                   for stringFormOfNode in inputWithNoParentheses.split(',')]
    return createTree(stringNodes)


def createTree(stringNodes):
    root = TreeNode(int(stringNodes[0]))
    posInArray, q = 1, deque([root])
    while len(q) > 0:
        size = len(q)
        while size > 0:
            currentNode = q.popleft()
            if posInArray == len(stringNodes):
                q = []
                break
            if stringNodes[posInArray] != "null":
                currentNode.left = TreeNode(int(stringNodes[posInArray]))
                q.append(currentNode.left)
            posInArray += 1
            if posInArray == len(stringNodes):
                q = []
                break
            if stringNodes[posInArray] != "null":
                currentNode.right = TreeNode(int(stringNodes[posInArray]))
                q.append(currentNode.right)
            posInArray += 1
            size -= 1
    return root


def createArrayFromTreeInput(root):
    if not root:
        return []
    array, q = [str(root.val)], deque([root])
    while len(q) > 0:
        size = len(q)
        while size > 0:
            currentNode = q.popleft()
            if currentNode.left:
                q.append(currentNode.left)
                array.append(str(currentNode.left.val))
            else:
                array.append("null")
            if currentNode.right:
                q.append(currentNode.right)
                array.append(str(currentNode.right.val))
            else:
                array.append("null")
            size -= 1
    while array[-1] == "null":
        array.pop()
    return array


def getSubtreeFromRootValue(root, val):
    if not root:
        return None
    if val == root.val:
        return root
    if root.val > val:
        return getSubtreeFromRootValue(root.left, val)
    return getSubtreeFromRootValue(root.right, val)


def displayErrorMessage(testCase, actualOutput, expectedOutput):
    print("Result: Failed for test case: " + testCase)
    print("Actual Output: ", actualOutput)
    print("Expected Output: ", expectedOutput)
