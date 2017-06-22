package compiler;
import compiler.parser.*;
import compiler.lexer.*;
import compiler.node.*;

import java.io.*;
import java.util.Scanner;
import java.util.*;


public class Main
{
	public static void main(String[] arguments) throws FileNotFoundException
	{
		//File file = new File(arguments[0]);
		//File file = new File("insert pathname");
		try
		{
			Parser p =
					new Parser(
							new Lexer(
									new PushbackReader(
											new InputStreamReader(new FileInputStream(arguments[0])), 1024)));
		Start tree = p.parse();
		tree.apply(new PrinterAST());
		}
		catch (LexerException e) {
			System.err.printf("Lexing error: %s\n", e.getMessage());
		} catch (IOException e) {
			System.err.printf("I/O error: %s\n", e.getMessage());
			e.printStackTrace();
		} catch (ParserException e) {
			System.err.printf("Parsing error: %s\n", e.getMessage());
		}
	}
}