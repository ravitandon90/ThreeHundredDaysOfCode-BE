#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>

#include "Solution.hpp"

using namespace std;

ListNode *add(int data)
{
    ListNode *newnode = new ListNode;
    newnode->val = data;
    newnode->next = NULL;
    return newnode;
}

void Print(ListNode *actual_output, ListNode *expected_output)
{
    ListNode *curr = actual_output;
    cout << "Actual Output: ";
    while (curr != NULL)
    {
        cout << curr->val << "->";
        curr = curr->next;
    }
    cout << endl;

    cout << "Expected Output: ";
    curr = expected_output;
    while (curr != NULL)
    {
        cout << curr->val << "->";
        curr = curr->next;
    }
    cout << endl;
}

int main()
{
    ifstream infile("testcases.txt");
    ListNode *curr;
    ListNode *head1;
    ListNode *head2;
    ListNode *expected_output;
    ListNode *actual_output;
    string line;
    int c = 0;
    int lineNo = 1;
    while (getline(infile, line))
    {
        if (lineNo == 1)
        {
            stringstream ss(line);
            int v;
            while (ss >> v)
            {
                if (c == 0)
                {
                    head1 = add(v);
                    curr = head1;
                    c += 1;
                }
                else
                {
                    curr->next = add(v);
                    curr = curr->next;
                }
            }
            c = 0;
        }
        if (lineNo == 2)
        {
            stringstream ss(line);
            int v;
            while (ss >> v)
            {
                if (c == 0)
                {
                    head2 = add(v);
                    curr = head2;
                    c += 1;
                }
                else
                {
                    curr->next = add(v);
                    curr = curr->next;
                }
            }
            c = 0;
        }
        if (lineNo == 3)
        {
            stringstream ss(line);
            int v;
            while (ss >> v)
            {
                if (c == 0)
                {
                    expected_output = add(v);
                    curr = expected_output;
                    c += 1;
                }
                else
                {
                    curr->next = add(v);
                    curr = curr->next;
                }
            }

            Solution *obj = new Solution;
            actual_output = obj->addTwoNumbers(head1, head2);
            ListNode *curr1 = actual_output;
            ListNode *curr2 = expected_output;
            while (curr1 != NULL && curr2 != NULL)
            {
                if (curr1->val != curr2->val)
                {
                    cout << "Result: Failed" << endl;
                    Print(actual_output, expected_output);
                    return 0;
                }
                curr1 = curr1->next;
                curr2 = curr2->next;
            }
            c = 0;
            lineNo = 0;
        }
        lineNo += 1;
    }
    cout << "Result: Success" << endl;
    return 0;
}
