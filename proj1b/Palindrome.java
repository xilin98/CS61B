public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> result = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> result = this.wordToDeque(word);
        for (int i = 0; i < word.length()/2; i++) {
            if (result.removeFirst() != result.removeLast()) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> result = this.wordToDeque(word);
        for (int i = 0; i < word.length()/2; i++){
            if (!cc.equalChars(result.removeFirst(), result.removeLast())){
                return false;
            }
        }
        return true;
    }
}
