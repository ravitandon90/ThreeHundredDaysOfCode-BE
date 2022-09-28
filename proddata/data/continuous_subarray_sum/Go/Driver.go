package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// [23,2,4,6,7], 6
// true
// [23,2,6,4,7], 6
// true
// [23,2,6,4,7], 13
// false

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
	var actualOutput bool
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			ind := strings.Index(line, "]")
			arr := createIntArrayFromInput(line[:ind+1])
			for line[ind] != ',' {
				ind++
			}
			k, _ := strconv.Atoi(strings.TrimSpace(line[ind+1:]))
			actualOutput = checkSubarraySum(arr, k)
		} else {
			expectedOutput := false
			if line == "true" {
				expectedOutput = true
			}
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, strconv.FormatBool(actualOutput), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
