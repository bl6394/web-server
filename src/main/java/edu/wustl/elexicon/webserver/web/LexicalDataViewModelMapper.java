package edu.wustl.elexicon.webserver.web;

public enum LexicalDataViewModelMapper {

    SUB_ID ("sub_id", "Sub_ID", "minSubId", "maxSubId"),
    TRIAL ("trial", "Trial", "minTrial", "maxTrial"),
    TYPE ("type", "Type", "minType", "maxType"),
    D_ACCURACY ("d_accuracy", "D_ACCURACY", "minDAccuracy", "maxDAccuracy"),
    D_RT ("d_rt", "D_RT", "minDRT", "maxDRT"),
    D_WORD ("d_word", "D_Word", "minDWord", "maxDWord"),
    OUTLIER ("outlier", "Outlier", "minOutlier", "maxOutlier"),
    D_ZSCORE ("d_zscore", "D_Zscore", "minDZscore", "maxDZscore");

    private final String columnName;

    public static LexicalDataViewModelMapper getByMinConstraint(String minConstraintName) {
        for (LexicalDataViewModelMapper value : LexicalDataViewModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static LexicalDataViewModelMapper getByMaxConstraint(String maxConstraintName) {
        for (LexicalDataViewModelMapper value : LexicalDataViewModelMapper.values()){
            if (value.maxConstraintName != null && value.maxConstraintName.equals(maxConstraintName)){
                return value;
            }
        }
        return null;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMinConstraintName() {
        return minConstraintName;
    }

    public String getMaxConstraintName() {
        return maxConstraintName;
    }

    private final String fieldName;
    private final String minConstraintName;
    private final String maxConstraintName;


    LexicalDataViewModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
