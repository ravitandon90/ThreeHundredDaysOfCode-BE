package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 00000010100101000001111010011100
// 964176192
// 11111111111111111111111111111101
// 3221225471

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
	var actualOutput uint32
	var testCase string

	for lineNumber, line := range lines {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			input, _ := strconv.ParseUint(line, 2, 32)
			actualOutput = reverseBits(uint32(input))
		} else {
			expectedOutput, _ := strconv.ParseUint(line, 10, 32)
			if actualOutput != uint32(expectedOutput) {
				displayErrorMessage(testCase,
					strconv.FormatUint(expectedOutput, 10), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
