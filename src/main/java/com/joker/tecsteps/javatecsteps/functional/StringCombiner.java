package com.joker.tecsteps.javatecsteps.functional;

/**
 * Created by joker on 17-9-24.
 */
public class StringCombiner {
    private String delim;
    private String prefix;
    private String suffix;
    private StringBuilder builder;
    public StringCombiner(String delim, String prefix, String suffix){
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
        this.builder = new StringBuilder();
    }

    public StringBuilder getBuilder(){
        return this.builder;
    }

    public StringCombiner add(String element){
        if(builder.length() == 0){
            builder.append(prefix);
        }else{
            builder.append(delim);
        }
        builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other){
        builder.append(other.getBuilder());
        return this;
    }

    @Override
    public String toString() {
        int initialLength = builder.length();
        String result = builder.append(suffix).toString();
        // reset value to pre-append initialLength
        builder.setLength(initialLength);
        return result;
    }
}
