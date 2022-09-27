package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// "abc", [3,5,9]
// "rpl"
// "aaa", [1,2,3]
// "gfd"

func createIntArrayFromInput(input string) []int {
	array := []int{}
	inputAfterRemovingBraces := strings.TrimSpace(input[1 : len(input)-1])
	if inputAfterRemovingBraces != "" {
		arrayWithStringValues := strings.Split(inputAfterRemovingBraces, ",")
		for _, stringValue := range arrayWithStringValues {
			value, _ := strconv.Atoi(strings.TrimSpace(stringValue))
			array = append(array, value)
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
	var actualOutput string
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			ind := strings.Index(line, ",")
			stringAfterTrim := strings.TrimRight(line[:ind], " ")
			s := strings.TrimSpace(stringAfterTrim[1 : len(stringAfterTrim)-1])
			shifts := createIntArrayFromInput(strings.TrimLeft(line[ind+1:], " "))
			actualOutput = shiftingLetters(s, shifts)
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
