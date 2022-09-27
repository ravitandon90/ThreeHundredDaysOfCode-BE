package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strings"
)

// 3
// "tree"
// "eert"
// "cccaaa"
// "aaaccc"
// "Aabb"
// "bbAa"

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
			actualOutput = frequencySort(input)
		} else {
			expectedOutput := strings.TrimSpace(line[1 : len(line)-1])
			visited := make(map[rune]bool)
			prevChar := rune(actualOutput[0])
			prevCharFreq := len(actualOutput)
			currFreq := 1
			for _, c := range actualOutput[1:] {
				if c == prevChar {
					currFreq++
					if currFreq > prevCharFreq {
						isSolutionWrong = true
						break
					}
				} else {
					if visited[c] {
						isSolutionWrong = true
						fmt.Println(currFreq)
						break
					}
					visited[c] = true
					prevCharFreq = currFreq
					prevChar = c
					currFreq = 1
				}
			}
			if isSolutionWrong {
				displayErrorMessage(testCase, actualOutput, expectedOutput)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
