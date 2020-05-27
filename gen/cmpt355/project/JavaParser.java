// Generated from /home/kathy/IdeaProjects/2020spring_cmpt355_ip5/grammar/Java.g4 by ANTLR 4.8
package cmpt355.project;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, T__93=94, 
		KEYWORD=95, BOOLEAN_LITERAL=96, NULL_LITERAL=97, IDENTIFIER=98, INT_LITERAL=99, 
		LONG_LITERAL=100, DOUBLE_LITERAL=101, FLOAT_LITERAL=102, CHAR_LITERAL=103, 
		STRING_LITERAL=104, COMMENT=105, SEPARATOR=106, OPERATOR=107, WHITESPACE=108;
	public static final int
		RULE_qualifiedIdentifier = 0, RULE_accessModifier = 1, RULE_scalarType = 2, 
		RULE_dataType = 3, RULE_returnType = 4, RULE_varNameInitialization = 5, 
		RULE_multVarDeclaration = 6, RULE_varDeclarationNoInit = 7, RULE_initialization = 8, 
		RULE_lvalue = 9, RULE_javaFile = 10, RULE_packageStatement = 11, RULE_importStatement = 12, 
		RULE_classDefinition = 13, RULE_classModifier = 14, RULE_classBody = 15, 
		RULE_classMember = 16, RULE_classInitializer = 17, RULE_field = 18, RULE_fieldModifier = 19, 
		RULE_methodHeader = 20, RULE_methodModifier = 21, RULE_methodParameter = 22, 
		RULE_parameterModifier = 23, RULE_method = 24, RULE_methodStub = 25, RULE_statement = 26, 
		RULE_block = 27, RULE_leafStatement = 28, RULE_localVariableModifier = 29, 
		RULE_switchBody = 30, RULE_caseLabel = 31, RULE_defaultLabel = 32, RULE_localVarDeclarationNoInit = 33, 
		RULE_catchBlock = 34, RULE_expression = 35, RULE_arguments = 36, RULE_instantiation = 37, 
		RULE_postIncrement = 38, RULE_preIncrement = 39, RULE_superMethodCall = 40, 
		RULE_thisMethodCall = 41, RULE_methodCall = 42, RULE_combinedAssignment = 43, 
		RULE_arrayInitializer = 44, RULE_arrayInitializerItem = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"qualifiedIdentifier", "accessModifier", "scalarType", "dataType", "returnType", 
			"varNameInitialization", "multVarDeclaration", "varDeclarationNoInit", 
			"initialization", "lvalue", "javaFile", "packageStatement", "importStatement", 
			"classDefinition", "classModifier", "classBody", "classMember", "classInitializer", 
			"field", "fieldModifier", "methodHeader", "methodModifier", "methodParameter", 
			"parameterModifier", "method", "methodStub", "statement", "block", "leafStatement", 
			"localVariableModifier", "switchBody", "caseLabel", "defaultLabel", "localVarDeclarationNoInit", 
			"catchBlock", "expression", "arguments", "instantiation", "postIncrement", 
			"preIncrement", "superMethodCall", "thisMethodCall", "methodCall", "combinedAssignment", 
			"arrayInitializer", "arrayInitializerItem"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'public'", "'private'", "'protected'", "'byte'", "'short'", 
			"'int'", "'long'", "'float'", "'double'", "'char'", "'boolean'", "'['", 
			"']'", "'void'", "','", "'='", "'('", "')'", "'this'", "'package'", "';'", 
			"'import'", "'static'", "'*'", "'class'", "'interface'", "'extends'", 
			"'implements'", "'abstract'", "'final'", "'strictfp'", "'{'", "'}'", 
			"'transient'", "'volatile'", "'throws'", "'default'", "'native'", "'stricftp'", 
			"'synchronized'", "'return'", "'if'", "'else'", "'while'", "'do'", "'for'", 
			"':'", "'try'", "'finally'", "'break'", "'continue'", "'switch'", "'assert'", 
			"'super'", "'throw'", "'case'", "'catch'", "'+'", "'-'", "'~'", "'!'", 
			"'/'", "'%'", "'<<'", "'>>'", "'>>>'", "'<'", "'>'", "'<='", "'>='", 
			"'instanceof'", "'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", 
			"'?'", "'new'", "'++'", "'--'", "'+='", "'-='", "'*='", "'/='", "'%='", 
			"'&='", "'|='", "'^='", "'<<='", "'>>='", "'>>>='", null, null, "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "KEYWORD", 
			"BOOLEAN_LITERAL", "NULL_LITERAL", "IDENTIFIER", "INT_LITERAL", "LONG_LITERAL", 
			"DOUBLE_LITERAL", "FLOAT_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", 
			"COMMENT", "SEPARATOR", "OPERATOR", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Java.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class QualifiedIdentifierContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public List<Token> components = new ArrayList<Token>();
		public List<TerminalNode> IDENTIFIER() { return getTokens(JavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JavaParser.IDENTIFIER, i);
		}
		public QualifiedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterQualifiedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitQualifiedIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitQualifiedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_qualifiedIdentifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((QualifiedIdentifierContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((QualifiedIdentifierContext)_localctx).components.add(((QualifiedIdentifierContext)_localctx).IDENTIFIER);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					match(T__0);
					setState(94);
					((QualifiedIdentifierContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					((QualifiedIdentifierContext)_localctx).components.add(((QualifiedIdentifierContext)_localctx).IDENTIFIER);
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccessModifierContext extends ParserRuleContext {
		public AccessModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterAccessModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitAccessModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitAccessModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessModifierContext accessModifier() throws RecognitionException {
		AccessModifierContext _localctx = new AccessModifierContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_accessModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarTypeContext extends ParserRuleContext {
		public ScalarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarType; }
	 
		public ScalarTypeContext() { }
		public void copyFrom(ScalarTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NamedTypeContext extends ScalarTypeContext {
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public NamedTypeContext(ScalarTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterNamedType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitNamedType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitNamedType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimitiveTypeContext extends ScalarTypeContext {
		public PrimitiveTypeContext(ScalarTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_scalarType);
		int _la;
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
				_localctx = new PrimitiveTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case IDENTIFIER:
				_localctx = new NamedTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				qualifiedIdentifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
	 
		public DataTypeContext() { }
		public void copyFrom(DataTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ScalarDataTypeContext extends DataTypeContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ScalarDataTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterScalarDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitScalarDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitScalarDataType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTypeContext extends DataTypeContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayTypeContext(DataTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dataType);
		try {
			int _alt;
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new ScalarDataTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				scalarType();
				}
				break;
			case 2:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				scalarType();
				setState(110); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(108);
						match(T__12);
						setState(109);
						match(T__13);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(112); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnType);
		try {
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				dataType();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(T__14);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarNameInitializationContext extends ParserRuleContext {
		public Token id;
		public InitializationContext init;
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public InitializationContext initialization() {
			return getRuleContext(InitializationContext.class,0);
		}
		public VarNameInitializationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varNameInitialization; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterVarNameInitialization(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitVarNameInitialization(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitVarNameInitialization(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarNameInitializationContext varNameInitialization() throws RecognitionException {
		VarNameInitializationContext _localctx = new VarNameInitializationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varNameInitialization);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			((VarNameInitializationContext)_localctx).id = match(IDENTIFIER);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(121);
				((VarNameInitializationContext)_localctx).init = initialization();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultVarDeclarationContext extends ParserRuleContext {
		public DataTypeContext type;
		public VarNameInitializationContext varNameInitialization;
		public List<VarNameInitializationContext> nameInits = new ArrayList<VarNameInitializationContext>();
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<VarNameInitializationContext> varNameInitialization() {
			return getRuleContexts(VarNameInitializationContext.class);
		}
		public VarNameInitializationContext varNameInitialization(int i) {
			return getRuleContext(VarNameInitializationContext.class,i);
		}
		public MultVarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multVarDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMultVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMultVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMultVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultVarDeclarationContext multVarDeclaration() throws RecognitionException {
		MultVarDeclarationContext _localctx = new MultVarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_multVarDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			((MultVarDeclarationContext)_localctx).type = dataType();
			setState(125);
			((MultVarDeclarationContext)_localctx).varNameInitialization = varNameInitialization();
			((MultVarDeclarationContext)_localctx).nameInits.add(((MultVarDeclarationContext)_localctx).varNameInitialization);
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(126);
					match(T__15);
					setState(127);
					((MultVarDeclarationContext)_localctx).varNameInitialization = varNameInitialization();
					((MultVarDeclarationContext)_localctx).nameInits.add(((MultVarDeclarationContext)_localctx).varNameInitialization);
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationNoInitContext extends ParserRuleContext {
		public DataTypeContext type;
		public Token id;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public VarDeclarationNoInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclarationNoInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterVarDeclarationNoInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitVarDeclarationNoInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitVarDeclarationNoInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationNoInitContext varDeclarationNoInit() throws RecognitionException {
		VarDeclarationNoInitContext _localctx = new VarDeclarationNoInitContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDeclarationNoInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((VarDeclarationNoInitContext)_localctx).type = dataType();
			setState(134);
			((VarDeclarationNoInitContext)_localctx).id = match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitializationContext extends ParserRuleContext {
		public ExpressionContext rhs;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public InitializationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialization; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterInitialization(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitInitialization(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitInitialization(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializationContext initialization() throws RecognitionException {
		InitializationContext _localctx = new InitializationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_initialization);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(T__16);
				setState(137);
				((InitializationContext)_localctx).rhs = expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(T__16);
				setState(139);
				arrayInitializer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LvalueContext extends ParserRuleContext {
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	 
		public LvalueContext() { }
		public void copyFrom(LvalueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ThisFieldLValueContext extends LvalueContext {
		public Token fieldName;
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ThisFieldLValueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThisFieldLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThisFieldLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThisFieldLValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesizedLValueContext extends LvalueContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ParenthesizedLValueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterParenthesizedLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitParenthesizedLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitParenthesizedLValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FieldRefLValueContext extends LvalueContext {
		public Token fieldName;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public FieldRefLValueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterFieldRefLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitFieldRefLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitFieldRefLValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptLvalueContext extends LvalueContext {
		public ExpressionContext index;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SubscriptLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSubscriptLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSubscriptLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSubscriptLvalue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameLvalueContext extends LvalueContext {
		public QualifiedIdentifierContext id;
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public NameLvalueContext(LvalueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterNameLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitNameLvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitNameLvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				_localctx = new NameLvalueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(143);
				((NameLvalueContext)_localctx).id = qualifiedIdentifier();
				}
				break;
			case T__17:
				{
				_localctx = new ParenthesizedLValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(T__17);
				setState(145);
				lvalue(0);
				setState(146);
				match(T__18);
				}
				break;
			case T__19:
				{
				_localctx = new ThisFieldLValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(T__19);
				setState(149);
				match(T__0);
				setState(150);
				((ThisFieldLValueContext)_localctx).fieldName = match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(163);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(161);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new SubscriptLvalueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(153);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(154);
						match(T__12);
						setState(155);
						((SubscriptLvalueContext)_localctx).index = expression(0);
						setState(156);
						match(T__13);
						}
						break;
					case 2:
						{
						_localctx = new FieldRefLValueContext(new LvalueContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(158);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(159);
						match(T__0);
						setState(160);
						((FieldRefLValueContext)_localctx).fieldName = match(IDENTIFIER);
						}
						break;
					}
					} 
				}
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class JavaFileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(JavaParser.EOF, 0); }
		public PackageStatementContext packageStatement() {
			return getRuleContext(PackageStatementContext.class,0);
		}
		public List<ImportStatementContext> importStatement() {
			return getRuleContexts(ImportStatementContext.class);
		}
		public ImportStatementContext importStatement(int i) {
			return getRuleContext(ImportStatementContext.class,i);
		}
		public List<ClassDefinitionContext> classDefinition() {
			return getRuleContexts(ClassDefinitionContext.class);
		}
		public ClassDefinitionContext classDefinition(int i) {
			return getRuleContext(ClassDefinitionContext.class,i);
		}
		public JavaFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_javaFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterJavaFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitJavaFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitJavaFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JavaFileContext javaFile() throws RecognitionException {
		JavaFileContext _localctx = new JavaFileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_javaFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(166);
				packageStatement();
				}
			}

			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__22) {
				{
				{
				setState(169);
				importStatement();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__25) | (1L << T__26) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) {
				{
				{
				setState(175);
				classDefinition();
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageStatementContext extends ParserRuleContext {
		public QualifiedIdentifierContext id;
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public PackageStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPackageStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPackageStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPackageStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageStatementContext packageStatement() throws RecognitionException {
		PackageStatementContext _localctx = new PackageStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_packageStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__20);
			setState(184);
			((PackageStatementContext)_localctx).id = qualifiedIdentifier();
			setState(185);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportStatementContext extends ParserRuleContext {
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
	 
		public ImportStatementContext() { }
		public void copyFrom(ImportStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ImportClassContext extends ImportStatementContext {
		public QualifiedIdentifierContext id;
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public ImportClassContext(ImportStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterImportClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitImportClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitImportClass(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ImportWildcardContext extends ImportStatementContext {
		public QualifiedIdentifierContext id;
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public ImportWildcardContext(ImportStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterImportWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitImportWildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitImportWildcard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_importStatement);
		int _la;
		try {
			setState(203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new ImportClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				match(T__22);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(188);
					match(T__23);
					}
				}

				setState(191);
				((ImportClassContext)_localctx).id = qualifiedIdentifier();
				setState(192);
				match(T__21);
				}
				break;
			case 2:
				_localctx = new ImportWildcardContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(T__22);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(195);
					match(T__23);
					}
				}

				setState(198);
				((ImportWildcardContext)_localctx).id = qualifiedIdentifier();
				setState(199);
				match(T__0);
				setState(200);
				match(T__24);
				setState(201);
				match(T__21);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefinitionContext extends ParserRuleContext {
		public ClassModifierContext classModifier;
		public List<ClassModifierContext> mods = new ArrayList<ClassModifierContext>();
		public Token type;
		public Token id;
		public QualifiedIdentifierContext qualifiedIdentifier;
		public List<QualifiedIdentifierContext> extending = new ArrayList<QualifiedIdentifierContext>();
		public List<QualifiedIdentifierContext> implementing = new ArrayList<QualifiedIdentifierContext>();
		public ClassBodyContext body;
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public List<ClassModifierContext> classModifier() {
			return getRuleContexts(ClassModifierContext.class);
		}
		public ClassModifierContext classModifier(int i) {
			return getRuleContext(ClassModifierContext.class,i);
		}
		public List<QualifiedIdentifierContext> qualifiedIdentifier() {
			return getRuleContexts(QualifiedIdentifierContext.class);
		}
		public QualifiedIdentifierContext qualifiedIdentifier(int i) {
			return getRuleContext(QualifiedIdentifierContext.class,i);
		}
		public ClassDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefinitionContext classDefinition() throws RecognitionException {
		ClassDefinitionContext _localctx = new ClassDefinitionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_classDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__29) | (1L << T__30) | (1L << T__31))) != 0)) {
				{
				{
				setState(205);
				((ClassDefinitionContext)_localctx).classModifier = classModifier();
				((ClassDefinitionContext)_localctx).mods.add(((ClassDefinitionContext)_localctx).classModifier);
				}
				}
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(211);
			((ClassDefinitionContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__25 || _la==T__26) ) {
				((ClassDefinitionContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(212);
			((ClassDefinitionContext)_localctx).id = match(IDENTIFIER);
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(213);
				match(T__27);
				setState(214);
				((ClassDefinitionContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
				((ClassDefinitionContext)_localctx).extending.add(((ClassDefinitionContext)_localctx).qualifiedIdentifier);
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(215);
					match(T__15);
					setState(216);
					((ClassDefinitionContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
					((ClassDefinitionContext)_localctx).extending.add(((ClassDefinitionContext)_localctx).qualifiedIdentifier);
					}
					}
					setState(221);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(224);
				match(T__28);
				setState(225);
				((ClassDefinitionContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
				((ClassDefinitionContext)_localctx).implementing.add(((ClassDefinitionContext)_localctx).qualifiedIdentifier);
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(226);
					match(T__15);
					setState(227);
					((ClassDefinitionContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
					((ClassDefinitionContext)_localctx).implementing.add(((ClassDefinitionContext)_localctx).qualifiedIdentifier);
					}
					}
					setState(232);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(235);
			((ClassDefinitionContext)_localctx).body = classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassModifierContext extends ParserRuleContext {
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public ClassModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassModifierContext classModifier() throws RecognitionException {
		ClassModifierContext _localctx = new ClassModifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_classModifier);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				accessModifier();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				match(T__29);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 3);
				{
				setState(239);
				match(T__30);
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 4);
				{
				setState(240);
				match(T__31);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public ClassMemberContext classMember;
		public List<ClassMemberContext> members = new ArrayList<ClassMemberContext>();
		public ClassInitializerContext classInitializer;
		public List<ClassInitializerContext> initializers = new ArrayList<ClassInitializerContext>();
		public List<ClassMemberContext> classMember() {
			return getRuleContexts(ClassMemberContext.class);
		}
		public ClassMemberContext classMember(int i) {
			return getRuleContext(ClassMemberContext.class,i);
		}
		public List<ClassInitializerContext> classInitializer() {
			return getRuleContexts(ClassInitializerContext.class);
		}
		public ClassInitializerContext classInitializer(int i) {
			return getRuleContext(ClassInitializerContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(T__32);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__14) | (1L << T__23) | (1L << T__29) | (1L << T__30) | (1L << T__32) | (1L << T__34) | (1L << T__35) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40))) != 0) || _la==IDENTIFIER) {
				{
				setState(246);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(244);
					((ClassBodyContext)_localctx).classMember = classMember();
					((ClassBodyContext)_localctx).members.add(((ClassBodyContext)_localctx).classMember);
					}
					break;
				case 2:
					{
					setState(245);
					((ClassBodyContext)_localctx).classInitializer = classInitializer();
					((ClassBodyContext)_localctx).initializers.add(((ClassBodyContext)_localctx).classInitializer);
					}
					break;
				}
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(251);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassMemberContext extends ParserRuleContext {
		public ClassMemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMember; }
	 
		public ClassMemberContext() { }
		public void copyFrom(ClassMemberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassMethodContext extends ClassMemberContext {
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public ClassMethodContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassMethod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassMethodStubContext extends ClassMemberContext {
		public MethodStubContext methodStub() {
			return getRuleContext(MethodStubContext.class,0);
		}
		public ClassMethodStubContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassMethodStub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassMethodStub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassMethodStub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassFieldContext extends ClassMemberContext {
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public ClassFieldContext(ClassMemberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassMemberContext classMember() throws RecognitionException {
		ClassMemberContext _localctx = new ClassMemberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_classMember);
		try {
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				_localctx = new ClassFieldContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				field();
				}
				break;
			case 2:
				_localctx = new ClassMethodContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				method();
				}
				break;
			case 3:
				_localctx = new ClassMethodStubContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(255);
				methodStub();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassInitializerContext extends ParserRuleContext {
		public Token flag;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ClassInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassInitializerContext classInitializer() throws RecognitionException {
		ClassInitializerContext _localctx = new ClassInitializerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_classInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(258);
				((ClassInitializerContext)_localctx).flag = match(T__23);
				}
			}

			setState(261);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public FieldModifierContext fieldModifier;
		public List<FieldModifierContext> modifiers = new ArrayList<FieldModifierContext>();
		public MultVarDeclarationContext multVarDeclaration() {
			return getRuleContext(MultVarDeclarationContext.class,0);
		}
		public List<FieldModifierContext> fieldModifier() {
			return getRuleContexts(FieldModifierContext.class);
		}
		public FieldModifierContext fieldModifier(int i) {
			return getRuleContext(FieldModifierContext.class,i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__23) | (1L << T__30) | (1L << T__34) | (1L << T__35))) != 0)) {
				{
				{
				setState(263);
				((FieldContext)_localctx).fieldModifier = fieldModifier();
				((FieldContext)_localctx).modifiers.add(((FieldContext)_localctx).fieldModifier);
				}
				}
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(269);
			multVarDeclaration();
			setState(270);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldModifierContext extends ParserRuleContext {
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public FieldModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterFieldModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitFieldModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitFieldModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldModifierContext fieldModifier() throws RecognitionException {
		FieldModifierContext _localctx = new FieldModifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fieldModifier);
		try {
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				accessModifier();
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(T__30);
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				match(T__23);
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				match(T__34);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				match(T__35);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodHeaderContext extends ParserRuleContext {
		public MethodModifierContext methodModifier;
		public List<MethodModifierContext> modifiers = new ArrayList<MethodModifierContext>();
		public ReturnTypeContext type;
		public Token id;
		public MethodParameterContext methodParameter;
		public List<MethodParameterContext> params = new ArrayList<MethodParameterContext>();
		public QualifiedIdentifierContext qualifiedIdentifier;
		public List<QualifiedIdentifierContext> throwing = new ArrayList<QualifiedIdentifierContext>();
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public List<MethodModifierContext> methodModifier() {
			return getRuleContexts(MethodModifierContext.class);
		}
		public MethodModifierContext methodModifier(int i) {
			return getRuleContext(MethodModifierContext.class,i);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public List<MethodParameterContext> methodParameter() {
			return getRuleContexts(MethodParameterContext.class);
		}
		public MethodParameterContext methodParameter(int i) {
			return getRuleContext(MethodParameterContext.class,i);
		}
		public List<QualifiedIdentifierContext> qualifiedIdentifier() {
			return getRuleContexts(QualifiedIdentifierContext.class);
		}
		public QualifiedIdentifierContext qualifiedIdentifier(int i) {
			return getRuleContext(QualifiedIdentifierContext.class,i);
		}
		public MethodHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodHeaderContext methodHeader() throws RecognitionException {
		MethodHeaderContext _localctx = new MethodHeaderContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_methodHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__23) | (1L << T__29) | (1L << T__30) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40))) != 0)) {
				{
				{
				setState(279);
				((MethodHeaderContext)_localctx).methodModifier = methodModifier();
				((MethodHeaderContext)_localctx).modifiers.add(((MethodHeaderContext)_localctx).methodModifier);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(285);
				((MethodHeaderContext)_localctx).type = returnType();
				}
				break;
			}
			setState(288);
			((MethodHeaderContext)_localctx).id = match(IDENTIFIER);
			setState(289);
			match(T__17);
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__30))) != 0) || _la==IDENTIFIER) {
				{
				setState(290);
				((MethodHeaderContext)_localctx).methodParameter = methodParameter();
				((MethodHeaderContext)_localctx).params.add(((MethodHeaderContext)_localctx).methodParameter);
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(291);
					match(T__15);
					setState(292);
					((MethodHeaderContext)_localctx).methodParameter = methodParameter();
					((MethodHeaderContext)_localctx).params.add(((MethodHeaderContext)_localctx).methodParameter);
					}
					}
					setState(297);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(300);
			match(T__18);
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__36) {
				{
				setState(301);
				match(T__36);
				setState(302);
				((MethodHeaderContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
				((MethodHeaderContext)_localctx).throwing.add(((MethodHeaderContext)_localctx).qualifiedIdentifier);
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(303);
					match(T__15);
					setState(304);
					((MethodHeaderContext)_localctx).qualifiedIdentifier = qualifiedIdentifier();
					((MethodHeaderContext)_localctx).throwing.add(((MethodHeaderContext)_localctx).qualifiedIdentifier);
					}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodModifierContext extends ParserRuleContext {
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public MethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodModifierContext methodModifier() throws RecognitionException {
		MethodModifierContext _localctx = new MethodModifierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_methodModifier);
		try {
			setState(320);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				accessModifier();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(T__29);
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
				match(T__37);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 4);
				{
				setState(315);
				match(T__30);
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 5);
				{
				setState(316);
				match(T__38);
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				setState(317);
				match(T__23);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 7);
				{
				setState(318);
				match(T__39);
				}
				break;
			case T__40:
				enterOuterAlt(_localctx, 8);
				{
				setState(319);
				match(T__40);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodParameterContext extends ParserRuleContext {
		public ParameterModifierContext parameterModifier;
		public List<ParameterModifierContext> mods = new ArrayList<ParameterModifierContext>();
		public DataTypeContext type;
		public Token id;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public List<ParameterModifierContext> parameterModifier() {
			return getRuleContexts(ParameterModifierContext.class);
		}
		public ParameterModifierContext parameterModifier(int i) {
			return getRuleContext(ParameterModifierContext.class,i);
		}
		public MethodParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodParameterContext methodParameter() throws RecognitionException {
		MethodParameterContext _localctx = new MethodParameterContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_methodParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(322);
				((MethodParameterContext)_localctx).parameterModifier = parameterModifier();
				((MethodParameterContext)_localctx).mods.add(((MethodParameterContext)_localctx).parameterModifier);
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(328);
			((MethodParameterContext)_localctx).type = dataType();
			setState(329);
			((MethodParameterContext)_localctx).id = match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterModifierContext extends ParserRuleContext {
		public ParameterModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterParameterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitParameterModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitParameterModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterModifierContext parameterModifier() throws RecognitionException {
		ParameterModifierContext _localctx = new ParameterModifierContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_parameterModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(T__30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public MethodHeaderContext header;
		public BlockContext body;
		public MethodHeaderContext methodHeader() {
			return getRuleContext(MethodHeaderContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			((MethodContext)_localctx).header = methodHeader();
			setState(334);
			((MethodContext)_localctx).body = block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodStubContext extends ParserRuleContext {
		public MethodHeaderContext header;
		public MethodHeaderContext methodHeader() {
			return getRuleContext(MethodHeaderContext.class,0);
		}
		public MethodStubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodStub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodStub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodStub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodStub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodStubContext methodStub() throws RecognitionException {
		MethodStubContext _localctx = new MethodStubContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_methodStub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			((MethodStubContext)_localctx).header = methodHeader();
			setState(337);
			match(T__21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyStatementContext extends StatementContext {
		public EmptyStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileLoopContext extends StatementContext {
		public ExpressionContext cond;
		public StatementContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileLoopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TryStatementContext extends StatementContext {
		public BlockContext body;
		public CatchBlockContext catchBlock;
		public List<CatchBlockContext> catches = new ArrayList<CatchBlockContext>();
		public BlockContext finallyBlock;
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<CatchBlockContext> catchBlock() {
			return getRuleContexts(CatchBlockContext.class);
		}
		public CatchBlockContext catchBlock(int i) {
			return getRuleContext(CatchBlockContext.class,i);
		}
		public TryStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitTryStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitTryStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatementContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SynchronizedMonitorBlockContext extends StatementContext {
		public ExpressionContext monitor;
		public BlockContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SynchronizedMonitorBlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSynchronizedMonitorBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSynchronizedMonitorBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSynchronizedMonitorBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EnhancedForLoopContext extends StatementContext {
		public LocalVarDeclarationNoInitContext localVarDeclarationNoInit() {
			return getRuleContext(LocalVarDeclarationNoInitContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public EnhancedForLoopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterEnhancedForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitEnhancedForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitEnhancedForLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfElseStatementContext extends StatementContext {
		public ExpressionContext cond;
		public StatementContext trueBlock;
		public StatementContext falseBlock;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfElseStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterIfElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitIfElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitIfElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStatementContext extends StatementContext {
		public BreakStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatementContext extends StatementContext {
		public ExpressionContext cond;
		public StatementContext trueBlock;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IfStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LeafContext extends StatementContext {
		public LeafStatementContext leafStatement() {
			return getRuleContext(LeafStatementContext.class,0);
		}
		public LeafContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterLeaf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitLeaf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitLeaf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SwitchStatementContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SwitchBodyContext switchBody() {
			return getRuleContext(SwitchBodyContext.class,0);
		}
		public SwitchStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SynchronizedBlockContext extends StatementContext {
		public BlockContext body;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SynchronizedBlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSynchronizedBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSynchronizedBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSynchronizedBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssertStatementContext extends StatementContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssertStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterAssertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitAssertStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitAssertStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStatementContext extends StatementContext {
		public ContinueStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoWhileLoopContext extends StatementContext {
		public StatementContext body;
		public ExpressionContext cond;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoWhileLoopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterDoWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitDoWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitDoWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForLoopContext extends StatementContext {
		public LeafStatementContext leafStatement;
		public List<LeafStatementContext> init = new ArrayList<LeafStatementContext>();
		public ExpressionContext cond;
		public List<LeafStatementContext> update = new ArrayList<LeafStatementContext>();
		public StatementContext body;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<LeafStatementContext> leafStatement() {
			return getRuleContexts(LeafStatementContext.class);
		}
		public LeafStatementContext leafStatement(int i) {
			return getRuleContext(LeafStatementContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForLoopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_statement);
		int _la;
		try {
			setState(452);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				_localctx = new BlockStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				block();
				}
				break;
			case 2:
				_localctx = new EmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				match(T__21);
				}
				break;
			case 3:
				_localctx = new LeafContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(341);
				leafStatement();
				setState(342);
				match(T__21);
				}
				break;
			case 4:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(344);
				match(T__41);
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__19) | (1L << T__54) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
					{
					setState(345);
					expression(0);
					}
				}

				setState(348);
				match(T__21);
				}
				break;
			case 5:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(349);
				match(T__42);
				setState(350);
				match(T__17);
				setState(351);
				((IfStatementContext)_localctx).cond = expression(0);
				setState(352);
				match(T__18);
				setState(353);
				((IfStatementContext)_localctx).trueBlock = statement();
				}
				break;
			case 6:
				_localctx = new IfElseStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(355);
				match(T__42);
				setState(356);
				match(T__17);
				setState(357);
				((IfElseStatementContext)_localctx).cond = expression(0);
				setState(358);
				match(T__18);
				setState(359);
				((IfElseStatementContext)_localctx).trueBlock = statement();
				setState(360);
				match(T__43);
				setState(361);
				((IfElseStatementContext)_localctx).falseBlock = statement();
				}
				break;
			case 7:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(363);
				match(T__44);
				setState(364);
				match(T__17);
				setState(365);
				((WhileLoopContext)_localctx).cond = expression(0);
				setState(366);
				match(T__18);
				setState(367);
				((WhileLoopContext)_localctx).body = statement();
				}
				break;
			case 8:
				_localctx = new DoWhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(369);
				match(T__45);
				setState(370);
				((DoWhileLoopContext)_localctx).body = statement();
				setState(371);
				match(T__44);
				setState(372);
				match(T__17);
				setState(373);
				((DoWhileLoopContext)_localctx).cond = expression(0);
				setState(374);
				match(T__18);
				setState(375);
				match(T__21);
				}
				break;
			case 9:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(377);
				match(T__46);
				setState(378);
				match(T__17);
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__19) | (1L << T__30) | (1L << T__54) | (1L << T__55) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
					{
					setState(379);
					((ForLoopContext)_localctx).leafStatement = leafStatement();
					((ForLoopContext)_localctx).init.add(((ForLoopContext)_localctx).leafStatement);
					setState(384);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__15) {
						{
						{
						setState(380);
						match(T__15);
						setState(381);
						((ForLoopContext)_localctx).leafStatement = leafStatement();
						((ForLoopContext)_localctx).init.add(((ForLoopContext)_localctx).leafStatement);
						}
						}
						setState(386);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(389);
				match(T__21);
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__19) | (1L << T__54) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
					{
					setState(390);
					((ForLoopContext)_localctx).cond = expression(0);
					}
				}

				setState(393);
				match(T__21);
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__19) | (1L << T__30) | (1L << T__54) | (1L << T__55) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
					{
					setState(394);
					((ForLoopContext)_localctx).leafStatement = leafStatement();
					((ForLoopContext)_localctx).update.add(((ForLoopContext)_localctx).leafStatement);
					setState(399);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__15) {
						{
						{
						setState(395);
						match(T__15);
						setState(396);
						((ForLoopContext)_localctx).leafStatement = leafStatement();
						((ForLoopContext)_localctx).update.add(((ForLoopContext)_localctx).leafStatement);
						}
						}
						setState(401);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(404);
				match(T__18);
				setState(405);
				((ForLoopContext)_localctx).body = statement();
				}
				break;
			case 10:
				_localctx = new EnhancedForLoopContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(406);
				match(T__46);
				setState(407);
				match(T__17);
				setState(408);
				localVarDeclarationNoInit();
				setState(409);
				match(T__47);
				setState(410);
				expression(0);
				setState(411);
				match(T__18);
				setState(412);
				statement();
				}
				break;
			case 11:
				_localctx = new TryStatementContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(414);
				match(T__48);
				setState(415);
				((TryStatementContext)_localctx).body = block();
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__57) {
					{
					{
					setState(416);
					((TryStatementContext)_localctx).catchBlock = catchBlock();
					((TryStatementContext)_localctx).catches.add(((TryStatementContext)_localctx).catchBlock);
					}
					}
					setState(421);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__49) {
					{
					setState(422);
					match(T__49);
					setState(423);
					((TryStatementContext)_localctx).finallyBlock = block();
					}
				}

				}
				break;
			case 12:
				_localctx = new SynchronizedBlockContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(426);
				match(T__40);
				setState(427);
				((SynchronizedBlockContext)_localctx).body = block();
				}
				break;
			case 13:
				_localctx = new SynchronizedMonitorBlockContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(428);
				match(T__40);
				setState(429);
				match(T__17);
				setState(430);
				((SynchronizedMonitorBlockContext)_localctx).monitor = expression(0);
				setState(431);
				match(T__18);
				setState(432);
				((SynchronizedMonitorBlockContext)_localctx).body = block();
				}
				break;
			case 14:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(434);
				match(T__50);
				setState(435);
				match(T__21);
				}
				break;
			case 15:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(436);
				match(T__51);
				setState(437);
				match(T__21);
				}
				break;
			case 16:
				_localctx = new SwitchStatementContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(438);
				match(T__52);
				setState(439);
				match(T__17);
				setState(440);
				expression(0);
				setState(441);
				match(T__18);
				setState(442);
				switchBody();
				}
				break;
			case 17:
				_localctx = new AssertStatementContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(444);
				match(T__53);
				setState(445);
				expression(0);
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__47) {
					{
					setState(446);
					match(T__47);
					setState(447);
					expression(0);
					}
				}

				setState(450);
				match(T__21);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public StatementContext statement;
		public List<StatementContext> statements = new ArrayList<StatementContext>();
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			match(T__32);
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__19) | (1L << T__21) | (1L << T__30) | (1L << T__32) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__48) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
				{
				{
				setState(455);
				((BlockContext)_localctx).statement = statement();
				((BlockContext)_localctx).statements.add(((BlockContext)_localctx).statement);
				}
				}
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(461);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeafStatementContext extends ParserRuleContext {
		public LeafStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leafStatement; }
	 
		public LeafStatementContext() { }
		public void copyFrom(LeafStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DeclarationStatementContext extends LeafStatementContext {
		public LocalVariableModifierContext localVariableModifier;
		public List<LocalVariableModifierContext> modifiers = new ArrayList<LocalVariableModifierContext>();
		public MultVarDeclarationContext multVarDeclaration() {
			return getRuleContext(MultVarDeclarationContext.class,0);
		}
		public List<LocalVariableModifierContext> localVariableModifier() {
			return getRuleContexts(LocalVariableModifierContext.class);
		}
		public LocalVariableModifierContext localVariableModifier(int i) {
			return getRuleContext(LocalVariableModifierContext.class,i);
		}
		public DeclarationStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitDeclarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitDeclarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreIncrementStatementContext extends LeafStatementContext {
		public PreIncrementContext preIncrement() {
			return getRuleContext(PreIncrementContext.class,0);
		}
		public PreIncrementStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPreIncrementStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPreIncrementStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPreIncrementStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisMethodCallStatementContext extends LeafStatementContext {
		public ThisMethodCallContext thisMethodCall() {
			return getRuleContext(ThisMethodCallContext.class,0);
		}
		public ThisMethodCallStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThisMethodCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThisMethodCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThisMethodCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodCallStatementContext extends LeafStatementContext {
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodCallStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThrowStatementContext extends LeafStatementContext {
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostIncrementStatementContext extends LeafStatementContext {
		public PostIncrementContext postIncrement() {
			return getRuleContext(PostIncrementContext.class,0);
		}
		public PostIncrementStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPostIncrementStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPostIncrementStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPostIncrementStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuperMethodCallStatementContext extends LeafStatementContext {
		public SuperMethodCallContext superMethodCall() {
			return getRuleContext(SuperMethodCallContext.class,0);
		}
		public SuperMethodCallStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSuperMethodCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSuperMethodCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSuperMethodCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuperConstructorCallStatementContext extends LeafStatementContext {
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SuperConstructorCallStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSuperConstructorCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSuperConstructorCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSuperConstructorCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CombinedAssignmentStatementContext extends LeafStatementContext {
		public CombinedAssignmentContext combinedAssignment() {
			return getRuleContext(CombinedAssignmentContext.class,0);
		}
		public CombinedAssignmentStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCombinedAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCombinedAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCombinedAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstantiationStatementContext extends LeafStatementContext {
		public InstantiationContext instantiation() {
			return getRuleContext(InstantiationContext.class,0);
		}
		public InstantiationStatementContext(LeafStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterInstantiationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitInstantiationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitInstantiationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeafStatementContext leafStatement() throws RecognitionException {
		LeafStatementContext _localctx = new LeafStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_leafStatement);
		int _la;
		try {
			setState(492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				_localctx = new DeclarationStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(463);
					((DeclarationStatementContext)_localctx).localVariableModifier = localVariableModifier();
					((DeclarationStatementContext)_localctx).modifiers.add(((DeclarationStatementContext)_localctx).localVariableModifier);
					}
					}
					setState(468);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(469);
				multVarDeclaration();
				}
				break;
			case 2:
				_localctx = new CombinedAssignmentStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(470);
				combinedAssignment();
				}
				break;
			case 3:
				_localctx = new PreIncrementStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(471);
				preIncrement();
				}
				break;
			case 4:
				_localctx = new PostIncrementStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(472);
				postIncrement();
				}
				break;
			case 5:
				_localctx = new SuperMethodCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(473);
				superMethodCall();
				}
				break;
			case 6:
				_localctx = new ThisMethodCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(474);
				thisMethodCall();
				}
				break;
			case 7:
				_localctx = new MethodCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(475);
				methodCall();
				}
				break;
			case 8:
				_localctx = new SuperConstructorCallStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(476);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__54) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(477);
				match(T__17);
				setState(486);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__19) | (1L << T__54) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
					{
					setState(478);
					((SuperConstructorCallStatementContext)_localctx).expression = expression(0);
					((SuperConstructorCallStatementContext)_localctx).args.add(((SuperConstructorCallStatementContext)_localctx).expression);
					setState(483);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__15) {
						{
						{
						setState(479);
						match(T__15);
						setState(480);
						((SuperConstructorCallStatementContext)_localctx).expression = expression(0);
						((SuperConstructorCallStatementContext)_localctx).args.add(((SuperConstructorCallStatementContext)_localctx).expression);
						}
						}
						setState(485);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(488);
				match(T__18);
				}
				break;
			case 9:
				_localctx = new InstantiationStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(489);
				instantiation();
				}
				break;
			case 10:
				_localctx = new ThrowStatementContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(490);
				match(T__55);
				setState(491);
				((ThrowStatementContext)_localctx).expr = expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableModifierContext extends ParserRuleContext {
		public LocalVariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterLocalVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitLocalVariableModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitLocalVariableModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVariableModifierContext localVariableModifier() throws RecognitionException {
		LocalVariableModifierContext _localctx = new LocalVariableModifierContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_localVariableModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(T__30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBodyContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<CaseLabelContext> caseLabel() {
			return getRuleContexts(CaseLabelContext.class);
		}
		public CaseLabelContext caseLabel(int i) {
			return getRuleContext(CaseLabelContext.class,i);
		}
		public List<DefaultLabelContext> defaultLabel() {
			return getRuleContexts(DefaultLabelContext.class);
		}
		public DefaultLabelContext defaultLabel(int i) {
			return getRuleContext(DefaultLabelContext.class,i);
		}
		public SwitchBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSwitchBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSwitchBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSwitchBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchBodyContext switchBody() throws RecognitionException {
		SwitchBodyContext _localctx = new SwitchBodyContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_switchBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			match(T__32);
			setState(502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__19) | (1L << T__21) | (1L << T__30) | (1L << T__32) | (1L << T__37) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__48) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
				{
				setState(500);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case T__17:
				case T__19:
				case T__21:
				case T__30:
				case T__32:
				case T__40:
				case T__41:
				case T__42:
				case T__44:
				case T__45:
				case T__46:
				case T__48:
				case T__50:
				case T__51:
				case T__52:
				case T__53:
				case T__54:
				case T__55:
				case T__58:
				case T__59:
				case T__60:
				case T__61:
				case T__80:
				case T__81:
				case T__82:
				case BOOLEAN_LITERAL:
				case NULL_LITERAL:
				case IDENTIFIER:
				case INT_LITERAL:
				case LONG_LITERAL:
				case DOUBLE_LITERAL:
				case FLOAT_LITERAL:
				case CHAR_LITERAL:
				case STRING_LITERAL:
					{
					setState(497);
					statement();
					}
					break;
				case T__56:
					{
					setState(498);
					caseLabel();
					}
					break;
				case T__37:
					{
					setState(499);
					defaultLabel();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(504);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(505);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseLabelContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CaseLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCaseLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCaseLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCaseLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseLabelContext caseLabel() throws RecognitionException {
		CaseLabelContext _localctx = new CaseLabelContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_caseLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			match(T__56);
			setState(508);
			expression(0);
			setState(509);
			match(T__47);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultLabelContext extends ParserRuleContext {
		public DefaultLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterDefaultLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitDefaultLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitDefaultLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultLabelContext defaultLabel() throws RecognitionException {
		DefaultLabelContext _localctx = new DefaultLabelContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_defaultLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(T__37);
			setState(512);
			match(T__47);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVarDeclarationNoInitContext extends ParserRuleContext {
		public LocalVariableModifierContext localVariableModifier;
		public List<LocalVariableModifierContext> modifiers = new ArrayList<LocalVariableModifierContext>();
		public VarDeclarationNoInitContext varDeclarationNoInit() {
			return getRuleContext(VarDeclarationNoInitContext.class,0);
		}
		public List<LocalVariableModifierContext> localVariableModifier() {
			return getRuleContexts(LocalVariableModifierContext.class);
		}
		public LocalVariableModifierContext localVariableModifier(int i) {
			return getRuleContext(LocalVariableModifierContext.class,i);
		}
		public LocalVarDeclarationNoInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVarDeclarationNoInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterLocalVarDeclarationNoInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitLocalVarDeclarationNoInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitLocalVarDeclarationNoInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVarDeclarationNoInitContext localVarDeclarationNoInit() throws RecognitionException {
		LocalVarDeclarationNoInitContext _localctx = new LocalVarDeclarationNoInitContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_localVarDeclarationNoInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(514);
				((LocalVarDeclarationNoInitContext)_localctx).localVariableModifier = localVariableModifier();
				((LocalVarDeclarationNoInitContext)_localctx).modifiers.add(((LocalVarDeclarationNoInitContext)_localctx).localVariableModifier);
				}
				}
				setState(519);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(520);
			varDeclarationNoInit();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchBlockContext extends ParserRuleContext {
		public QualifiedIdentifierContext type;
		public Token id;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public CatchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCatchBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCatchBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCatchBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchBlockContext catchBlock() throws RecognitionException {
		CatchBlockContext _localctx = new CatchBlockContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_catchBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			match(T__57);
			setState(523);
			match(T__17);
			setState(524);
			((CatchBlockContext)_localctx).type = qualifiedIdentifier();
			setState(525);
			((CatchBlockContext)_localctx).id = match(IDENTIFIER);
			setState(526);
			match(T__18);
			setState(527);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnaryOperatorContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterUnaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitUnaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CombinedAssignmentExpressionContext extends ExpressionContext {
		public CombinedAssignmentContext combinedAssignment() {
			return getRuleContext(CombinedAssignmentContext.class,0);
		}
		public CombinedAssignmentExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCombinedAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCombinedAssignmentExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCombinedAssignmentExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodCallExpressionContext extends ExpressionContext {
		public Token name;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public MethodCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArraySubscriptContext extends ExpressionContext {
		public ExpressionContext array;
		public ExpressionContext index;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArraySubscriptContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArraySubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArraySubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArraySubscript(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CastContext extends ExpressionContext {
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCast(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesizedExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesizedExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayInstantiationWithInitializerContext extends ExpressionContext {
		public ScalarTypeContext type;
		public Token s13;
		public List<Token> emptyDims = new ArrayList<Token>();
		public ArrayInitializerContext init;
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayInstantiationWithInitializerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArrayInstantiationWithInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArrayInstantiationWithInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArrayInstantiationWithInitializer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstantiationExpressionContext extends ExpressionContext {
		public InstantiationContext instantiation() {
			return getRuleContext(InstantiationContext.class,0);
		}
		public InstantiationExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterInstantiationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitInstantiationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitInstantiationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatLiteralContext extends ExpressionContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(JavaParser.FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FieldReferenceContext extends ExpressionContext {
		public Token fieldName;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public FieldReferenceContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterFieldReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitFieldReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitFieldReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisMethodCallExpressionContext extends ExpressionContext {
		public ThisMethodCallContext thisMethodCall() {
			return getRuleContext(ThisMethodCallContext.class,0);
		}
		public ThisMethodCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThisMethodCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThisMethodCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThisMethodCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreIncrementExpressionContext extends ExpressionContext {
		public PreIncrementContext preIncrement() {
			return getRuleContext(PreIncrementContext.class,0);
		}
		public PreIncrementExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPreIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPreIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPreIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends ExpressionContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(JavaParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NameExpressionContext extends ExpressionContext {
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public NameExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterNameExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitNameExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitNameExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionalExpressionContext extends ExpressionContext {
		public ExpressionContext cond;
		public ExpressionContext trueExpr;
		public ExpressionContext falseExpr;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiteralContext extends ExpressionContext {
		public TerminalNode INT_LITERAL() { return getToken(JavaParser.INT_LITERAL, 0); }
		public IntLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassLiteralContext extends ExpressionContext {
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public ClassLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterClassLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitClassLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitClassLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLiteralContext extends ExpressionContext {
		public TerminalNode CHAR_LITERAL() { return getToken(JavaParser.CHAR_LITERAL, 0); }
		public CharLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCharLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCharLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCharLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuperMethodCallExpressionContext extends ExpressionContext {
		public SuperMethodCallContext superMethodCall() {
			return getRuleContext(SuperMethodCallContext.class,0);
		}
		public SuperMethodCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSuperMethodCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSuperMethodCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSuperMethodCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayInstantiationNoInitializerContext extends ExpressionContext {
		public ScalarTypeContext type;
		public ExpressionContext expression;
		public List<ExpressionContext> dims = new ArrayList<ExpressionContext>();
		public Token s13;
		public List<Token> emptyDims = new ArrayList<Token>();
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayInstantiationNoInitializerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArrayInstantiationNoInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArrayInstantiationNoInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArrayInstantiationNoInitializer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisReferenceContext extends ExpressionContext {
		public ThisReferenceContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThisReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThisReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThisReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstanceofContext extends ExpressionContext {
		public ExpressionContext expr;
		public DataTypeContext type;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public InstanceofContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterInstanceof(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitInstanceof(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitInstanceof(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends ExpressionContext {
		public TerminalNode NULL_LITERAL() { return getToken(JavaParser.NULL_LITERAL, 0); }
		public NullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExpressionContext {
		public TerminalNode STRING_LITERAL() { return getToken(JavaParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryOperatorContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterBinaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitBinaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitBinaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleLiteralContext extends ExpressionContext {
		public TerminalNode DOUBLE_LITERAL() { return getToken(JavaParser.DOUBLE_LITERAL, 0); }
		public DoubleLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostIncrementExpressionContext extends ExpressionContext {
		public PostIncrementContext postIncrement() {
			return getRuleContext(PostIncrementContext.class,0);
		}
		public PostIncrementExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPostIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPostIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPostIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LongLiteralContext extends ExpressionContext {
		public TerminalNode LONG_LITERAL() { return getToken(JavaParser.LONG_LITERAL, 0); }
		public LongLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterLongLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitLongLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitLongLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 70;
		enterRecursionRule(_localctx, 70, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(588);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				_localctx = new IntLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(530);
				match(INT_LITERAL);
				}
				break;
			case 2:
				{
				_localctx = new LongLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(531);
				match(LONG_LITERAL);
				}
				break;
			case 3:
				{
				_localctx = new FloatLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(532);
				match(FLOAT_LITERAL);
				}
				break;
			case 4:
				{
				_localctx = new DoubleLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(533);
				match(DOUBLE_LITERAL);
				}
				break;
			case 5:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(534);
				match(BOOLEAN_LITERAL);
				}
				break;
			case 6:
				{
				_localctx = new CharLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(535);
				match(CHAR_LITERAL);
				}
				break;
			case 7:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(536);
				match(STRING_LITERAL);
				}
				break;
			case 8:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(537);
				match(NULL_LITERAL);
				}
				break;
			case 9:
				{
				_localctx = new ThisMethodCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(538);
				thisMethodCall();
				}
				break;
			case 10:
				{
				_localctx = new SuperMethodCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(539);
				superMethodCall();
				}
				break;
			case 11:
				{
				_localctx = new ClassLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(540);
				qualifiedIdentifier();
				setState(541);
				match(T__0);
				setState(542);
				match(T__25);
				}
				break;
			case 12:
				{
				_localctx = new NameExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(544);
				qualifiedIdentifier();
				}
				break;
			case 13:
				{
				_localctx = new PostIncrementExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(545);
				postIncrement();
				}
				break;
			case 14:
				{
				_localctx = new PreIncrementExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(546);
				preIncrement();
				}
				break;
			case 15:
				{
				_localctx = new UnaryOperatorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(547);
				((UnaryOperatorContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0)) ) {
					((UnaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(548);
				((UnaryOperatorContext)_localctx).expr = expression(20);
				}
				break;
			case 16:
				{
				_localctx = new CombinedAssignmentExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(549);
				combinedAssignment();
				}
				break;
			case 17:
				{
				_localctx = new InstantiationExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(550);
				instantiation();
				}
				break;
			case 18:
				{
				_localctx = new ArrayInstantiationNoInitializerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(551);
				match(T__80);
				setState(552);
				((ArrayInstantiationNoInitializerContext)_localctx).type = scalarType();
				setState(557); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(553);
						match(T__12);
						setState(554);
						((ArrayInstantiationNoInitializerContext)_localctx).expression = expression(0);
						((ArrayInstantiationNoInitializerContext)_localctx).dims.add(((ArrayInstantiationNoInitializerContext)_localctx).expression);
						setState(555);
						match(T__13);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(559); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(565);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(561);
						((ArrayInstantiationNoInitializerContext)_localctx).s13 = match(T__12);
						((ArrayInstantiationNoInitializerContext)_localctx).emptyDims.add(((ArrayInstantiationNoInitializerContext)_localctx).s13);
						setState(562);
						match(T__13);
						}
						} 
					}
					setState(567);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				}
				}
				break;
			case 19:
				{
				_localctx = new ArrayInstantiationWithInitializerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(568);
				match(T__80);
				setState(569);
				((ArrayInstantiationWithInitializerContext)_localctx).type = scalarType();
				setState(572); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(570);
					((ArrayInstantiationWithInitializerContext)_localctx).s13 = match(T__12);
					((ArrayInstantiationWithInitializerContext)_localctx).emptyDims.add(((ArrayInstantiationWithInitializerContext)_localctx).s13);
					setState(571);
					match(T__13);
					}
					}
					setState(574); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__12 );
				setState(576);
				((ArrayInstantiationWithInitializerContext)_localctx).init = arrayInitializer();
				}
				break;
			case 20:
				{
				_localctx = new CastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(578);
				match(T__17);
				setState(579);
				dataType();
				setState(580);
				match(T__18);
				setState(581);
				expression(3);
				}
				break;
			case 21:
				{
				_localctx = new ThisReferenceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(583);
				match(T__19);
				}
				break;
			case 22:
				{
				_localctx = new ParenthesizedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(584);
				match(T__17);
				setState(585);
				expression(0);
				setState(586);
				match(T__18);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(643);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(641);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(590);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(591);
						((BinaryOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (T__24 - 25)) | (1L << (T__62 - 25)) | (1L << (T__63 - 25)))) != 0)) ) {
							((BinaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(592);
						((BinaryOperatorContext)_localctx).right = expression(20);
						}
						break;
					case 2:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(593);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(594);
						((BinaryOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__58 || _la==T__59) ) {
							((BinaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(595);
						((BinaryOperatorContext)_localctx).right = expression(19);
						}
						break;
					case 3:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(596);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(597);
						((BinaryOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (T__64 - 65)) | (1L << (T__65 - 65)) | (1L << (T__66 - 65)))) != 0)) ) {
							((BinaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(598);
						((BinaryOperatorContext)_localctx).right = expression(18);
						}
						break;
					case 4:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(599);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(600);
						((BinaryOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (T__67 - 68)) | (1L << (T__68 - 68)) | (1L << (T__69 - 68)) | (1L << (T__70 - 68)))) != 0)) ) {
							((BinaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(601);
						((BinaryOperatorContext)_localctx).right = expression(17);
						}
						break;
					case 5:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(602);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(603);
						((BinaryOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__72 || _la==T__73) ) {
							((BinaryOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(604);
						((BinaryOperatorContext)_localctx).right = expression(15);
						}
						break;
					case 6:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(605);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(606);
						((BinaryOperatorContext)_localctx).op = match(T__74);
						setState(607);
						((BinaryOperatorContext)_localctx).right = expression(14);
						}
						break;
					case 7:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(608);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(609);
						((BinaryOperatorContext)_localctx).op = match(T__75);
						setState(610);
						((BinaryOperatorContext)_localctx).right = expression(13);
						}
						break;
					case 8:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(611);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(612);
						((BinaryOperatorContext)_localctx).op = match(T__76);
						setState(613);
						((BinaryOperatorContext)_localctx).right = expression(12);
						}
						break;
					case 9:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(614);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(615);
						((BinaryOperatorContext)_localctx).op = match(T__77);
						setState(616);
						((BinaryOperatorContext)_localctx).right = expression(11);
						}
						break;
					case 10:
						{
						_localctx = new BinaryOperatorContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryOperatorContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(617);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(618);
						((BinaryOperatorContext)_localctx).op = match(T__78);
						setState(619);
						((BinaryOperatorContext)_localctx).right = expression(10);
						}
						break;
					case 11:
						{
						_localctx = new ConditionalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ConditionalExpressionContext)_localctx).cond = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(620);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(621);
						match(T__79);
						setState(622);
						((ConditionalExpressionContext)_localctx).trueExpr = expression(0);
						setState(623);
						match(T__47);
						setState(624);
						((ConditionalExpressionContext)_localctx).falseExpr = expression(8);
						}
						break;
					case 12:
						{
						_localctx = new FieldReferenceContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(626);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(627);
						match(T__0);
						setState(628);
						((FieldReferenceContext)_localctx).fieldName = match(IDENTIFIER);
						}
						break;
					case 13:
						{
						_localctx = new MethodCallExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(629);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(630);
						match(T__0);
						setState(631);
						((MethodCallExpressionContext)_localctx).name = match(IDENTIFIER);
						setState(632);
						arguments();
						}
						break;
					case 14:
						{
						_localctx = new ArraySubscriptContext(new ExpressionContext(_parentctx, _parentState));
						((ArraySubscriptContext)_localctx).array = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(633);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(634);
						match(T__12);
						setState(635);
						((ArraySubscriptContext)_localctx).index = expression(0);
						setState(636);
						match(T__13);
						}
						break;
					case 15:
						{
						_localctx = new InstanceofContext(new ExpressionContext(_parentctx, _parentState));
						((InstanceofContext)_localctx).expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(638);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(639);
						match(T__71);
						setState(640);
						((InstanceofContext)_localctx).type = dataType();
						}
						break;
					}
					} 
				}
				setState(645);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			match(T__17);
			setState(655);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__19) | (1L << T__54) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
				{
				setState(647);
				((ArgumentsContext)_localctx).expression = expression(0);
				((ArgumentsContext)_localctx).args.add(((ArgumentsContext)_localctx).expression);
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(648);
					match(T__15);
					setState(649);
					((ArgumentsContext)_localctx).expression = expression(0);
					((ArgumentsContext)_localctx).args.add(((ArgumentsContext)_localctx).expression);
					}
					}
					setState(654);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(657);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstantiationContext extends ParserRuleContext {
		public QualifiedIdentifierContext type;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public InstantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instantiation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterInstantiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitInstantiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitInstantiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstantiationContext instantiation() throws RecognitionException {
		InstantiationContext _localctx = new InstantiationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_instantiation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(659);
			match(T__80);
			setState(660);
			((InstantiationContext)_localctx).type = qualifiedIdentifier();
			setState(661);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostIncrementContext extends ParserRuleContext {
		public Token op;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public PostIncrementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postIncrement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPostIncrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPostIncrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPostIncrement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostIncrementContext postIncrement() throws RecognitionException {
		PostIncrementContext _localctx = new PostIncrementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_postIncrement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(663);
			lvalue(0);
			setState(664);
			((PostIncrementContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__81 || _la==T__82) ) {
				((PostIncrementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreIncrementContext extends ParserRuleContext {
		public Token op;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public PreIncrementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preIncrement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterPreIncrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitPreIncrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitPreIncrement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreIncrementContext preIncrement() throws RecognitionException {
		PreIncrementContext _localctx = new PreIncrementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_preIncrement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			((PreIncrementContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__81 || _la==T__82) ) {
				((PreIncrementContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(667);
			lvalue(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperMethodCallContext extends ParserRuleContext {
		public Token name;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public SuperMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superMethodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterSuperMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitSuperMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitSuperMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperMethodCallContext superMethodCall() throws RecognitionException {
		SuperMethodCallContext _localctx = new SuperMethodCallContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_superMethodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			match(T__54);
			setState(670);
			match(T__0);
			setState(671);
			((SuperMethodCallContext)_localctx).name = match(IDENTIFIER);
			setState(672);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThisMethodCallContext extends ParserRuleContext {
		public Token thisRef;
		public Token name;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ThisMethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thisMethodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterThisMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitThisMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitThisMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThisMethodCallContext thisMethodCall() throws RecognitionException {
		ThisMethodCallContext _localctx = new ThisMethodCallContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_thisMethodCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(676);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(674);
				((ThisMethodCallContext)_localctx).thisRef = match(T__19);
				setState(675);
				match(T__0);
				}
			}

			setState(678);
			((ThisMethodCallContext)_localctx).name = match(IDENTIFIER);
			setState(679);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public Token name;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_methodCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681);
			expression(0);
			setState(682);
			match(T__0);
			setState(683);
			((MethodCallContext)_localctx).name = match(IDENTIFIER);
			setState(684);
			arguments();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CombinedAssignmentContext extends ParserRuleContext {
		public Token op;
		public ExpressionContext rhs;
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CombinedAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combinedAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterCombinedAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitCombinedAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitCombinedAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CombinedAssignmentContext combinedAssignment() throws RecognitionException {
		CombinedAssignmentContext _localctx = new CombinedAssignmentContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_combinedAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			lvalue(0);
			setState(687);
			((CombinedAssignmentContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__16 || ((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (T__83 - 84)) | (1L << (T__84 - 84)) | (1L << (T__85 - 84)) | (1L << (T__86 - 84)) | (1L << (T__87 - 84)) | (1L << (T__88 - 84)) | (1L << (T__89 - 84)) | (1L << (T__90 - 84)) | (1L << (T__91 - 84)) | (1L << (T__92 - 84)) | (1L << (T__93 - 84)))) != 0)) ) {
				((CombinedAssignmentContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(688);
			((CombinedAssignmentContext)_localctx).rhs = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitializerContext extends ParserRuleContext {
		public ArrayInitializerItemContext arrayInitializerItem;
		public List<ArrayInitializerItemContext> items = new ArrayList<ArrayInitializerItemContext>();
		public List<ArrayInitializerItemContext> arrayInitializerItem() {
			return getRuleContexts(ArrayInitializerItemContext.class);
		}
		public ArrayInitializerItemContext arrayInitializerItem(int i) {
			return getRuleContext(ArrayInitializerItemContext.class,i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_arrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(690);
			match(T__32);
			setState(699);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__19) | (1L << T__32) | (1L << T__54) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61))) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & ((1L << (T__80 - 81)) | (1L << (T__81 - 81)) | (1L << (T__82 - 81)) | (1L << (BOOLEAN_LITERAL - 81)) | (1L << (NULL_LITERAL - 81)) | (1L << (IDENTIFIER - 81)) | (1L << (INT_LITERAL - 81)) | (1L << (LONG_LITERAL - 81)) | (1L << (DOUBLE_LITERAL - 81)) | (1L << (FLOAT_LITERAL - 81)) | (1L << (CHAR_LITERAL - 81)) | (1L << (STRING_LITERAL - 81)))) != 0)) {
				{
				setState(691);
				((ArrayInitializerContext)_localctx).arrayInitializerItem = arrayInitializerItem();
				((ArrayInitializerContext)_localctx).items.add(((ArrayInitializerContext)_localctx).arrayInitializerItem);
				setState(696);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__15) {
					{
					{
					setState(692);
					match(T__15);
					setState(693);
					((ArrayInitializerContext)_localctx).arrayInitializerItem = arrayInitializerItem();
					((ArrayInitializerContext)_localctx).items.add(((ArrayInitializerContext)_localctx).arrayInitializerItem);
					}
					}
					setState(698);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(701);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitializerItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayInitializerItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializerItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).enterArrayInitializerItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaListener ) ((JavaListener)listener).exitArrayInitializerItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaVisitor ) return ((JavaVisitor<? extends T>)visitor).visitArrayInitializerItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerItemContext arrayInitializerItem() throws RecognitionException {
		ArrayInitializerItemContext _localctx = new ArrayInitializerItemContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_arrayInitializerItem);
		try {
			setState(705);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
			case T__19:
			case T__54:
			case T__58:
			case T__59:
			case T__60:
			case T__61:
			case T__80:
			case T__81:
			case T__82:
			case BOOLEAN_LITERAL:
			case NULL_LITERAL:
			case IDENTIFIER:
			case INT_LITERAL:
			case LONG_LITERAL:
			case DOUBLE_LITERAL:
			case FLOAT_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(703);
				expression(0);
				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 2);
				{
				setState(704);
				arrayInitializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		case 35:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 19);
		case 3:
			return precpred(_ctx, 18);
		case 4:
			return precpred(_ctx, 17);
		case 5:
			return precpred(_ctx, 16);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 12);
		case 9:
			return precpred(_ctx, 11);
		case 10:
			return precpred(_ctx, 10);
		case 11:
			return precpred(_ctx, 9);
		case 12:
			return precpred(_ctx, 8);
		case 13:
			return precpred(_ctx, 25);
		case 14:
			return precpred(_ctx, 24);
		case 15:
			return precpred(_ctx, 23);
		case 16:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3n\u02c6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\7\2b\n\2\f\2\16\2e\13\2\3\3\3\3\3\4"+
		"\3\4\5\4k\n\4\3\5\3\5\3\5\3\5\6\5q\n\5\r\5\16\5r\5\5u\n\5\3\6\3\6\5\6"+
		"y\n\6\3\7\3\7\5\7}\n\7\3\b\3\b\3\b\3\b\7\b\u0083\n\b\f\b\16\b\u0086\13"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n\u008f\n\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u009a\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00a4\n\13\f\13\16\13\u00a7\13\13\3\f\5\f\u00aa\n\f\3\f\7\f"+
		"\u00ad\n\f\f\f\16\f\u00b0\13\f\3\f\7\f\u00b3\n\f\f\f\16\f\u00b6\13\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\5\16\u00c0\n\16\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u00c7\n\16\3\16\3\16\3\16\3\16\3\16\5\16\u00ce\n\16\3\17\7\17"+
		"\u00d1\n\17\f\17\16\17\u00d4\13\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17"+
		"\u00dc\n\17\f\17\16\17\u00df\13\17\5\17\u00e1\n\17\3\17\3\17\3\17\3\17"+
		"\7\17\u00e7\n\17\f\17\16\17\u00ea\13\17\5\17\u00ec\n\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\5\20\u00f4\n\20\3\21\3\21\3\21\7\21\u00f9\n\21\f\21\16"+
		"\21\u00fc\13\21\3\21\3\21\3\22\3\22\3\22\5\22\u0103\n\22\3\23\5\23\u0106"+
		"\n\23\3\23\3\23\3\24\7\24\u010b\n\24\f\24\16\24\u010e\13\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\5\25\u0118\n\25\3\26\7\26\u011b\n\26\f"+
		"\26\16\26\u011e\13\26\3\26\5\26\u0121\n\26\3\26\3\26\3\26\3\26\3\26\7"+
		"\26\u0128\n\26\f\26\16\26\u012b\13\26\5\26\u012d\n\26\3\26\3\26\3\26\3"+
		"\26\3\26\7\26\u0134\n\26\f\26\16\26\u0137\13\26\5\26\u0139\n\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0143\n\27\3\30\7\30\u0146\n\30"+
		"\f\30\16\30\u0149\13\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u015d\n\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\7\34\u0181\n\34\f\34\16\34\u0184\13\34\5\34\u0186"+
		"\n\34\3\34\3\34\5\34\u018a\n\34\3\34\3\34\3\34\3\34\7\34\u0190\n\34\f"+
		"\34\16\34\u0193\13\34\5\34\u0195\n\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u01a4\n\34\f\34\16\34\u01a7\13"+
		"\34\3\34\3\34\5\34\u01ab\n\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\5\34\u01c3\n\34\3\34\3\34\5\34\u01c7\n\34\3\35\3\35\7\35\u01cb\n\35\f"+
		"\35\16\35\u01ce\13\35\3\35\3\35\3\36\7\36\u01d3\n\36\f\36\16\36\u01d6"+
		"\13\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36"+
		"\u01e4\n\36\f\36\16\36\u01e7\13\36\5\36\u01e9\n\36\3\36\3\36\3\36\3\36"+
		"\5\36\u01ef\n\36\3\37\3\37\3 \3 \3 \3 \7 \u01f7\n \f \16 \u01fa\13 \3"+
		" \3 \3!\3!\3!\3!\3\"\3\"\3\"\3#\7#\u0206\n#\f#\16#\u0209\13#\3#\3#\3$"+
		"\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\6%\u0230\n%\r%\16%\u0231\3%\3%\7%\u0236"+
		"\n%\f%\16%\u0239\13%\3%\3%\3%\3%\6%\u023f\n%\r%\16%\u0240\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\5%\u024f\n%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\7%\u0284\n%\f%\16"+
		"%\u0287\13%\3&\3&\3&\3&\7&\u028d\n&\f&\16&\u0290\13&\5&\u0292\n&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\5+\u02a7\n+\3"+
		"+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\7.\u02b9\n.\f.\16.\u02bc"+
		"\13.\5.\u02be\n.\3.\3.\3/\3/\5/\u02c4\n/\3/\2\4\24H\60\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\\2\16"+
		"\3\2\4\6\3\2\7\16\3\2\34\35\4\2\26\2699\3\2=@\4\2\33\33AB\3\2=>\3\2CE"+
		"\3\2FI\3\2KL\3\2TU\4\2\23\23V`\2\u0320\2^\3\2\2\2\4f\3\2\2\2\6j\3\2\2"+
		"\2\bt\3\2\2\2\nx\3\2\2\2\fz\3\2\2\2\16~\3\2\2\2\20\u0087\3\2\2\2\22\u008e"+
		"\3\2\2\2\24\u0099\3\2\2\2\26\u00a9\3\2\2\2\30\u00b9\3\2\2\2\32\u00cd\3"+
		"\2\2\2\34\u00d2\3\2\2\2\36\u00f3\3\2\2\2 \u00f5\3\2\2\2\"\u0102\3\2\2"+
		"\2$\u0105\3\2\2\2&\u010c\3\2\2\2(\u0117\3\2\2\2*\u011c\3\2\2\2,\u0142"+
		"\3\2\2\2.\u0147\3\2\2\2\60\u014d\3\2\2\2\62\u014f\3\2\2\2\64\u0152\3\2"+
		"\2\2\66\u01c6\3\2\2\28\u01c8\3\2\2\2:\u01ee\3\2\2\2<\u01f0\3\2\2\2>\u01f2"+
		"\3\2\2\2@\u01fd\3\2\2\2B\u0201\3\2\2\2D\u0207\3\2\2\2F\u020c\3\2\2\2H"+
		"\u024e\3\2\2\2J\u0288\3\2\2\2L\u0295\3\2\2\2N\u0299\3\2\2\2P\u029c\3\2"+
		"\2\2R\u029f\3\2\2\2T\u02a6\3\2\2\2V\u02ab\3\2\2\2X\u02b0\3\2\2\2Z\u02b4"+
		"\3\2\2\2\\\u02c3\3\2\2\2^c\7d\2\2_`\7\3\2\2`b\7d\2\2a_\3\2\2\2be\3\2\2"+
		"\2ca\3\2\2\2cd\3\2\2\2d\3\3\2\2\2ec\3\2\2\2fg\t\2\2\2g\5\3\2\2\2hk\t\3"+
		"\2\2ik\5\2\2\2jh\3\2\2\2ji\3\2\2\2k\7\3\2\2\2lu\5\6\4\2mp\5\6\4\2no\7"+
		"\17\2\2oq\7\20\2\2pn\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2t"+
		"l\3\2\2\2tm\3\2\2\2u\t\3\2\2\2vy\5\b\5\2wy\7\21\2\2xv\3\2\2\2xw\3\2\2"+
		"\2y\13\3\2\2\2z|\7d\2\2{}\5\22\n\2|{\3\2\2\2|}\3\2\2\2}\r\3\2\2\2~\177"+
		"\5\b\5\2\177\u0084\5\f\7\2\u0080\u0081\7\22\2\2\u0081\u0083\5\f\7\2\u0082"+
		"\u0080\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\17\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\5\b\5\2\u0088\u0089"+
		"\7d\2\2\u0089\21\3\2\2\2\u008a\u008b\7\23\2\2\u008b\u008f\5H%\2\u008c"+
		"\u008d\7\23\2\2\u008d\u008f\5Z.\2\u008e\u008a\3\2\2\2\u008e\u008c\3\2"+
		"\2\2\u008f\23\3\2\2\2\u0090\u0091\b\13\1\2\u0091\u009a\5\2\2\2\u0092\u0093"+
		"\7\24\2\2\u0093\u0094\5\24\13\2\u0094\u0095\7\25\2\2\u0095\u009a\3\2\2"+
		"\2\u0096\u0097\7\26\2\2\u0097\u0098\7\3\2\2\u0098\u009a\7d\2\2\u0099\u0090"+
		"\3\2\2\2\u0099\u0092\3\2\2\2\u0099\u0096\3\2\2\2\u009a\u00a5\3\2\2\2\u009b"+
		"\u009c\f\6\2\2\u009c\u009d\7\17\2\2\u009d\u009e\5H%\2\u009e\u009f\7\20"+
		"\2\2\u009f\u00a4\3\2\2\2\u00a0\u00a1\f\4\2\2\u00a1\u00a2\7\3\2\2\u00a2"+
		"\u00a4\7d\2\2\u00a3\u009b\3\2\2\2\u00a3\u00a0\3\2\2\2\u00a4\u00a7\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\25\3\2\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a8\u00aa\5\30\r\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2"+
		"\u00aa\u00ae\3\2\2\2\u00ab\u00ad\5\32\16\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0"+
		"\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b4\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b1\u00b3\5\34\17\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3"+
		"\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b7\u00b8\7\2\2\3\u00b8\27\3\2\2\2\u00b9\u00ba\7\27\2"+
		"\2\u00ba\u00bb\5\2\2\2\u00bb\u00bc\7\30\2\2\u00bc\31\3\2\2\2\u00bd\u00bf"+
		"\7\31\2\2\u00be\u00c0\7\32\2\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2"+
		"\u00c0\u00c1\3\2\2\2\u00c1\u00c2\5\2\2\2\u00c2\u00c3\7\30\2\2\u00c3\u00ce"+
		"\3\2\2\2\u00c4\u00c6\7\31\2\2\u00c5\u00c7\7\32\2\2\u00c6\u00c5\3\2\2\2"+
		"\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\5\2\2\2\u00c9\u00ca"+
		"\7\3\2\2\u00ca\u00cb\7\33\2\2\u00cb\u00cc\7\30\2\2\u00cc\u00ce\3\2\2\2"+
		"\u00cd\u00bd\3\2\2\2\u00cd\u00c4\3\2\2\2\u00ce\33\3\2\2\2\u00cf\u00d1"+
		"\5\36\20\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2"+
		"\u00d2\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6"+
		"\t\4\2\2\u00d6\u00e0\7d\2\2\u00d7\u00d8\7\36\2\2\u00d8\u00dd\5\2\2\2\u00d9"+
		"\u00da\7\22\2\2\u00da\u00dc\5\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00df\3"+
		"\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e1\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00d7\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00eb\3\2"+
		"\2\2\u00e2\u00e3\7\37\2\2\u00e3\u00e8\5\2\2\2\u00e4\u00e5\7\22\2\2\u00e5"+
		"\u00e7\5\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00e2\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\5 "+
		"\21\2\u00ee\35\3\2\2\2\u00ef\u00f4\5\4\3\2\u00f0\u00f4\7 \2\2\u00f1\u00f4"+
		"\7!\2\2\u00f2\u00f4\7\"\2\2\u00f3\u00ef\3\2\2\2\u00f3\u00f0\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\37\3\2\2\2\u00f5\u00fa\7#\2\2"+
		"\u00f6\u00f9\5\"\22\2\u00f7\u00f9\5$\23\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7"+
		"\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fd\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\7$\2\2\u00fe!\3\2\2\2\u00ff"+
		"\u0103\5&\24\2\u0100\u0103\5\62\32\2\u0101\u0103\5\64\33\2\u0102\u00ff"+
		"\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103#\3\2\2\2\u0104"+
		"\u0106\7\32\2\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3"+
		"\2\2\2\u0107\u0108\58\35\2\u0108%\3\2\2\2\u0109\u010b\5(\25\2\u010a\u0109"+
		"\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0110\5\16\b\2\u0110\u0111\7"+
		"\30\2\2\u0111\'\3\2\2\2\u0112\u0118\5\4\3\2\u0113\u0118\7!\2\2\u0114\u0118"+
		"\7\32\2\2\u0115\u0118\7%\2\2\u0116\u0118\7&\2\2\u0117\u0112\3\2\2\2\u0117"+
		"\u0113\3\2\2\2\u0117\u0114\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0116\3\2"+
		"\2\2\u0118)\3\2\2\2\u0119\u011b\5,\27\2\u011a\u0119\3\2\2\2\u011b\u011e"+
		"\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0120\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011f\u0121\5\n\6\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0123\7d\2\2\u0123\u012c\7\24\2\2\u0124"+
		"\u0129\5.\30\2\u0125\u0126\7\22\2\2\u0126\u0128\5.\30\2\u0127\u0125\3"+
		"\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u0124\3\2\2\2\u012c\u012d\3\2"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u0138\7\25\2\2\u012f\u0130\7\'\2\2\u0130"+
		"\u0135\5\2\2\2\u0131\u0132\7\22\2\2\u0132\u0134\5\2\2\2\u0133\u0131\3"+
		"\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u012f\3\2\2\2\u0138\u0139\3\2"+
		"\2\2\u0139+\3\2\2\2\u013a\u0143\5\4\3\2\u013b\u0143\7 \2\2\u013c\u0143"+
		"\7(\2\2\u013d\u0143\7!\2\2\u013e\u0143\7)\2\2\u013f\u0143\7\32\2\2\u0140"+
		"\u0143\7*\2\2\u0141\u0143\7+\2\2\u0142\u013a\3\2\2\2\u0142\u013b\3\2\2"+
		"\2\u0142\u013c\3\2\2\2\u0142\u013d\3\2\2\2\u0142\u013e\3\2\2\2\u0142\u013f"+
		"\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0141\3\2\2\2\u0143-\3\2\2\2\u0144"+
		"\u0146\5\60\31\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3"+
		"\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014b\5\b\5\2\u014b\u014c\7d\2\2\u014c/\3\2\2\2\u014d\u014e\7!\2\2\u014e"+
		"\61\3\2\2\2\u014f\u0150\5*\26\2\u0150\u0151\58\35\2\u0151\63\3\2\2\2\u0152"+
		"\u0153\5*\26\2\u0153\u0154\7\30\2\2\u0154\65\3\2\2\2\u0155\u01c7\58\35"+
		"\2\u0156\u01c7\7\30\2\2\u0157\u0158\5:\36\2\u0158\u0159\7\30\2\2\u0159"+
		"\u01c7\3\2\2\2\u015a\u015c\7,\2\2\u015b\u015d\5H%\2\u015c\u015b\3\2\2"+
		"\2\u015c\u015d\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u01c7\7\30\2\2\u015f"+
		"\u0160\7-\2\2\u0160\u0161\7\24\2\2\u0161\u0162\5H%\2\u0162\u0163\7\25"+
		"\2\2\u0163\u0164\5\66\34\2\u0164\u01c7\3\2\2\2\u0165\u0166\7-\2\2\u0166"+
		"\u0167\7\24\2\2\u0167\u0168\5H%\2\u0168\u0169\7\25\2\2\u0169\u016a\5\66"+
		"\34\2\u016a\u016b\7.\2\2\u016b\u016c\5\66\34\2\u016c\u01c7\3\2\2\2\u016d"+
		"\u016e\7/\2\2\u016e\u016f\7\24\2\2\u016f\u0170\5H%\2\u0170\u0171\7\25"+
		"\2\2\u0171\u0172\5\66\34\2\u0172\u01c7\3\2\2\2\u0173\u0174\7\60\2\2\u0174"+
		"\u0175\5\66\34\2\u0175\u0176\7/\2\2\u0176\u0177\7\24\2\2\u0177\u0178\5"+
		"H%\2\u0178\u0179\7\25\2\2\u0179\u017a\7\30\2\2\u017a\u01c7\3\2\2\2\u017b"+
		"\u017c\7\61\2\2\u017c\u0185\7\24\2\2\u017d\u0182\5:\36\2\u017e\u017f\7"+
		"\22\2\2\u017f\u0181\5:\36\2\u0180\u017e\3\2\2\2\u0181\u0184\3\2\2\2\u0182"+
		"\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2"+
		"\2\2\u0185\u017d\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0189\7\30\2\2\u0188\u018a\5H%\2\u0189\u0188\3\2\2\2\u0189\u018a\3\2"+
		"\2\2\u018a\u018b\3\2\2\2\u018b\u0194\7\30\2\2\u018c\u0191\5:\36\2\u018d"+
		"\u018e\7\22\2\2\u018e\u0190\5:\36\2\u018f\u018d\3\2\2\2\u0190\u0193\3"+
		"\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0195\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0194\u018c\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\3\2"+
		"\2\2\u0196\u0197\7\25\2\2\u0197\u01c7\5\66\34\2\u0198\u0199\7\61\2\2\u0199"+
		"\u019a\7\24\2\2\u019a\u019b\5D#\2\u019b\u019c\7\62\2\2\u019c\u019d\5H"+
		"%\2\u019d\u019e\7\25\2\2\u019e\u019f\5\66\34\2\u019f\u01c7\3\2\2\2\u01a0"+
		"\u01a1\7\63\2\2\u01a1\u01a5\58\35\2\u01a2\u01a4\5F$\2\u01a3\u01a2\3\2"+
		"\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6"+
		"\u01aa\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a8\u01a9\7\64\2\2\u01a9\u01ab\5"+
		"8\35\2\u01aa\u01a8\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01c7\3\2\2\2\u01ac"+
		"\u01ad\7+\2\2\u01ad\u01c7\58\35\2\u01ae\u01af\7+\2\2\u01af\u01b0\7\24"+
		"\2\2\u01b0\u01b1\5H%\2\u01b1\u01b2\7\25\2\2\u01b2\u01b3\58\35\2\u01b3"+
		"\u01c7\3\2\2\2\u01b4\u01b5\7\65\2\2\u01b5\u01c7\7\30\2\2\u01b6\u01b7\7"+
		"\66\2\2\u01b7\u01c7\7\30\2\2\u01b8\u01b9\7\67\2\2\u01b9\u01ba\7\24\2\2"+
		"\u01ba\u01bb\5H%\2\u01bb\u01bc\7\25\2\2\u01bc\u01bd\5> \2\u01bd\u01c7"+
		"\3\2\2\2\u01be\u01bf\78\2\2\u01bf\u01c2\5H%\2\u01c0\u01c1\7\62\2\2\u01c1"+
		"\u01c3\5H%\2\u01c2\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2"+
		"\2\u01c4\u01c5\7\30\2\2\u01c5\u01c7\3\2\2\2\u01c6\u0155\3\2\2\2\u01c6"+
		"\u0156\3\2\2\2\u01c6\u0157\3\2\2\2\u01c6\u015a\3\2\2\2\u01c6\u015f\3\2"+
		"\2\2\u01c6\u0165\3\2\2\2\u01c6\u016d\3\2\2\2\u01c6\u0173\3\2\2\2\u01c6"+
		"\u017b\3\2\2\2\u01c6\u0198\3\2\2\2\u01c6\u01a0\3\2\2\2\u01c6\u01ac\3\2"+
		"\2\2\u01c6\u01ae\3\2\2\2\u01c6\u01b4\3\2\2\2\u01c6\u01b6\3\2\2\2\u01c6"+
		"\u01b8\3\2\2\2\u01c6\u01be\3\2\2\2\u01c7\67\3\2\2\2\u01c8\u01cc\7#\2\2"+
		"\u01c9\u01cb\5\66\34\2\u01ca\u01c9\3\2\2\2\u01cb\u01ce\3\2\2\2\u01cc\u01ca"+
		"\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce\u01cc\3\2\2\2\u01cf"+
		"\u01d0\7$\2\2\u01d09\3\2\2\2\u01d1\u01d3\5<\37\2\u01d2\u01d1\3\2\2\2\u01d3"+
		"\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d7\3\2"+
		"\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01ef\5\16\b\2\u01d8\u01ef\5X-\2\u01d9"+
		"\u01ef\5P)\2\u01da\u01ef\5N(\2\u01db\u01ef\5R*\2\u01dc\u01ef\5T+\2\u01dd"+
		"\u01ef\5V,\2\u01de\u01df\t\5\2\2\u01df\u01e8\7\24\2\2\u01e0\u01e5\5H%"+
		"\2\u01e1\u01e2\7\22\2\2\u01e2\u01e4\5H%\2\u01e3\u01e1\3\2\2\2\u01e4\u01e7"+
		"\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7"+
		"\u01e5\3\2\2\2\u01e8\u01e0\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01ea\3\2"+
		"\2\2\u01ea\u01ef\7\25\2\2\u01eb\u01ef\5L\'\2\u01ec\u01ed\7:\2\2\u01ed"+
		"\u01ef\5H%\2\u01ee\u01d4\3\2\2\2\u01ee\u01d8\3\2\2\2\u01ee\u01d9\3\2\2"+
		"\2\u01ee\u01da\3\2\2\2\u01ee\u01db\3\2\2\2\u01ee\u01dc\3\2\2\2\u01ee\u01dd"+
		"\3\2\2\2\u01ee\u01de\3\2\2\2\u01ee\u01eb\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ef"+
		";\3\2\2\2\u01f0\u01f1\7!\2\2\u01f1=\3\2\2\2\u01f2\u01f8\7#\2\2\u01f3\u01f7"+
		"\5\66\34\2\u01f4\u01f7\5@!\2\u01f5\u01f7\5B\"\2\u01f6\u01f3\3\2\2\2\u01f6"+
		"\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2"+
		"\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fb\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb"+
		"\u01fc\7$\2\2\u01fc?\3\2\2\2\u01fd\u01fe\7;\2\2\u01fe\u01ff\5H%\2\u01ff"+
		"\u0200\7\62\2\2\u0200A\3\2\2\2\u0201\u0202\7(\2\2\u0202\u0203\7\62\2\2"+
		"\u0203C\3\2\2\2\u0204\u0206\5<\37\2\u0205\u0204\3\2\2\2\u0206\u0209\3"+
		"\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u020a\3\2\2\2\u0209"+
		"\u0207\3\2\2\2\u020a\u020b\5\20\t\2\u020bE\3\2\2\2\u020c\u020d\7<\2\2"+
		"\u020d\u020e\7\24\2\2\u020e\u020f\5\2\2\2\u020f\u0210\7d\2\2\u0210\u0211"+
		"\7\25\2\2\u0211\u0212\58\35\2\u0212G\3\2\2\2\u0213\u0214\b%\1\2\u0214"+
		"\u024f\7e\2\2\u0215\u024f\7f\2\2\u0216\u024f\7h\2\2\u0217\u024f\7g\2\2"+
		"\u0218\u024f\7b\2\2\u0219\u024f\7i\2\2\u021a\u024f\7j\2\2\u021b\u024f"+
		"\7c\2\2\u021c\u024f\5T+\2\u021d\u024f\5R*\2\u021e\u021f\5\2\2\2\u021f"+
		"\u0220\7\3\2\2\u0220\u0221\7\34\2\2\u0221\u024f\3\2\2\2\u0222\u024f\5"+
		"\2\2\2\u0223\u024f\5N(\2\u0224\u024f\5P)\2\u0225\u0226\t\6\2\2\u0226\u024f"+
		"\5H%\26\u0227\u024f\5X-\2\u0228\u024f\5L\'\2\u0229\u022a\7S\2\2\u022a"+
		"\u022f\5\6\4\2\u022b\u022c\7\17\2\2\u022c\u022d\5H%\2\u022d\u022e\7\20"+
		"\2\2\u022e\u0230\3\2\2\2\u022f\u022b\3\2\2\2\u0230\u0231\3\2\2\2\u0231"+
		"\u022f\3\2\2\2\u0231\u0232\3\2\2\2\u0232\u0237\3\2\2\2\u0233\u0234\7\17"+
		"\2\2\u0234\u0236\7\20\2\2\u0235\u0233\3\2\2\2\u0236\u0239\3\2\2\2\u0237"+
		"\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u024f\3\2\2\2\u0239\u0237\3\2"+
		"\2\2\u023a\u023b\7S\2\2\u023b\u023e\5\6\4\2\u023c\u023d\7\17\2\2\u023d"+
		"\u023f\7\20\2\2\u023e\u023c\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u023e\3"+
		"\2\2\2\u0240\u0241\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0243\5Z.\2\u0243"+
		"\u024f\3\2\2\2\u0244\u0245\7\24\2\2\u0245\u0246\5\b\5\2\u0246\u0247\7"+
		"\25\2\2\u0247\u0248\5H%\5\u0248\u024f\3\2\2\2\u0249\u024f\7\26\2\2\u024a"+
		"\u024b\7\24\2\2\u024b\u024c\5H%\2\u024c\u024d\7\25\2\2\u024d\u024f\3\2"+
		"\2\2\u024e\u0213\3\2\2\2\u024e\u0215\3\2\2\2\u024e\u0216\3\2\2\2\u024e"+
		"\u0217\3\2\2\2\u024e\u0218\3\2\2\2\u024e\u0219\3\2\2\2\u024e\u021a\3\2"+
		"\2\2\u024e\u021b\3\2\2\2\u024e\u021c\3\2\2\2\u024e\u021d\3\2\2\2\u024e"+
		"\u021e\3\2\2\2\u024e\u0222\3\2\2\2\u024e\u0223\3\2\2\2\u024e\u0224\3\2"+
		"\2\2\u024e\u0225\3\2\2\2\u024e\u0227\3\2\2\2\u024e\u0228\3\2\2\2\u024e"+
		"\u0229\3\2\2\2\u024e\u023a\3\2\2\2\u024e\u0244\3\2\2\2\u024e\u0249\3\2"+
		"\2\2\u024e\u024a\3\2\2\2\u024f\u0285\3\2\2\2\u0250\u0251\f\25\2\2\u0251"+
		"\u0252\t\7\2\2\u0252\u0284\5H%\26\u0253\u0254\f\24\2\2\u0254\u0255\t\b"+
		"\2\2\u0255\u0284\5H%\25\u0256\u0257\f\23\2\2\u0257\u0258\t\t\2\2\u0258"+
		"\u0284\5H%\24\u0259\u025a\f\22\2\2\u025a\u025b\t\n\2\2\u025b\u0284\5H"+
		"%\23\u025c\u025d\f\20\2\2\u025d\u025e\t\13\2\2\u025e\u0284\5H%\21\u025f"+
		"\u0260\f\17\2\2\u0260\u0261\7M\2\2\u0261\u0284\5H%\20\u0262\u0263\f\16"+
		"\2\2\u0263\u0264\7N\2\2\u0264\u0284\5H%\17\u0265\u0266\f\r\2\2\u0266\u0267"+
		"\7O\2\2\u0267\u0284\5H%\16\u0268\u0269\f\f\2\2\u0269\u026a\7P\2\2\u026a"+
		"\u0284\5H%\r\u026b\u026c\f\13\2\2\u026c\u026d\7Q\2\2\u026d\u0284\5H%\f"+
		"\u026e\u026f\f\n\2\2\u026f\u0270\7R\2\2\u0270\u0271\5H%\2\u0271\u0272"+
		"\7\62\2\2\u0272\u0273\5H%\n\u0273\u0284\3\2\2\2\u0274\u0275\f\33\2\2\u0275"+
		"\u0276\7\3\2\2\u0276\u0284\7d\2\2\u0277\u0278\f\32\2\2\u0278\u0279\7\3"+
		"\2\2\u0279\u027a\7d\2\2\u027a\u0284\5J&\2\u027b\u027c\f\31\2\2\u027c\u027d"+
		"\7\17\2\2\u027d\u027e\5H%\2\u027e\u027f\7\20\2\2\u027f\u0284\3\2\2\2\u0280"+
		"\u0281\f\21\2\2\u0281\u0282\7J\2\2\u0282\u0284\5\b\5\2\u0283\u0250\3\2"+
		"\2\2\u0283\u0253\3\2\2\2\u0283\u0256\3\2\2\2\u0283\u0259\3\2\2\2\u0283"+
		"\u025c\3\2\2\2\u0283\u025f\3\2\2\2\u0283\u0262\3\2\2\2\u0283\u0265\3\2"+
		"\2\2\u0283\u0268\3\2\2\2\u0283\u026b\3\2\2\2\u0283\u026e\3\2\2\2\u0283"+
		"\u0274\3\2\2\2\u0283\u0277\3\2\2\2\u0283\u027b\3\2\2\2\u0283\u0280\3\2"+
		"\2\2\u0284\u0287\3\2\2\2\u0285\u0283\3\2\2\2\u0285\u0286\3\2\2\2\u0286"+
		"I\3\2\2\2\u0287\u0285\3\2\2\2\u0288\u0291\7\24\2\2\u0289\u028e\5H%\2\u028a"+
		"\u028b\7\22\2\2\u028b\u028d\5H%\2\u028c\u028a\3\2\2\2\u028d\u0290\3\2"+
		"\2\2\u028e\u028c\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0292\3\2\2\2\u0290"+
		"\u028e\3\2\2\2\u0291\u0289\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0293\3\2"+
		"\2\2\u0293\u0294\7\25\2\2\u0294K\3\2\2\2\u0295\u0296\7S\2\2\u0296\u0297"+
		"\5\2\2\2\u0297\u0298\5J&\2\u0298M\3\2\2\2\u0299\u029a\5\24\13\2\u029a"+
		"\u029b\t\f\2\2\u029bO\3\2\2\2\u029c\u029d\t\f\2\2\u029d\u029e\5\24\13"+
		"\2\u029eQ\3\2\2\2\u029f\u02a0\79\2\2\u02a0\u02a1\7\3\2\2\u02a1\u02a2\7"+
		"d\2\2\u02a2\u02a3\5J&\2\u02a3S\3\2\2\2\u02a4\u02a5\7\26\2\2\u02a5\u02a7"+
		"\7\3\2\2\u02a6\u02a4\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8"+
		"\u02a9\7d\2\2\u02a9\u02aa\5J&\2\u02aaU\3\2\2\2\u02ab\u02ac\5H%\2\u02ac"+
		"\u02ad\7\3\2\2\u02ad\u02ae\7d\2\2\u02ae\u02af\5J&\2\u02afW\3\2\2\2\u02b0"+
		"\u02b1\5\24\13\2\u02b1\u02b2\t\r\2\2\u02b2\u02b3\5H%\2\u02b3Y\3\2\2\2"+
		"\u02b4\u02bd\7#\2\2\u02b5\u02ba\5\\/\2\u02b6\u02b7\7\22\2\2\u02b7\u02b9"+
		"\5\\/\2\u02b8\u02b6\3\2\2\2\u02b9\u02bc\3\2\2\2\u02ba\u02b8\3\2\2\2\u02ba"+
		"\u02bb\3\2\2\2\u02bb\u02be\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bd\u02b5\3\2"+
		"\2\2\u02bd\u02be\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02c0\7$\2\2\u02c0"+
		"[\3\2\2\2\u02c1\u02c4\5H%\2\u02c2\u02c4\5Z.\2\u02c3\u02c1\3\2\2\2\u02c3"+
		"\u02c2\3\2\2\2\u02c4]\3\2\2\2Ecjrtx|\u0084\u008e\u0099\u00a3\u00a5\u00a9"+
		"\u00ae\u00b4\u00bf\u00c6\u00cd\u00d2\u00dd\u00e0\u00e8\u00eb\u00f3\u00f8"+
		"\u00fa\u0102\u0105\u010c\u0117\u011c\u0120\u0129\u012c\u0135\u0138\u0142"+
		"\u0147\u015c\u0182\u0185\u0189\u0191\u0194\u01a5\u01aa\u01c2\u01c6\u01cc"+
		"\u01d4\u01e5\u01e8\u01ee\u01f6\u01f8\u0207\u0231\u0237\u0240\u024e\u0283"+
		"\u0285\u028e\u0291\u02a6\u02ba\u02bd\u02c3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}