package class08;

import java.util.HashMap;
import java.util.Map;

//前缀树
public class Code01_TrieTree {

    private static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    private static class TrieTree1 {
        private Node1 root;

        public TrieTree1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] chars = word.toCharArray();
            root.pass++;
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node1();
                }
                cur = cur.nexts[index];
                cur.pass++;
            }
            cur.end++;
        }

        public void delete(String word) {
            if (word == null || search(word) == 0) return;
            char[] chars = word.toCharArray();
            Node1 cur = root;
            cur.pass--;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--cur.nexts[index].pass == 0) {
                    cur.nexts[index] = null;
                    return;
                }
                cur = cur.nexts[index];
            }
            cur.end--;
        }

        public int search(String word) {
            if (word == null) return 0;
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    return 0;
                }
                cur = cur.nexts[index];
            }
            return cur.end;
        }

        public int prefixNumber(String prefix) {
            if (prefix == null) return 0;
            char[] chars = prefix.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.nexts[index].pass == 0) return 0;
                cur = cur.nexts[index];
            }
            return cur.pass;
        }
    }


    private static class Node2 {
        public int pass;
        public int end;
        public Map<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class TrieTree2 {

        private Node2 root;

        public TrieTree2() {
            this.root = new Node2();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] chars = word.toCharArray();
            Node2 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (!cur.nexts.containsKey(path)) {
                    cur.nexts.put(path, new Node2());
                }
                cur = cur.nexts.get(path);
                cur.pass++;
            }
            cur.end++;
        }

        public void delete(String word) {
            if (word == null || search(word) == 0) return;
            char[] chars = word.toCharArray();
            Node2 cur = root;
            cur.pass--;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (--cur.nexts.get(path).pass == 0) {
                    cur.nexts.remove(path);
                    return;
                }
                cur = cur.nexts.get(path);
            }
            cur.end--;
        }

        public int search(String word) {
            if (word == null) return 0;
            char[] chars = word.toCharArray();
            Node2 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (!cur.nexts.containsKey(path)) return 0;
                cur = cur.nexts.get(path);
            }
            return cur.end;
        }

        public int prefixNumber(String prefix) {
            if (prefix == null) return 0;
            char[] chars = prefix.toCharArray();
            Node2 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (!cur.nexts.containsKey(path)) return 0;
                cur = cur.nexts.get(path);
            }
            return cur.pass;
        }
    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree1 trie1 = new TrieTree1();
            TrieTree2 trie2 = new TrieTree2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
