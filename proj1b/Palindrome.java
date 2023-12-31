public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        word = word.toLowerCase();
        int len = word.length();
        for (int i = 0; i < len; i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        word = word.toLowerCase();
        int len = word.length();
        if (word == null || len <= 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(i) != word.charAt(len - i - 1)) {
                return false;
            }

        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        word = word.toLowerCase();
        int len = word.length();
        if (word == null || len <= 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
