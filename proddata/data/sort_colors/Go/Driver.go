package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [2,0,2,1,1,0]
// [0,0,1,1,2,2]
// [2,0,1]
// [0,1,2]

func createIntArrayFromString(input string) []int {
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

func areIntArraysEqual(arr1 []int, arr2 []int) bool {
	if len(arr1) != len(arr2) {
		return false
	}
	for ind, ele := range arr1 {
		if ele != arr2[ind] {
			return false
		}
	}
	return true
}

func createStringFromArray(array []int) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += strconv.Itoa(ele)
		if ind != len(array)-1 {
			s += ","
		} else {
			s += "]"
		}
	}
	return s
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
	var nums []int
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			nums = createIntArrayFromString(line)
			sortColors(nums)
		} else {
			expectedOutput := createIntArrayFromString(line)
			if !areIntArraysEqual(nums, expectedOutput) {
				displayErrorMessage(testCase, createStringFromArray(nums), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}