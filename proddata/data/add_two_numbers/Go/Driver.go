package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2 4 3
// 5 6 4
// 7 0 8
// 0
// 0
// 0
// 9 9 9 9 9 9 9
// 9 9 9 9
// 8 9 9 9 0 0 0 1

type ListNode struct {
	Val  int
	Next *ListNode
}

func createIntArray(input string) []int {
	array := []int{}
	if input != "" {
		arrayWithStringValues := strings.Fields(input)
		for _, stringValue := range arrayWithStringValues {
			value, _ := strconv.Atoi(strings.TrimSpace(stringValue))
			array = append(array, value)
		}
	}
	return array
}

func createLLFromArrayInput(arrayInput string) *ListNode {
	values := createIntArray(arrayInput)
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

func createStringFromLinkedList(ll *ListNode) string {
	if ll == nil {
		return ""
	}
	res := "["
	for ll != nil {
		res += strconv.Itoa(ll.Val)
		if ll.Next != nil {
			res += ","
		} else {
			res += "]"
		}
		ll = ll.Next
	}
	return res
}

func areLinkedListsIdentical(ll1 *ListNode, ll2 *ListNode) bool {
	for ll1 != nil && ll2 != nil {
		if ll1.Val != ll2.Val {
			return false
		}
		ll1 = ll1.Next
		ll2 = ll2.Next
	}
	return ll1 == nil && ll2 == nil
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
	var actualOutput, l1, l2 *ListNode

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%3 == 0 {
			l1 = createLLFromArrayInput(line)
		} else if lineNumber%3 == 1 {
			l2 = createLLFromArrayInput(line)
			actualOutput = addTwoNumbers(l1, l2)
		} else {
			expectedOutput := createLLFromArrayInput(line)
			if !areLinkedListsIdentical(actualOutput, expectedOutput) {
				isSolutionWrong = true
				testCase := createStringFromLinkedList(l1) + "," + createStringFromLinkedList(l2)
				displayErrorMessage(testCase, createStringFromLinkedList(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
