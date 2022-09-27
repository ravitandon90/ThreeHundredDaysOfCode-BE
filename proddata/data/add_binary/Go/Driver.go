package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// 11 1
// 100
// 1010 1011
// 10101

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
	var actualOutput string
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			input := strings.Fields(line)
			actualOutput = addBinary(input[0], input[1])
		} else {
			expectedOutput := line
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, actualOutput, line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
