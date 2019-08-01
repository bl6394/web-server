package edu.wustl.elexicon.webserver.web;

public enum NonWordItemViewModelMapper {

    WORD ("word", "Word", null, null),
    LENGTH ("length", "Length", "minLength", "maxLength"),
    ORTHO_N ("ortho_n", "Ortho_N","minOrthoN", "maxOrthoN"),
    BG_SUM ("bg_sum", "BG_Sum","minBGSum", "maxBGSum"),
    BG_MEAN ("bg_mean", "BG_Mean", "minBGMean", "maxBGMean"),
    BG_FREQ_BY_POS ("bg_freq_by_pos", "BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos"),
    NWI_MEAN_RT ("nwi_mean_rt", "NWI_Mean_RT","minNWIMeanRT", "maxNWIMeanRT"),
    NWI_SD ("nwi_sd", "NWI_SD","minNWISD", "maxNWISD"),
    NWI_ZSCORE ("nwi_zscore", "NWI_Zscore","minNWIZscore", "maxNWIZscore"),
    OBS ("obs", "Obs","minObs", "maxObs"),
    NWI_MEAN_ACCURACY ("nwi_mean_accuracy", "NWI_Mean_Accuracy", "minNWIMeanAccuracy", "maxNWIMeanAccuracy");


    private final String columnName;

    public static NonWordItemViewModelMapper getByMinConstraint(String minConstraintName) {
        for (NonWordItemViewModelMapper value : NonWordItemViewModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static NonWordItemViewModelMapper getByMaxConstraint(String maxConstraintName) {
        for (NonWordItemViewModelMapper value : NonWordItemViewModelMapper.values()){
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


    NonWordItemViewModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
