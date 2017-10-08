package com.joker.tecsteps.javatecsteps.functional;

import com.joker.tecsteps.javatecsteps.functional.model.Artist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaUtil {
    public static void lambdaFormat() {
        Runnable runnable = () -> System.out.print("hello world");
        Runnable multi = () -> {
            System.out.print("hello");
            System.out.print("world");
        };
        BinaryOperator<Long> add = (x, y) -> x + y;
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x+ y;

    }

    public static void main(String args[]){
        CompletableFuture
        Map<String, Integer> map = new HashMap<>();
        List<Artist> strings = new ArrayList<>();
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        strings.add(new Artist("jimmy"));
        System.out.print(strings.stream().
            map(artist -> artist.getName()).
                collect(new StringCollector(",", "[", "]")));
    }

    public List<String> findHeading(Reader input){
        try (BufferedReader reader = new BufferedReader(input)) {
            return reader.lines()
                    .filter(line -> line.startsWith(":"))
                    .map(line -> line.substring(0, line.length() - 1))
                    .collect(Collectors.toList());

        }catch (IOException e){
            throw new HeadingLookUpException(e);
        }
    }

    public <T> T withLinesOf(Reader input, Function<Stream<String>, T> handler,
        Function<IOException, RuntimeException> error){
        try (BufferedReader reader = new BufferedReader(input)){
            return handler.apply(reader.lines());
        }catch (IOException e){
            throw error.apply(e);
        }

    }
    public List<String> findHeading(Reader input){
        return withLinesOf(input,
                lines -> lines.filter(line -> line.startsWith(":"))
                                .map(line -> line.substring(0, line.length() -1))
                                .collect(Collectors.toList()),
                HeadingLookUpException::new);
    }

}