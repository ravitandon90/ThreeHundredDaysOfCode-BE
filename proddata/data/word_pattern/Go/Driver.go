package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// "abba", "dog cat cat dog"
// true
// "abba", "dog cat cat fish"
// false
// "aaaa", "dog cat cat dog"
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
			input := strings.Split(line, ",")
			pattern := strings.TrimRight(input[0], " ")
			pattern = strings.TrimSpace(pattern[1 : len(pattern)-1])
			s := strings.TrimLeft(input[1], " ")
			s = strings.TrimSpace(s[1 : len(s)-1])
			actualOutput = wordPattern(pattern, s)
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
