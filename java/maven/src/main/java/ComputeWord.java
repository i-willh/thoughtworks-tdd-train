import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by weihuang on 16/03/2017.
 */
public class ComputeWord {

    private String readFile(String path) {
        FileInputStream fileInputStream = null;
        FileChannel channel = null;
        String text = "";
        try {
            fileInputStream = new FileInputStream(path);
            channel = fileInputStream.getChannel();
            //文件内容的大小
            try {
                int size=(int) channel.size();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                channel.read(byteBuffer);
                Buffer buffer = byteBuffer.flip();
                text = new String(byteBuffer.array(), 0, size);
                byteBuffer.clear();
                byteBuffer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return text.replaceAll("\\n+", "");
    }

    public String compute(String path) {
        String text = readFile(path);
        if("".equals(text)) {
            return "";
        }
        List<String> wordList = analyzeWords(text);
        Map<String, Integer> map = countWords(wordList);
        map = sortWordsAsCount(map);
        return print(map);
    }

    private List<String> analyzeWords(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    private Map<String, Integer> countWords(List<String> wordList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String word : wordList) {
            Integer count = map.get(word);
            if(count == null) {
                map.put(word, 1);
            } else {
                map.put(word, ++count);
            }
        }
        return map;
    }

    private Map<String, Integer> sortWordsAsCount(Map<String, Integer> map) {
        Map<String, Integer> sortWords = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(Map.Entry<String, Integer> entry : list) {
            sortWords.put(entry.getKey(), entry.getValue());
        }
        return sortWords;
    }

    private String print(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(Map.Entry<String, Integer> entry : set) {
            String word = entry.getKey();
            Integer count = entry.getValue();
            sb.append(word + " " + count);

            if(index < set.size() - 1) {
                sb.append("\n");
            }
            index++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("b", 5);
        map.put("d", 2);

        System.out.println(map);
    }
}
