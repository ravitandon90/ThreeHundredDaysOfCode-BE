package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// "aba", "cdc"
// 3
// "aaa", "bbb"
// 3
// "aaa", "aaa"
// -1

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

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			input := strings.Split(line, ",")
			a := strings.TrimRight(input[0], " ")
			a = strings.TrimSpace(a[1 : len(a)-1])
			b := strings.TrimLeft(input[1], " ")
			b = strings.TrimSpace(b[1 : len(b)-1])
			actualOutput = findLUSlength(a, b)
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
