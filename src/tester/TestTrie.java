package tester;

import java.io.*;
import java.util.*;
import trees.Trie;

public class TestTrie {
    public static void main(String[] args) throws FileNotFoundException {
        Trie dictionary = new Trie();

        // source: https://github.com/dwyl/english-words, contains 370,104 words
        dictionary.loadWordsFromFile("datasets/words_alpha.txt");

        ArrayList<String> words = new ArrayList<>();
        dictionary.wordsHavingPrefix("superab",words);

        for(String s : words )
            System.out.print(s + " ");

        System.out.println( "\n" + dictionary.isPresent("abdomen") );
        System.out.println( "Number of words in the trie: " + dictionary.numberOfWords() );

        dictionary.removeWord("abdomen");
        System.out.println( "Number of words in the trie: " + dictionary.numberOfWords() );

        System.out.println( dictionary.isPresent("abdomen"));

        System.out.println(dictionary.numberOfNodes());

    }
}
