package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [3,1,4,null,2], 1
// 1
// [5,3,6,2,4,null,null,1], 3
// 3

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func createTreeFromArrayInput(input string) *TreeNode {
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
	var actualOutput int
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			pos := strings.Index(line, "]")
			root := createTreeFromArrayInput(line[:pos+1])
			for line[pos] != ',' {
				pos++
			}
			k, _ := strconv.Atoi(strings.TrimLeft(line[pos+1:], " "))
			actualOutput = kthSmallest(root, k)
		} else {
			expectedOutput, _ := strconv.Atoi(line)
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, strconv.Itoa(actualOutput), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
