package types;

import ast.ASTNode;
import ast.Parameter;
import visitor.Visitor;

import java.util.List;

public class FunctionType extends AbstractType{
    private Type returnType;
    private List<Parameter> parameters;

    public FunctionType(int line, int column, Type returnType, List<Parameter> parameters) {
        super(line, column);
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public String getParametersAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            sb.append(parameters.get(i));
            if (i < parameters.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public Type arithmetic(Type type, ASTNode node) {
        return new ErrorType("Arithmetic operations are not allowed on structs", node);
    }

    @Override
    public char suffix() {
        return returnType.suffix();
    }

    @Override
    public int numberOfBytes() {
        return returnType.numberOfBytes();
    }

    @Override
    public String toString() {
        return "FunctionType: " + returnType + "(" + getParametersAsString() + ")";
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}