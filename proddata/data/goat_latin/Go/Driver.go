package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// 2
// "I speak Goat Latin"
// "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
// "The quick brown fox jumped over the lazy dog"
// "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"

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
			input := strings.TrimSpace(line[1 : len(line)-1])
			actualOutput = toGoatLatin(input)
		} else {
			expectedOutput := strings.TrimSpace(line[1 : len(line)-1])
			if actualOutput != expectedOutput {
				displayErrorMessage(testCase, actualOutput, line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
