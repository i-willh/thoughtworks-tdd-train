import org.fest.assertions.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by weihuang on 16/03/2017.
 */
public class WordTest {

    /**
     * test with none word
     */
    @Test
    public void TestWordsWithNoneWords() {
        String path = "/Users/weihuang/Downloads/ut-workshop/word.txt";
        String word = new ComputeWord().compute(path);
        Assertions.assertThat(word).isEqualTo("");

    }

    /**
     * test with only one word
     */
    @Test
    public void TestWordsWithOnlyOneWord() {
        String path = "/Users/weihuang/Downloads/ut-workshop/word1.txt";
        String word = new ComputeWord().compute(path);
        System.out.println(word);
        Assertions.assertThat(word).isEqualTo("a 1");

    }

    /**
     * test with more word
     */
    @Test
    public void TestWordsWithMoreWord() {
        String path = "/Users/weihuang/Downloads/ut-workshop/word2.txt";
        String word = new ComputeWord().compute(path);
        System.out.println(word);
        Assertions.assertThat(word).isEqualTo("a 1\nb 1\nc 1\nd 1\ne 1\nf 1\ng 1");
    }

}
