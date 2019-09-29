package edu.wustl.elexicon.webserver.web;

public enum ArbitraryViewModelMapper {

    WORD ("word", "Word", null, null),
    LENGTH ("length", "Length", "minLength", "maxLength"),
    ORTHO_N ("ortho_n", "Ortho_N","minOrthoN", "maxOrthoN"),
    BG_SUM ("bg_sum", "BG_Sum","minBGSum", "maxBGSum"),
    BG_MEAN ("bg_mean", "BG_Mean", "minBGMean", "maxBGMean"),
    BG_FREQ_BY_POS ("bg_freq_by_pos", "BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos");


    private final String columnName;

    public static ArbitraryViewModelMapper getByMinConstraint(String minConstraintName) {
        for (ArbitraryViewModelMapper value : ArbitraryViewModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static ArbitraryViewModelMapper getByMaxConstraint(String maxConstraintName) {
        for (ArbitraryViewModelMapper value : ArbitraryViewModelMapper.values()){
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


    ArbitraryViewModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
