package test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class DictionaryManager {
    private final HashMap<String,Dictionary> Books = new HashMap<>();
    private static DictionaryManager singletone = null;

    private  boolean challengequery (String method, String...args){
        boolean word_exist= false ;
        String searchword = args[args.length -1];
        Dictionary dict;
        for (int i =0 ; i< args.length -1; i ++){
            if (!(Books.containsKey(args[i]))){
                dict = new Dictionary(args[i]);
                Books.put(args[i], dict);
            }
            else {
                dict = Books.get(args[i]);
            }
            if (method.equals("query") ) {
                if (dict.query(searchword))
                    word_exist = true;
            }
            else {
                if( dict.challenge(searchword))
                    word_exist = true;
            }
        }
        return  word_exist ;
    }

    public boolean query(String...args) {
        return challengequery("query", args);
    }

    public boolean challenge (String...args) {
       return challengequery("challenge", args);
    }
    public static DictionaryManager get(){
        if (singletone == null)
            singletone = new DictionaryManager();
        return singletone;
    }

    public int getSize(){
        return Books.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryManager that)) return false;
        return Objects.equals(Books, that.Books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Books);
    }
}

