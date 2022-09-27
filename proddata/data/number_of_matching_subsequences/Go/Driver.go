package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// abcde a bb acd ace
// 3
// dsahjpjauf ahjpjau ja ahbwzgqnuk tnmlanowax
// 2

func createStringArrayFromInput(input string) []string {
	array := []string{}
	if input != "" {
		array = strings.Fields(input)
		for ind, stringValue := range array {
			array[ind] = strings.TrimSpace(stringValue)
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
	var actualOutput int
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			input := createStringArrayFromInput(line)
			s := input[0]
			words := input[1:]
			actualOutput = numMatchingSubseq(s, words)
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
