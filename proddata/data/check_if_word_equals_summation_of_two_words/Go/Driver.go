package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// "ab", "ba"
// true
// "ab", "ab"
// false
// "aa", "aa"
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
			firstWord := strings.TrimRight(input[0], " ")
			firstWord = strings.TrimSpace(firstWord[1 : len(firstWord)-1])
			secondWord := strings.TrimLeft(input[1], " ")
			secondWord = strings.TrimSpace(secondWord[1 : len(secondWord)-1])
			targetWord := strings.TrimLeft(input[2], " ")
			targetWord = strings.TrimSpace(targetWord[1 : len(targetWord)-1])
			actualOutput = isSumEqual(firstWord, secondWord, targetWord)
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
