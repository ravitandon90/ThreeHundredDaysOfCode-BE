package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 1 15 7 9 2 5 10
// 3
// 84
// 1 4 1 5 7 3 6 1 9 9 3
// 4
// 83
// 1
// 1
// 1

func createIntArray(input string) []int {
	array := []int{}
	if input == "" {
		return array
	}
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
	var arr []int
	var k int
	var actualOutput int
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%3 == 0 {
			testCase = line
			arr = createIntArray(line)
		} else if lineNumber%3 == 1 {
			testCase += ", " + line
			k, _ = strconv.Atoi(line)
			actualOutput = maxSumAfterPartitioning(arr, k)
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
