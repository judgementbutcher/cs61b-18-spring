public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    private boolean isPalindrome(Deque<Character> ans){
        if (ans.size() <= 1){
            return true;
        }
        if(ans.removeFirst() != ans.removeLast()){
            return false;
        }
        else return isPalindrome(ans);
    }
    public boolean isPalindrome(String word) {
        Deque<Character> res = wordToDeque(word);
        return isPalindrome(res);
    }

    private boolean isPalindrome(Deque<Character> ans, CharacterComparator cc){
        if (ans.size() <= 1){
            return true;
        }
        if(!cc.equalChars(ans.removeFirst(),ans.removeLast())){
            return false;
        }
        else return isPalindrome(ans,cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> res = wordToDeque(word);
        return isPalindrome(res,cc);
    }
}
