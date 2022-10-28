package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [-10,-3,0,5,9]
// [0,-3,9,-10,null,5]
// [1,3]
// [3,1]

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

func createIntArrayFromInput(input string) []int {
	array := []int{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(inputAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			value, _ := strconv.Atoi(strings.TrimSpace(stringValue))
			array = append(array, value)
		}
	}
	return array
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
			nums := createIntArrayFromInput(line)
			actualOutput = sortedArrayToBST(nums)
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
