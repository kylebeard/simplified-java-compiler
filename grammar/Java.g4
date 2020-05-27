
grammar Java;

// The characters that are valid in Java identifiers are a very long list, so keep them in a separate file
import JavaIdentifierCharacters;

/* ---- Parser rules ---- */
// -- Basic syntax elements --
qualifiedIdentifier
    : components+=IDENTIFIER ('.' components+=IDENTIFIER)*
    ;

accessModifier
    : 'public' | 'private' | 'protected'
    ;

// Non-array types
scalarType
    : ('byte' | 'short' | 'int' | 'long'
        | 'float' | 'double'
        | 'char' | 'boolean')           #primitiveType
    | qualifiedIdentifier               #namedType
    ;

dataType
    : scalarType                        #scalarDataType
    | scalarType ('[' ']')+             #arrayType
    ;

returnType
    : dataType
    | 'void'
    ;

// Definition of a variable: a name, possibly with an initialization
varNameInitialization
    : id=IDENTIFIER init=initialization?
    ;

// Declaration of one or more variables, each of which may or may not be initialized
multVarDeclaration
    : type=dataType nameInits+=varNameInitialization (',' nameInits+=varNameInitialization)*
    ;

// A single declaration with no initialization
varDeclarationNoInit
    : type=dataType id=IDENTIFIER
    ;

initialization
    : '=' rhs=expression
    | '=' arrayInitializer
    ;

// Something that can be assigned to
lvalue
    : id=qualifiedIdentifier                                                #nameLvalue
    | lvalue '[' index=expression ']'                                       #subscriptLvalue
    | '(' lvalue ')'                                                        #parenthesizedLValue
    | lvalue '.' fieldName=IDENTIFIER                                       #fieldRefLValue
    | 'this' '.' fieldName=IDENTIFIER                                       #thisFieldLValue
    ;

// -- Top-level declarations --
javaFile
    : packageStatement? importStatement* classDefinition* EOF
    ;

packageStatement
    : 'package' id=qualifiedIdentifier ';';

importStatement
    : 'import' 'static'? id=qualifiedIdentifier ';'                                                     #importClass
    | 'import' 'static'? id=qualifiedIdentifier '.' '*' ';'                                             #importWildcard
    ;

// Note: "class" in non-terminal names really means "class or interface" in this grammar.
// While there are differences between what classes and interfaces can contain, I handle this in the semantic analyzer.
classDefinition
    : (mods+=classModifier)*
        type=('class' | 'interface')
        id=IDENTIFIER
        ('extends' extending+=qualifiedIdentifier (',' extending+=qualifiedIdentifier)*)?
        ('implements' implementing+=qualifiedIdentifier (',' implementing+=qualifiedIdentifier)*)?
        body=classBody
    ;

classModifier
    : accessModifier
    | 'abstract' | 'final' | 'strictfp'
    ;

// -- Class/interface structure --
classBody
    : '{' (members+=classMember | initializers+=classInitializer)* '}'
    ;

classMember
    : field                       #classField
    | method                      #classMethod
    | methodStub                  #classMethodStub
    ;

classInitializer
    : flag='static'? block
    ;

field
    : (modifiers+=fieldModifier)*
        multVarDeclaration
        ';'
    ;

fieldModifier
    : accessModifier
    | 'final' | 'static' | 'transient' | 'volatile'
    ;

// The "stem" of a method (everything up to but not including the body)
methodHeader
    : (modifiers+=methodModifier)*
        type=returnType?
        id=IDENTIFIER
        '(' (params+=methodParameter (',' params+=methodParameter)*)? ')'
        ('throws' throwing+=qualifiedIdentifier (',' throwing+=qualifiedIdentifier)*)?
    ;

methodModifier
    : accessModifier
    | 'abstract' | 'default' | 'final' | 'native' | 'static' | 'stricftp' | 'synchronized'
    ;

methodParameter
    : (mods+=parameterModifier)* type=dataType id=IDENTIFIER
    ;

parameterModifier
    : 'final'
    ;

method
    :  header=methodHeader body=block
    ;

