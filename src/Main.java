import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        /**
         * @문제설명
         * 두 개의 단어 begin, target과 단어의 집합 words가 있습니다.
         * 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
         * 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
         * 2. words에 있는 단어로만 변환할 수 있습니다.
         * 예를 들어 begin이 hit, target가 cog, words가 [hot,dot,dog,lot,log,cog]라면
         * hit -> hot -> dot -> dog -> cog와 같이 4단계를 거쳐 변환할 수 있습니다.
         * 두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때,
         * 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
         * @제한사항
         * 각 단어는 알파벳 소문자로만 이루어져 있습니다.
         * 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
         * words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
         * begin과 target은 같지 않습니다.
         * 변환할 수 없는 경우에는 0를 return 합니다.
         * @입출력예
         * begin    target	words	return
         * hit	cog	[hot, dot, dog, lot, log, cog]	4
         * hit	cog	[hot, dot, dog, lot, log]	0
         * -------------------------------------------------------------------------
         * hit -> (hot) -> (dot), (lot) -> (dog, lot), (log)  -> (cog, log), (cog ....)
         * 너비 우선 탐색을 시도하여 target 값이 나오는 경우에 단계 값 출력
         * -------------------------------------------------------------------------
         */
        String begin = "hit";
        String target = "cog";
//        String[] words = {"cog", "hot", "dot", "dog", "lot", "log"};
//        String[] words = {"hot", "dot", "dog", "lot", "log", "loc", "hoc", "hod", "cog"};
        String[] words = {"hot", "dot", "dog", "lot", "log"};
        Solution solution = new Solution();
        System.out.printf("%d", solution.solution(begin, target, words));
    }
}


class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }

    class Node {
        private String word;
        private int count;

        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    private int bfs(String begin, String target, String[] words) {
        Queue<Node> queue = new LinkedList<>();
        int count = 0;
        boolean[] checked = new boolean[words.length];
        queue.add(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.word.equals(target)) return curNode.count;

            for (int i = 0; i < words.length; i++) {
                if (!checked[i] && isNextWord(curNode.word, words[i])) {
                    checked[i] = true;
                    queue.add(new Node(words[i], curNode.count + 1));
                }
            }
        }

        return count;
    }

    private boolean isNextWord(String node, String word) {
        return getDiffCharNum(node, word) == 1;
    }

    private int getDiffCharNum(String src, String des) {
        int count = 0;
        for (int i = 0; i < src.length(); i++) {
            char s1 = src.charAt(i);
            char s2 = des.charAt(i);
            if(s1 != s2) count++;
        }
        return count;
    }
}