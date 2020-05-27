package cmpt355.project.ast.node.statement;

import cmpt355.project.codegen.Label;

interface Loop {

    Label getBreakLabel();
    Label getContinueLabel();
}
