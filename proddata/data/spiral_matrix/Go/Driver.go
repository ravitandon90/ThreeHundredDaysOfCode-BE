package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [[1,2,3],[4,5,6],[7,8,9]]
// [1,2,3,6,9,8,7,4,5]
// [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// [1,2,3,4,8,12,11,10,9,5,6,7]

func createIntMatrixFromString(s string) [][]int {
	stringWithoutOuterBraces := strings.TrimSpace(s[1 : len(s)-1])
	mat := [][]int{}
	if stringWithoutOuterBraces == "" {
		return mat
	}
	arrays := strings.Split(stringWithoutOuterBraces[:len(stringWithoutOuterBraces)-1], "]")
	for _, arr := range arrays {
		for i, ch := range arr {
			if byte(ch) == '[' {
				row := []int{}
				if strings.TrimSpace(arr[i+1:]) != "" {
					stringNums := strings.Split(arr[i+1:], ",")
					for _, stringNum := range stringNums {
						num, _ := strconv.Atoi(strings.TrimSpace(stringNum))
						row = append(row, num)
					}
				}
				mat = append(mat, row)
				break
			}
		}
	}
	return mat
}

func createIntArrayFromString(input string) []int {
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

func areIntArraysEqual(arr1 []int, arr2 []int) bool {
	if len(arr1) != len(arr2) {
		return false
	}
	for ind, ele := range arr1 {
		if ele != arr2[ind] {
			return false
		}
	}
	return true
}

func createStringFromArray(array []int) string {
	if len(array) == 0 {
		return "[]"
	}
	s := "["
	for ind, ele := range array {
		s += strconv.Itoa(ele)
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
	var actualOutput []int
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			matrix := createIntMatrixFromString(line)
			actualOutput = spiralOrder(matrix)
		} else {
			expectedOutput := createIntArrayFromString(line)
			if !areIntArraysEqual(actualOutput, expectedOutput) {
				displayErrorMessage(testCase, createStringFromArray(actualOutput), line)
				isSolutionWrong = true
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
