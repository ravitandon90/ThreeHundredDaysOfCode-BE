package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [1,2,2,1]
// true
// [1,2]
// false

type ListNode struct {
	Val  int
	Next *ListNode
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

func createLLFromArrayInput(arrayInput string) *ListNode {
	values := createIntArrayFromInput(arrayInput)
	if len(values) == 0 {
		return nil
	}
	return createLinkedList(values)
}

func createLinkedList(values []int) *ListNode {
	head := &ListNode{values[0], nil}
	prev := head
	for _, value := range values[1:] {
		prev.Next = &ListNode{value, nil}
		prev = prev.Next
	}
	return head
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
	var actualOutput bool
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			ll := createLLFromArrayInput(line)
			actualOutput = isPalindrome(ll)
		} else {
			expectedOutput := true
			if line == "false" {
				expectedOutput = false
			}
			if actualOutput != expectedOutput {
				isSolutionWrong = true
				displayErrorMessage(testCase, strconv.FormatBool(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
