package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [5,3,6,2,4,null,8,1,null,null,null,7,9]
// [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// [5,1,7]
// [1,null,5,null,7]

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func createStringArrayFromInput(input string) []string {
	array := []string{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(inputAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			array = append(array, strings.TrimSpace(stringValue))
		}
	}
	return array
}

func createTreeFromArray(input string) *TreeNode {
	inputWithNoParentheses := strings.TrimSpace(input[1 : len(input)-1])
	if inputWithNoParentheses == "" {
		return nil
	}
	stringNodes := strings.Split(inputWithNoParentheses, ",")
	for ind, stringNode := range stringNodes {
		stringNodes[ind] = strings.TrimSpace(stringNode)
	}
	return createTree(stringNodes)
}

func createTree(stringNodes []string) *TreeNode {
	rootVal, _ := strconv.Atoi(stringNodes[0])
	root := &TreeNode{rootVal, nil, nil}
	posInArray := 1
	var queue []*TreeNode
	queue = append(queue, root)
	for len(queue) > 0 {
		size := len(queue)
		for size > 0 {
			if posInArray == len(stringNodes) {
				queue = make([]*TreeNode, 0)
				break
			}
			currentNode := queue[0]
			var nodeVal int
			if stringNodes[posInArray] != "null" {
				nodeVal, _ = strconv.Atoi(stringNodes[posInArray])
				currentNode.Left = &TreeNode{nodeVal, nil, nil}
				queue = append(queue, currentNode.Left)
			}
			posInArray++
			if posInArray == len(stringNodes) {
				queue = make([]*TreeNode, 0)
				break
			}
			if stringNodes[posInArray] != "null" {
				nodeVal, _ = strconv.Atoi(stringNodes[posInArray])
				currentNode.Right = &TreeNode{nodeVal, nil, nil}
				queue = append(queue, currentNode.Right)
			}
			posInArray++
			size--
			queue[0] = nil
			queue = queue[1:]
		}
	}
	return root
}

func createArrayFromTree(root *TreeNode) []string {
	array := []string{}
	if root == nil {
		return array
	}
	array = append(array, strconv.Itoa(root.Val))
	var queue []*TreeNode
	queue = append(queue, root)
	for len(queue) > 0 {
		size := len(queue)
		for size > 0 {
			currentNode := queue[0]
			if currentNode.Left != nil {
				queue = append(queue, currentNode.Left)
				array = append(array, strconv.Itoa(currentNode.Left.Val))
			} else {
				array = append(array, "null")
			}
			if currentNode.Right != nil {
				queue = append(queue, currentNode.Right)
				array = append(array, strconv.Itoa(currentNode.Right.Val))
			} else {
				array = append(array, "null")
			}
			size--
			queue[0] = nil
			queue = queue[1:]
		}
	}
	for array[len(array)-1] == "null" {
		array = array[:len(array)-1]
	}
	return array
}

func createStringFromTree(root *TreeNode) string {
	if root == nil {
		return "[]"
	}
	return "[" + strings.Join(createArrayFromTree(root), ",") + "]"
}

func displayErrorMessage(testCase string, actualOutput string, expectedOutput string) {
	fmt.Println("Result: Failed for test case: ", testCase)
	fmt.Println("Actual Output: ", actualOutput)
	fmt.Println("Expected Output: ", expectedOutput)
}

func main() {
	bs, err := ioutil.ReadFile("../testcases.txt")
	if err != nil {
		os.Exit(1)
	}
	lines := strings.Split(string(bs), "\n")

	isSolutionWrong := false
	var actualOutput *TreeNode
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			tree := createTreeFromArray(line)
			actualOutput = increasingBST(tree)
		} else {
			expectedOutput := createStringArrayFromInput(line)
			actualOutputAsArray := createArrayFromTree(actualOutput)
			if len(expectedOutput) != len(actualOutputAsArray) {
				isSolutionWrong = true
			} else {
				for pos, value := range expectedOutput {
					if value != actualOutputAsArray[pos] {
						isSolutionWrong = true
						break
					}
				}
			}
			if isSolutionWrong {
				displayErrorMessage(testCase, createStringFromTree(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
