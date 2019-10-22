/** This class outputs all palindromes in the words file in the current directory. */
 public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        String max = "";
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();

        OffByN offByTree = new OffByN(3);
        for (int i = 0; i < 26; i++){
            OffByN offByI = new OffByN(i);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (word.length() >= minLength && palindrome.isPalindrome(word, offByI)) {
                    if (max.length() < word.length()) {
                        max = word;
                    }
                }
            }
        }
        System.out.println(max);
    }
}