package by.htp.basumatarau.normalCatalog.dao.util.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria extends HashMap<LookUpOpts, String> {
    public enum CriteriaType {
        MATCH_ANY, MATCH_ALL
    }
    private CriteriaType type;
    private Map<LookUpOpts, Boolean> matchFlags = new HashMap<>();

    public Criteria(CriteriaType type){
         this.type = type;
    }

    public Criteria add(LookUpOpts key, String value) {
        super.put(key, value);
        return this;
    }

    private void resetFlags(){
        for (LookUpOpts opt : LookUpOpts.values()) {
            matchFlags.put(opt, false);
        }
    }

    public void matched(LookUpOpts opt){
        matchFlags.put(opt, true);
    }

    public boolean satisfied(){
        if(type.equals(CriteriaType.MATCH_ANY)){
            return anyCriteriaMatches();
        }else {
            return allCriteriaMatches();
        }
    }

    private boolean allCriteriaMatches(){
        boolean result = true;
        for (Entry<LookUpOpts, Boolean> matchFlag : this.matchFlags.entrySet()) {
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
        for (Entry<LookUpOpts, Boolean> matchFlag : matchFlags.entrySet()) {
            if(matchFlag.getValue()){
                result = true;
                break;
            }
        }
        resetFlags();
        return result;
    }
}
