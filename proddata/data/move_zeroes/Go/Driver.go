package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [0,1,0,3,12]
// [1,3,12,0,0]
// [0]
// [0]

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
	s := "["
	for _, ele := range array {
		s += strconv.Itoa(ele)
	}
	return s + "]"
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
	var actualOutput []int
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			ind := strings.Index(line, "]")
			nums := createIntArrayFromInput(line[:ind+1])
			for line[ind] != '[' {
				ind++
			}
			index := createIntArrayFromInput(line[ind:])
			actualOutput = moveZeroes(nums, index)
		} else {
			expectedOutput := createIntArrayFromInput(line)
			if !areIntArraysEqual(actualOutput, expectedOutput) {
				displayErrorMessage(testCase, createStringFromArray(actualOutput), line)
				isSolutionWrong = true
			}
		}
	}

	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
