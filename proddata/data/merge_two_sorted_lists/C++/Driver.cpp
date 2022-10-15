#include <algorithm>
#include <fstream>
#include <iostream>
#include <stack>
#include <string>
#include <vector>
#include <sstream>
#include <iterator>
#include <unordered_map>
#include <unordered_set>
#include <regex>

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;

int main()
{
    string input1;
    vector<int> nums;
    vector<int> nums1;
    ListNode* list1;
    ListNode* list2;
    int flag=0;
    int s = 0;
    ListNode* expected_output;
    ListNode* actual_output;
    ifstream infile("../testcases.txt");
    Solution *obj = new Solution();
    string line;
    int status = 0;
    while (getline(infile, line))
    {
        if (status == 0)
        {
            status += 1;
            continue;
        }
        if (status % 2 != 0)
        {
            input1 = line;
            parseIntOfVectorAndIntOfVector(line, nums,nums1);
            convertIntArrayToLinkedList(list1,nums);
            convertIntArrayToLinkedList(list2,nums1);
        }
        else
        {
            actual_output = obj->mergeTwoLists(list1,list2);
            line= RemoveAllPunctInArray(line);
            convertArrayToLinkedList(expected_output,line);
            checkOuputLinkedList(expected_output,actual_output,input1,flag);   
            nums.clear();
            nums1.clear();
            break;
        }
        status += 1;
    }
    if (flag==0){
        Success();
    }
    return 0;
}



