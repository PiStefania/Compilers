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
		try
		{
			Parser p =
					new Parser(
							new Lexer(
									new PushbackReader(
											new InputStreamReader(new FileInputStream("D:/σχολη/6o εξαμηνο/Μεταγλωττιστες/εργασια/examples/test.grace")/*System.in*/), 1024)));
		// Parse the input.
		Start tree = p.parse();
		tree.apply(new Printer());
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