methodStub
    : header=methodHeader ';'
    ;

// -- Statement-level constructs --
statement
    : block                                                                                         #blockStatement
    | ';'                                                                                           #emptyStatement
    | leafStatement ';'                                                                             #leaf
    | 'return' expression? ';'                                                                      #returnStatement
    | 'if' '(' cond=expression ')' trueBlock=statement                                              #ifStatement
    | 'if' '(' cond=expression ')' trueBlock=statement 'else' falseBlock=statement                  #ifElseStatement
    | 'while' '(' cond=expression ')' body=statement                                                #whileLoop
    | 'do' body=statement 'while' '(' cond=expression ')' ';'                                       #doWhileLoop
    | 'for' '('
            (init+=leafStatement (',' init+=leafStatement)*)? ';'
            cond=expression? ';'
            (update+=leafStatement (',' update+=leafStatement)*)? ')'
        body=statement                                                                              #forLoop
    | 'for' '(' localVarDeclarationNoInit ':' expression ')' statement                              #enhancedForLoop
    | 'try' body=block catches+=catchBlock* ('finally' finallyBlock=block)?                         #tryStatement
    | 'synchronized' body=block                                                                     #synchronizedBlock
    | 'synchronized' '(' monitor=expression ')' body=block                                          #synchronizedMonitorBlock
    | 'break' ';'                                                                                   #breakStatement
    | 'continue' ';'                                                                                #continueStatement
    | 'switch' '(' expression ')' switchBody                                                        #switchStatement
    | 'assert' expression (':' expression)? ';'                                                     #assertStatement
    ;

block
    : '{' (statements+=statement)* '}'
    ;

// Roughly, a leaf statement is one that cannot contain other statements.
// (In actuality, this non-terminal exists because of for loops: some kinds of statements can appear in a for loop
// initialization/update, for example variable declarations, increments, and method calls, and some cannot. This non-
// terminal is the kinds of statements that *can* be.)
// Note that none of the leafStatement productions ends in a ';' - this is because their purpose is to be included in
// a for loop header, where they may or may not be followed by a ';'.
leafStatement
    : (modifiers+=localVariableModifier)* multVarDeclaration                                        #declarationStatement
    | combinedAssignment                                                                            #combinedAssignmentStatement
    | preIncrement                                                                                  #preIncrementStatement
    | postIncrement                                                                                 #postIncrementStatement
    | superMethodCall                                                                               #superMethodCallStatement
    | thisMethodCall                                                                                #thisMethodCallStatement
    | methodCall                                                                                    #methodCallStatement
    | ('this' | 'super') '(' (args+=expression (',' args+=expression)*)? ')'                                   #superConstructorCallStatement
    | instantiation                                                                                 #instantiationStatement
    | 'throw' expr=expression                                                                       #throwStatement
    ;

localVariableModifier
    : 'final'
    ;

switchBody
    : '{' (statement | caseLabel | defaultLabel)* '}'
    ;

caseLabel
    : 'case' expression ':'
    ;

defaultLabel
    : 'default' ':'
    ;

localVarDeclarationNoInit
    : (modifiers+=localVariableModifier)* varDeclarationNoInit
    ;

catchBlock
    : 'catch' '(' type=qualifiedIdentifier id=IDENTIFIER ')' block
    ;

