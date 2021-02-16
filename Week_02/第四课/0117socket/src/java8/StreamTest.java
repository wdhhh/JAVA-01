package java8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        //数组
        String[] arr1 = {"a","b","c","d","e","f"};
        Stream<String> stream1 = Stream.of(arr1);
        String[] arr2 = {"h","i","j","k","l","m","n"};
        Stream<String> stream2 = Arrays.stream(arr2);

        // 使用collections
        List<String> list = new ArrayList<String>();
        Arrays.asList(arr1);
        Stream<String> streamByList = list.stream();

        //使用Stream.generate()
        Stream<String> streamByGenerate = Stream.generate(()->"love").limit(10);
        String[] arr3 = streamByGenerate.toArray(String[]::new);
        System.out.println(Arrays.toString(arr3));

        //使用Stream.iterate()
        Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE)).limit(10);
        BigInteger[] arr4 = bigIntegerStream.toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(arr4));

        //使用流行的APIs，如Pattern.compile().splitAsStream()
        String str = "a b c d e f g h";
        Stream<String> streamByPattern = Pattern.compile("\\W").splitAsStream(str);
        String[] arr5 = streamByPattern.toArray(String[]::new);
        System.out.println(Arrays.toString(arr5));

    }
}
