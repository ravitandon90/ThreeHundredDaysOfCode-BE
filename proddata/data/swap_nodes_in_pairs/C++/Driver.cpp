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

#include "Solution.hpp"
#include "../../cpp/helper.hpp"

using namespace std;


int main()
{
    string input1;
    ListNode *head = new ListNode();
    ListNode* expected_output= new ListNode();
    ListNode* actual_output= new ListNode();
    int flag=0;
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
            string x = RemoveAllPunctInArray(line);
            convertArrayToLinkedList(head,x);
        }
        else
        {
            actual_output = obj->swapPairs(head);
            string x = RemoveAllPunctInArray(line);
            convertArrayToLinkedList(expected_output,x);
            checkOuputLinkedList(expected_output, actual_output, input1, flag);
        }
        status += 1;
    }
    if (flag==0){
        Success();
    }
    return 0;
}


