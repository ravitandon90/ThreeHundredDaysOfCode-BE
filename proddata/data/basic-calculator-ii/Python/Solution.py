class Solution:
    def calculate(self, s: str) -> int:
        stack = []

        def append(operator, operand):
            if operator == "+":
                stack.append(operand)
            elif operator == "-":
                stack.append(-operand)
            elif operator == "*":
                stack.append(stack.pop() * operand)
            else:
                stack.append(int(stack.pop() / operand))
        operator, operand = "+", 0

        for c in s:
            if c in "0123456789":
                operand = operand * 10 + int(c)
            elif c in "+-*/":
                append(operator, operand)
                operator, operand = c, 0
        append(operator, operand)
        return sum(stack)
