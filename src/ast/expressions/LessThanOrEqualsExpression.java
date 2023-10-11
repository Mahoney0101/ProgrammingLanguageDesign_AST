package ast.expressions;

import visitor.Visitor;

public class LessThanOrEqualsExpression extends AbstractBinaryExpression {

    public LessThanOrEqualsExpression(int line, int column, Expression leftOperand, Expression rightOperand) {
        super(line, column, leftOperand, "<=", rightOperand);
    }

    @Override
    public String toString() {
        return getOperand1() + " <= " + getOperand2();
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}
