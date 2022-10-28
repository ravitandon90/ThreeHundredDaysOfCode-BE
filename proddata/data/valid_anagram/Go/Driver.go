package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// "anagram", "nagaram"
// true
// "rat", "car"
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
			inputStrings := strings.Split(line, ",")
			s := strings.TrimRight(inputStrings[0], " ")
			s = strings.TrimSpace(s[1 : len(s)-1])
			t := strings.TrimLeft(inputStrings[1], " ")
			t = strings.TrimSpace(t[1 : len(t)-1])
			actualOutput = isAnagram(s, t)
		} else {
			expectedOutput := true
			if line == "false" {
				expectedOutput = false
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
