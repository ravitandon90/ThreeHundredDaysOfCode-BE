struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution
{
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)
    {
        ListNode *zeroth_node = new ListNode(0);
        ListNode *last_node = zeroth_node;
        bool carry_over = false;

        while (l1 || l2)
        {
            int digit_sum = (int)carry_over;
            carry_over = false;

            if (l1)
            {
                digit_sum += l1->val;
                l1 = l1->next;
            }

            if (l2)
            {
                digit_sum += l2->val;
                l2 = l2->next;
            }

            if (digit_sum > 9)
            {
                carry_over = true;
                digit_sum -= 10;
            }

            ListNode *new_node = new ListNode(digit_sum);

            last_node->next = new_node;
            last_node = new_node;
        }

        if (carry_over)
            last_node->next = new ListNode(1);

        return zeroth_node->next;
    }
};