package com.github.rafaelsilvestri.subsequence;

import java.util.ArrayList;
import java.util.List;

public class SequenceGenerator {

    private List<String> sequences;

    public int longest(String str1, String str2) {
        List<String> sequence1 = findSequences(str1);
        List<String> sequence2 = findSequences(str2);
        int longest = 0;
        for (String s : sequence1) {
            if (sequence2.contains(s)) {
                if (longest < s.length()) {
                    longest = s.length();
                }
            }
        }

        return longest;
    }

    public List<String> findSequences(String str) {
        sequences = new ArrayList<>();

        // find recursively
        findNext(str, 0, "");
        //findNext2(str, "");
        // baby steps
        return sequences;
    }

    /**
     * approach one.
     */
    private void findNext(String str, int pos, String result) {
        if (pos == str.length()) {
            sequences.add(result);
            //return;
        } else {
            findNext(str, pos + 1, result + str.charAt(pos));
            findNext(str, pos + 1, result);
        }
    }

    /**
     * approach two.
     */
    private void findNext2(String str, String result) {
        if (str.length() == 0) {
            sequences.add(result);
            //return;
        } else {
            findNext2(str.substring(1), result + str.charAt(0));
            findNext2(str.substring(1), result);
        }
    }
}
