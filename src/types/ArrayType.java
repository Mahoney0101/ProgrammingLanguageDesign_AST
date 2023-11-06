package types;

import ast.ASTNode;
import visitor.Visitor;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayType extends AbstractType {

    private Type baseType;
    private List<Integer> dimensions;

    public ArrayType(int line, int column, Type baseType, List<Integer> dimensions) {
        super(line, column);
        this.baseType = baseType;
        this.dimensions = dimensions;
    }

    public Type getBaseType() {
        return baseType;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    @Override
    public Type arithmetic(Type type, ASTNode node) {
        return super.arithmetic(type, node);
    }

    @Override
    public Type squareBrackets(Type t, ASTNode node) {
        if(t instanceof ErrorType){
            return t;
        }
        if(t instanceof IntType){
            return IntType.getInstance();
        }

        return new ErrorType(String.format("The type %s cannot be used as an index", t), node);
    }

    @Override
    public Type assignment(Type type, ASTNode node) {
        if (type instanceof ErrorType)
            return type;
        if (type instanceof ArrayType)
            return type;
        return new ErrorType(String.format("Cannot assign %s to array type", type), node);
    }

    @Override
    public char suffix() {
        return 'a'; // This is arbitrary; you can choose any other character for array.
    }

    @Override
    public int numberOfBytes() {
        int totalSize = baseType.numberOfBytes();
        for(int dim : dimensions) {
            totalSize *= dim;
        }
        return totalSize;
    }

    @Override
    public String toString() {
        String dimsString = dimensions.stream()
                .map(dim -> "[" + dim + "]")
                .collect(Collectors.joining());
        return baseType.toString() + dimsString;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param) {
        return visitor.visit(this, param);
    }
}

