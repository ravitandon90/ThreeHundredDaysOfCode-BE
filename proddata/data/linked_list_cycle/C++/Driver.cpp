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
    ListNode *head=new ListNode(0);
    string expected_output;
    string actual_output;
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
            ListNode *prev;
            stringstream ss(x);
            int v,c=0;
            while (ss >> v)
            {
                if(c==0){
                    head->val=v;
                    prev=head;
                }
                if (c>=1){
                    prev->next=new ListNode(v);
                    prev=prev->next;
                }
                c++;
            }
            if (c>=3){
                ListNode *second_last=head;   
                int n;    
                while (second_last->next->next!=NULL){
                    n =second_last->next->next->val;
                    second_last=second_last->next;
                }
                delete(second_last->next);
                second_last->next=NULL;
                int i=0;
                ListNode *y;
                y=head;  
                while (i<=n){
                    if (i==n){
                        second_last->next=y;
                    }
                    y=y->next;
                    i++;
                }
            }
        }
        else
        {
            bool res = obj->hasCycle(head);

            actual_output = convertInttoBool(res);

            expected_output = line;
            if (checkOuputString(expected_output, actual_output, input1))
            {
                return 0;
            }
        }
        status += 1;
    }
    Success();
    return 0;
}


