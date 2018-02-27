import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;



public class wordNode{

        public static String currentWord;
        public static int currCharInd;
        public Queue<String> wordQueue = new LinkedList<String>();
        public static ArrayList<String> vistedWord=new ArrayList<>();
        private static Set<String> dictionary;

        public wordNode(){}
        public wordNode(String start, String end, int charOffset, Set<String> dictionary){

        //Find all the words in the dicitonary  one letter away
        //Don't forget to avoid repeating words --> vistedWord
                //System.out.println(currCharInd);
                this.currentWord = start;
                this.dictionary = dictionary;
                this.currCharInd = charOffset;
                //System.out.println(currCharInd);
                System.out.println("Beginning Word is: " + start);
                findNextWord(start, end);
                System.out.println("End of this Loop \n");
        }

        private void findNextWord(String word, String end){
                if(word.equals(end)){
                    return;
                }
                int charNum = currCharInd;
                while(charNum<word.length()) {
                    StringBuilder tmp = new StringBuilder(word);
                    for (int i = 0; i < 25; i = i + 1) {
                        tmp = changeLetter(tmp, charNum);
                        String tmp2 = tmp.toString();
                        tmp2 = tmp2.toUpperCase();
                        if (dictionary.contains(tmp2)) {
                            //System.out.println("Positive Dictionary Match");
                            if(tmp2.equals(end)){
                                System.out.println("Made it to the end.");
                                currentWord = end;
                                return;
                            }
                            wordQueue.add(tmp2.toLowerCase());
                        }
                    }
                    charNum = charNum + 1;
                }
                System.out.println(wordQueue);
        }

        public static StringBuilder changeLetter(StringBuilder word, int charNum){
                char ch = word.charAt(charNum);
                int letter = ch;
                letter = letter - 97;
                letter = ((letter + 1) % 26) + 97;
                word.setCharAt(charNum,(char) letter);
                //System.out.println(word);

                return word;
        }
}