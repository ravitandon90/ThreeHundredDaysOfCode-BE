package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// [1,2,4], [1,3,4]
// [1,1,2,3,4,4]
// [], []
// []
// [], [0]
// [0]

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
	var actualOutput *ListNode
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			pos := 0
			for line[pos] != ']' {
				pos++
			}
			l1 := createLLFromArrayInput(line[:pos+1])
			for line[pos] != '[' {
				pos++
			}
			l2 := createLLFromArrayInput(line[pos:])
			actualOutput = mergeTwoLists(l1, l2)
		} else {
			expectedOutput := createLLFromArrayInput(line)
			if !areLinkedListsIdentical(actualOutput, expectedOutput) {
				isSolutionWrong = true
				displayErrorMessage(testCase, createStringFromLinkedList(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
