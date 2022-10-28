package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [10,5,15,3,7,null,18], 7, 15
// 32
// [10,5,15,3,7,13,18,1,null,6], 6, 10
// 23

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
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
			ind := strings.Index(line, "]")
			tree := createTreeFromArray(line[:ind+1])
			lowAndHigh := strings.TrimSpace(line[ind+1:])[1:]
			values := strings.Split(lowAndHigh, ",")
			low, _ := strconv.Atoi(strings.TrimSpace(values[0]))
			high, _ := strconv.Atoi(strings.TrimSpace(values[1]))
			actualOutput = rangeSumBST(tree, low, high)
		} else {
			expectedOutput, _ := strconv.Atoi(line)
			if actualOutput != expectedOutput {
				isSolutionWrong = true
				displayErrorMessage(testCase, strconv.Itoa(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
