package string.manipulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringSplit {
	
	public static void split(String s, char c) {
		List<String> splitStrings = new ArrayList<>();
		StringBuilder splitStrBuilder = new StringBuilder();
		for (int i = 0 ; i < s.length() ; i++) {
			if (s.charAt(i) == c) {
				splitStrings.add(splitStrBuilder.toString());	
				splitStrBuilder = new StringBuilder();
			} else {
				splitStrBuilder.append(s.charAt(i));
			}
		}
		
		splitStrings.add(splitStrBuilder.toString());
		
		System.out.println("==Input==");
		System.out.println(String.format("\"%s\", Splitchar:\"%c\"", s,c));
		
		System.out.println("==Output==");
		Iterator<String> splitStringsIter = splitStrings.iterator();
		while (splitStringsIter.hasNext()) {
			String str = splitStringsIter.next();
			System.out.print(String.format("\"%s\"%s", str, 
					splitStringsIter.hasNext() ? "," : ""));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String ex1 = "split by space";
		char splitCharEx1 = ' ';
		split(ex1, splitCharEx1);
		
		String ex2 = "beekeeper needed";
		char splitCharEx2 = 'e';
		split(ex2, splitCharEx2);
		
		String ex3 = "/home/./..//Documents/";
		char splitCharEx3 = '/';
		split(ex3, splitCharEx3);
		
		String ex4 = "";
		char splitCharEx4 = '?';
		split(ex4, splitCharEx4);
	}
}