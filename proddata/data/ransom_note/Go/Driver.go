package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// "a", "b"
// false
// "aa", "ab"
// false
// "aa", "aab"
// true

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
			input := strings.Split(line, ",")
			a := strings.TrimRight(input[0], " ")
			a = strings.TrimSpace(a[1 : len(a)-1])
			b := strings.TrimLeft(input[1], " ")
			b = strings.TrimSpace(b[1 : len(b)-1])
			actualOutput = canConstruct(a, b)
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
