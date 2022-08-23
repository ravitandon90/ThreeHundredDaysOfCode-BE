#include <iostream>
using namespace std;

class Obj {
public:
int x;
};

int main() {

  int first_number, second_number, sum;
  cin >> first_number >> second_number;
  Obj* o = nullptr;
  cout << o->x;
  // sum of two numbers in stored in variable sumOfTwoNumbers
  sum = first_number + second_number;

  // prints sum
  cout << sum;

  return 0;
}