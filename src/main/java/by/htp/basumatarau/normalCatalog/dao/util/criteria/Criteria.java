package by.htp.basumatarau.normalCatalog.dao.util.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria<T> extends HashMap<T, String> {

    public enum Matcher {
        MATCH_ANY, MATCH_ALL
    }

    private final Class<T> mathcType;
    private Matcher type;
    private Map<T, Boolean> matchFlags = new HashMap<>();

    public Class<T> getCriteriaMatchType(){
        return mathcType;
    }

    public Criteria(Class<T> clazz){
        this.mathcType = clazz;
        this.type = Matcher.MATCH_ANY;
    }

    public Criteria(Class<T> clazz, Matcher type){
        this.mathcType = clazz;
        this.type = type;

    }

    public Criteria<T> add(T key, String value) {
        super.put(key, value);
        return this;
    }

    private void resetFlags(){
        for (Entry<T, Boolean> flag : matchFlags.entrySet()) {
            flag.setValue(false);
        }
    }

    public void matched(T opt){
        matchFlags.put(opt, true);
    }
    public void notMatched(T opt){
        matchFlags.put(opt, false);
    }

    public boolean satisfied(){
        if(type.equals(Matcher.MATCH_ANY)){
            return anyCriteriaMatches();
        }else {
            return allCriteriaMatches();
        }
    }

    private boolean allCriteriaMatches(){
        boolean result = true;
        for (Entry<T, Boolean> matchFlag : this.matchFlags.entrySet()) {
            if(!matchFlag.getValue()){
                result = false;
                break;
            }
        }
        resetFlags();
        return result;
    }

    private boolean anyCriteriaMatches(){
        boolean result = false;
        for (Entry<T, Boolean> matchFlag : matchFlags.entrySet()) {
            if(matchFlag.getValue()){
                result = true;
                break;
            }
        }
        resetFlags();
        return result;
    }
}
