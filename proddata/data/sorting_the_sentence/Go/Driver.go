package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// 2
// "is2 sentence4 This1 a3"
// "This is a sentence"
// "Myself2 Me1 I4 and3"
// "Me Myself and I"

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

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			s := strings.TrimSpace(line[1 : len(line)-1])
			actualOutput = sortSentence(s)
		} else {
			expectedOutput := strings.TrimSpace(line[1 : len(line)-1])
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, actualOutput, expectedOutput)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
