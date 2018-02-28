package assignment3;

import java.util.*;

public class wordNode{

        public String currentWord;
        public wordNode parentNode;
        public Queue<String> wordQueue = new LinkedList<String>();
        public static ArrayList<String> visitedWord=new ArrayList<>();
        public boolean flag = false;
        private static Set<String> dictionary;

        public wordNode(String start, String end, Set<String> dictionary){
                this.currentWord = start;
                this.dictionary = dictionary;
                boolean endFound = findNextWord(start, end);
                if(endFound){
                    this.flag = true;
                }
        }

        public boolean findNextWord(String word, String end){
                if(word.equals(end)){
                    return true;
                }
                int charNum = 0;
                while(charNum<word.length()) {
                    StringBuilder tmp = new StringBuilder(word);
                    for (int i = 0; i < 25; i = i + 1) {
                        tmp = changeLetter(tmp, charNum);
                        String tmp2 = tmp.toString();
                        tmp2 = tmp2.toUpperCase();
                        if (dictionary.contains(tmp2)) {
                            if(!visitedWord.contains(tmp2)) {
                                visitedWord.add(tmp2);
                                wordQueue.add(tmp2.toLowerCase());
                            }
                            if(tmp2.equals(end)){
                                currentWord = end;
                                return false;
                            }
                        }
                    }
                    charNum = charNum + 1;
                }
                return false;
        }

        public static StringBuilder changeLetter(StringBuilder word, int charNum){
                char ch = word.charAt(charNum);
                int letter = ch;
                letter = letter - 97;
                letter = ((letter + 1) % 26) + 97;
                word.setCharAt(charNum,(char) letter);
                return word;
        }
}