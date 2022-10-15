package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 3
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "ABCCED"
// true
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "SEE"
// true
// [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "ABCB"
// false

func createBytesMatrixFromString(s string) [][]byte {
	stringWithoutOuterBraces := strings.TrimSpace(s[1 : len(s)-1])
	mat := [][]byte{}
	if stringWithoutOuterBraces == "" {
		return mat
	}
	arrays := strings.Split(stringWithoutOuterBraces[:len(stringWithoutOuterBraces)-1], "]")
	for _, arr := range arrays {
		for i, ch := range arr {
			if byte(ch) == '[' {
				row := []byte{}
				trimmedString := strings.TrimSpace(arr[i+1:])
				if trimmedString != "" {
					for _, character := range trimmedString {
						if byte(character) != ',' && byte(character) != '"' {
							row = append(row, byte(character))
						}
					}
				}
				mat = append(mat, row)
				break
			}
		}
	}
	return mat
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
	var actualOutput bool
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			pos := len(line) - 1
			for line[pos] != ',' {
				pos--
			}
			word := strings.TrimLeft(line[pos+1:], " ")
			word = strings.TrimSpace(word[1 : len(word)-1])
			board := createBytesMatrixFromString(strings.TrimRight(line[:pos], " "))
			actualOutput = exist(board, word)
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