// -- Expression-level constructs --
expression
    : INT_LITERAL                                                                   #intLiteral
    | LONG_LITERAL                                                                  #longLiteral
    | FLOAT_LITERAL                                                                 #floatLiteral
    | DOUBLE_LITERAL                                                                #doubleLiteral
    | BOOLEAN_LITERAL                                                               #booleanLiteral
    | CHAR_LITERAL                                                                  #charLiteral
    | STRING_LITERAL                                                                #stringLiteral
    | NULL_LITERAL                                                                  #null
    | thisMethodCall                                                                #thisMethodCallExpression
    | superMethodCall                                                               #superMethodCallExpression
    | qualifiedIdentifier '.' 'class'                                               #classLiteral
    | qualifiedIdentifier                                                           #nameExpression
    | expression '.' fieldName=IDENTIFIER                                           #fieldReference
    | expression '.' name=IDENTIFIER arguments                                      #methodCallExpression
    | array=expression '[' index=expression ']'                                     #arraySubscript
    | postIncrement                                                                 #postIncrementExpression
    | preIncrement                                                                  #preIncrementExpression
    | op=('+' | '-' | '~' | '!')                   expr=expression                  #unaryOperator
    | left=expression op=('*' | '/' | '%')         right=expression                 #binaryOperator
    | left=expression op=('+' | '-')               right=expression                 #binaryOperator
    | left=expression op=('<<' | '>>' | '>>>')     right=expression                 #binaryOperator
    | left=expression op=('<' | '>' | '<=' | '>=') right=expression                 #binaryOperator
    | expr=expression 'instanceof' type=dataType                                    #instanceof
    | left=expression op=('==' | '!=')             right=expression                 #binaryOperator
    | left=expression op='&'                       right=expression                 #binaryOperator
    | left=expression op='^'                       right=expression                 #binaryOperator
    | left=expression op='|'                       right=expression                 #binaryOperator
    | left=expression op='&&'                      right=expression                 #binaryOperator
    | left=expression op='||'                      right=expression                 #binaryOperator
    |<assoc=right> cond=expression '?' trueExpr=expression ':' falseExpr=expression #conditionalExpression
    | combinedAssignment                                                            #combinedAssignmentExpression
    | instantiation                                                                 #instantiationExpression
    | 'new' type=scalarType ('[' dims+=expression ']')+ (emptyDims+='[' ']')*       #arrayInstantiationNoInitializer
    | 'new' type=scalarType (emptyDims+='[' ']')+ init=arrayInitializer             #arrayInstantiationWithInitializer
    | '(' dataType ')' expression                                                   #cast
    | 'this'                                                                        #thisReference
    | '(' expression ')'                                                            #parenthesizedExpression
    ;

arguments
    : '(' (args+= expression (',' args+=expression)*)? ')'
    ;

instantiation
    : 'new' type=qualifiedIdentifier arguments
    ;

postIncrement
    : lvalue op=('++' | '--')
    ;

preIncrement
    : op=('++' | '--') lvalue
    ;

superMethodCall
    : 'super' '.' name=IDENTIFIER arguments
    ;

thisMethodCall
    : (thisRef='this' '.')? name=IDENTIFIER arguments
    ;

methodCall
    : expression '.' name=IDENTIFIER arguments
    ;

combinedAssignment
    : lvalue op=('=' | '+=' | '-=' | '*=' | '/=' | '%='
                | '&=' | '|=' | '^=' | '<<=' | '>>=' | '>>>=') rhs=expression
    ;

arrayInitializer
    : '{' (items+=arrayInitializerItem (',' items+=arrayInitializerItem)*)? '}'
    ;

arrayInitializerItem
    : expression
    | arrayInitializer
    ;

/* ---- Lexer rules ---- */
KEYWORD
    // keywords
    : 'abstract' | 'assert'       | 'break'      | 'case'   | 'catch'      | 'class'     | 'const'    | 'continue'
    | 'default'  | 'do'           | 'else'       | 'enum'   | 'extends'    | 'final'     | 'finally'  | 'for'
    | 'goto'     | 'if'           | 'implements' | 'import' | 'instanceof' | 'interface' | 'native'   | 'new'
    | 'package'  | 'private'      | 'protected'  | 'public' | 'return'     | 'static'    | 'strictfp' | 'super'
    | 'switch'   | 'synchronized' | 'this'       | 'throw'  | 'throws'     | 'transient' | 'try'      | 'void'
    | 'volatile' | 'while'        | '_'
    // primitive types
    | 'byte'     | 'short'        | 'int'        | 'long'   | 'float'      | 'double'    | 'char'     | 'boolean'
    ;

BOOLEAN_LITERAL
    : 'true' | 'false'
    ;

NULL_LITERAL
    : 'null'
    ;

