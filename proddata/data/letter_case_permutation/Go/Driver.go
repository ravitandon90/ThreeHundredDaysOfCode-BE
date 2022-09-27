package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"sort"
	"strings"
)

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

func createStringFromStringArray(array []string) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += ele
		if ind != len(array)-1 {
			s += ","
		} else {
			s += "]"
		}
	}
	return s
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
	var actualOutput []string
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			actualOutput = letterCasePermutation(line)
		} else {
			expectedOutput := createStringArrayFromInput(line)
			sort.Strings(actualOutput)
			sort.Strings(expectedOutput)
			if len(actualOutput) != len(expectedOutput) {
				isSolutionWrong = true
			} else {
				for ind, s := range expectedOutput {
					if actualOutput[ind] != s {
						isSolutionWrong = true
						break
					}
				}
			}
			if isSolutionWrong {
				displayErrorMessage(testCase, createStringFromStringArray(actualOutput),
					createStringFromStringArray(expectedOutput))
				break
			}
		}
	}

	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
