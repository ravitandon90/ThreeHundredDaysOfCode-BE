package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"strconv"
	"strings"
)

// 2
// [[1,3],[-2,2]], 1
// [[-2,2]]
// [[3,3],[5,-1],[-2,4]], 2
// [[3,3],[-2,4]]

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

func areIntMatricesEqual(mat1 [][]int, mat2 [][]int) bool {
	if len(mat1) != len(mat2) {
		return false
	}
	for pos, array := range mat1 {
		if !areIntArraysEqual(array, mat2[pos]) {
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

func createStringFromMatrix(matrix [][]int) string {
	if len(matrix) == 0 {
		return "[]"
	}
	s := "["
	for ind, array := range matrix {
		s += createStringFromArray(array)
		if ind != len(matrix)-1 {
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
	var actualOutput [][]int
	var testCase string

	for lineNumber, line := range lines[1:] {
		line = strings.TrimSpace(line)
		if lineNumber%2 == 0 {
			testCase = line
			pos := len(line) - 1
			for line[pos] != ',' {
				pos--
			}
			k, _ := strconv.Atoi(strings.TrimLeft(line[pos+1:], " "))
			points := createIntMatrixFromString(strings.TrimRight(line[:pos], " "))
			actualOutput = kClosest(points, k)
		} else {
			expectedOutput := createIntMatrixFromString(line)
			if !areIntMatricesEqual(actualOutput, expectedOutput) {
				isSolutionWrong = true
				displayErrorMessage(testCase, createStringFromMatrix(actualOutput), line)
				break
			}
		}
	}
	if !isSolutionWrong {
		fmt.Println("Result: Success")
	}
}
