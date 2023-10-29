package ast;

import visitor.Visitor;
import java.util.List;

public class Program extends AbstractASTNode {

    private List<Definition> definitions;

    public Program(int line, int column,
                   List<Definition> definitions) {
        super(line, column);
        this.definitions = definitions;
    }

    public List<Definition> getdefinitions() {
        return this.definitions;
    }


    @Override
    public String toString() {
        return String.format("Program with %d definitions.",
                this.definitions.size());
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP param)
    {
        System.out.println("program accept");
        return visitor.visit(this, param);
    }
}
