package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// leetcode leet code
// true
// applepenapple apple pen
// true
// catsandog cats dog sand and cat
// false

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
			input := strings.Fields(line)
			s := input[0]
			wordDict := input[1:]
			actualOutput = wordBreak(s, wordDict)
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
