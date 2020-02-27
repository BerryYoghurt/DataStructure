package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws Exception{
		Hangman h = new Hangman();
		Scanner s = new Scanner(System.in);
		String in = "";
		try {
			h.setDictionary(readArray());
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			s.close();
			return;
		}
		do {
		System.out.println("Guess a letter:");
		if(s.hasNext()) {
			in = s.next();
			in = h.guess(in.charAt(0));
			System.out.println(in);
		}
		}while(in.contains("-"));
		System.out.println("You win");
		s.close();
	}
	
	private static String[] readArray() throws FileNotFoundException{
		Hangman h = new Hangman();
		URL u = h.getClass().getResource("dict.txt");
		Scanner s = new Scanner(new File(u.getPath()));
		
		List<String> w = new ArrayList<String>();
		String[] a = {};
		while(s.hasNext()) {
			w.add(s.nextLine());
		}
		s.close();
		if(w.size()!=0) return w.toArray(a);
		else return null;
	}
}
