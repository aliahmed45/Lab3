/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2017
 */


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.

	public static ArrayList<String> input = new ArrayList<>();
	public static Set<String> dictionary;
	public static ArrayList<String> ladderBFS = new ArrayList<>();
	public static ArrayList<String> ladderDFS = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		parse(kb);
		String start = input.get(0);
		String end   = input.get(1);
		initialize();
		// TODO methods to read in words, output ladder
		getWordLadderBFS(start, end);
		getWordLadderDFS(start, end);
		printLadder(ladderBFS);
		printLadder(ladderDFS);
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dictionary = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		input.add(keyboard.next());
		while (!input.contains("/quit")) {
			input.add(keyboard.next());
			//System.out.println(input);
		}
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// If ladder is empty, return list with just start and end.
		DFSTree dfsTree = new DFSTree(start, end, dictionary);
		ladderDFS = dfsTree.startTree(start);
		return ladderDFS; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		int flag = 0;
		dictionary.remove(start.toUpperCase());
		ArrayList<wordNode> wordTree = new ArrayList<>();
		wordNode first = new wordNode(start, end, dictionary);

		if(first.wordQueue.isEmpty()){
			ladderBFS.add((start));
			ladderBFS.add((end));
			return ladderBFS;
		}
		while (!first.wordQueue.isEmpty()) {
				wordNode tmp = new wordNode(first.wordQueue.poll(), end, dictionary);
				tmp.parentNode = first;
				wordTree.add(tmp);
		}

        /* Beginning of BFS tree */
		int i = 0;
		while(!wordTree.get(i).wordQueue.isEmpty() || !wordTree.isEmpty()){
			if(wordTree.get(i).wordQueue.isEmpty()){
				i = i + 1;
				if(i == wordTree.size()){
					break;
				}
			}else {
				wordNode tmp = new wordNode(wordTree.get(i).wordQueue.poll(), end, dictionary);
				tmp.parentNode = wordTree.get(i);
				wordTree.add(tmp);
				if(tmp.flag){
					ladderBFS.add(end);
					flag = 1;
					break;
				}
			}
		}
		if(flag == 1){
		wordNode wordInLadder = wordTree.get(i);
		while(wordInLadder.parentNode != first){
			ladderBFS.add(wordInLadder.currentWord);
			wordInLadder = wordTree.get(wordTree.indexOf(wordInLadder.parentNode));
		}
		ladderBFS.add(wordInLadder.currentWord);
		ladderBFS.add(start);
		Collections.reverse(ladderBFS);
		dictionary.add(start.toUpperCase());
		}else {
			ladderBFS.add(start);
			ladderBFS.add(end);
			//Collections.reverse(ladderBFS);
		}
		return ladderBFS; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		if(ladder.size()>2){
			System.out.printf("a %d-rung word ladder exists between smart and money.\n", ladder.size());
			for(int i = 0; i < ladder.size(); i = i + 1){
				System.out.println(ladder.get(i));
			}
		}else{
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		}
	}
	// TODO
	// Other private static methods here


	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("/Users/Ali/Desktop/UT Austin/EE 442C/Lab3/src/five_letter_words.txt")); //short_dict
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
