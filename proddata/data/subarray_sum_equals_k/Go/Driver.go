package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 1 1 1
// 2
// 2
// 1 2 3
// 3
// 2

func createIntArray(input string) []int {
	array := []int{}
	arrayWithStringValues := strings.Fields(input)
	for _, stringValue := range arrayWithStringValues {
		value, _ := strconv.Atoi(strings.TrimSpace(stringValue))
		array = append(array, value)
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
	var nums []int
	var actualOutput int
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%3 == 0 {
			testCase = line
			nums = createIntArray(line)
		} else if lineNumber%3 == 1 {
			k, _ := strconv.Atoi(line)
			actualOutput = subarraySum(nums, k)
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