// The IDENTIFIER_PART/_START fragments are defined in JavaIdentifierCharacters.g4 due to length
IDENTIFIER
    : IDENTIFIER_START IDENTIFIER_PART*
    ;

// Numbers
/* Note that all of the numeric rules are complicated by the fact that Java allows underscores to be used arbitrarily
 * inside any numeric literal (except that there can't be an underscore before the first digit or after the last).
 * So we get lots of complicated rules like
 *     [0-9] ([0-9_]* [0-9])?
 * ...which boils down to "digits 0-9 and underscores, beginning and ending with a digit." The ? is necessary so that
 * a single digit is still accepted.
 */
fragment INTEGER
    // zero
    : '0'
    // decimal
    | [1-9] ([0-9_]* [0-9])?
    // hexadecimal
    | '0' [Xx] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])?
    // octal
    | '0' [0-7_]* [0-7]
    // binary
    | '0' [Bb] [01] ([01_]* [01])?
    ;

// All floating-point literals have an optional exponent on the end
fragment EXPONENT
    : [Ee] [+-]? [0-9] ([0-9_]* [0-9])?
    ;

fragment REAL
    // real number with at least one digit before the .
    : [0-9] ([0-9_]* [0-9])? '.' ([0-9] ([0-9_]* [0-9])?)? EXPONENT?
    // real number with at least one digit after the .
    | ([0-9] ([0-9_]* [0-9])?)? '.' [0-9] ([0-9_]* [0-9])? EXPONENT?
    // real number that has at least an exponent
    | ([0-9] ([0-9_]* [0-9])?)? EXPONENT
    ;

INT_LITERAL
    : INTEGER
    ;

LONG_LITERAL
    : INTEGER [Ll]
    ;

DOUBLE_LITERAL
    : REAL
    ;

FLOAT_LITERAL
    : (REAL | INTEGER) [Ff]
    ;

// Characters and strings
/* Note that this is *not* handling Unicode escape sequences correctly: they are supposed to be applied before the rest
 * of the lexer, so that e.g. '\u000a' should not be allowed because U+000A is the newline character (\n) which is not
 * allowed inside a character literal. Here, however, we treat them on the same level as all other escape sequences.
 */
fragment ESCAPE_SEQUENCE
    : '\\b' | '\\t' | '\\n' | '\\f' | '\\r' | '\\\'' | '\\"' | '\\\\'
    | '\\' [0-7]
    | '\\' [0-7] [0-7]
    | '\\' [0-3] [0-7] [0-7]
    | '\\u' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
    ;

CHAR_LITERAL
    : '\'' ~['\\\r\t\n] '\''
    | '\'' ESCAPE_SEQUENCE '\''
    ;

STRING_LITERAL
    : '"' (~["\\\r\n] | ESCAPE_SEQUENCE)* '"'
    ;

// Comments and whitespace (skipped)
fragment LINE_COMMENT
    : '//' (~[\n])* '\n'
    ;

// A part of a block comment. Anything that is not a star, or is a star followed by something other than a /.
fragment BLOCK_COMMENT_PART
    : ~[*] | '*' ~[/]
    ;

fragment BLOCK_COMMENT
    : '/*' BLOCK_COMMENT_PART* '*/'
    ;

fragment DOCUMENTATION_COMMENT
    : '/**' BLOCK_COMMENT_PART* '*/'
    ;

COMMENT
    : (LINE_COMMENT | BLOCK_COMMENT | DOCUMENTATION_COMMENT) -> skip
    ;

SEPARATOR
    : '(' | ')'
    | '{' | '}'
    | '[' | ']'
    | ';' | ',' | '.'
    | '@'
    | '...'
    ;

OPERATOR
    : '='  | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '>>>='
           | '+'  | '-'  | '*'  | '/'  | '%'  | '&'  | '|'  | '^'  | '<<'  | '>>'  | '>>>'
    | '==' | '!=' | '<'  | '>'  | '<=' | '>='
    | '&&' | '||'
    | '!'  | '~'  | '++' | '--'
    | '?'  | ':'
    | '->'
    ;

WHITESPACE
    : [ \t\n\r]+ -> skip
    ;
