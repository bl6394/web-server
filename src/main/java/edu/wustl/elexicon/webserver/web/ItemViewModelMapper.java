package edu.wustl.elexicon.webserver.web;

public enum ItemViewModelMapper {

    OCCURRENCES("occurrences", "Occurrences", null, null),
    WORD ("word", "Word", null, null),
    LENGTH ("length", "Length", "minLength", "maxLength"),
    FREQ_KF ("freq_kf", "Freq_KF", "minFreqKF", "maxFreqKF"),
    FREQ_HAL ("freq_hal", "Freq_HAL", "minFreqHal", "maxFreqHal"),
    LOG_FREQ_HAL ("log_freq_hal", "Log_Freq_HAL", "minLogFreqHal", "maxLogFreqHal"),
    SUBTLWF ("subtlwf", "SUBTLWF", "minSubtlwf", "maxSubtlwf"),
    LGSUBTLWF ("lgsubtlwf", "LgSUBTLWF", "minLgSubtlcd", "maxLgSubtlcd"),
    SUBTLCD ("subtlcd", "SUBTLCD", "minSubtlcd", "maxSubtlcd"),
    LGSUBTLCD ("lgsubtlcd", "LgSUBTLCD","minLgSubtlcd", "maxLgSubtlcd"),
    ORTHO_N ("ortho_n", "Ortho_N","minOrthoN", "maxOrthoN"),
    PHONO_N ("phono_n", "Phono_N","minPhonoN", "maxPhonoN"),
    PHONO_N_H ("phono_n_h", "Phono_N_H","minPhonoNH", "maxPhonoNH"),
    OG_N ("og_n", "OG_N","minOGN", "maxOGN"),
    OG_N_H ("og_n_h", "OG_N_H","minOGNH", "maxOGNH"),
    FREQ_N ("freq_n", "Freq_N","minFreqN", "maxFreqN"),
    FREQ_N_P ("freq_n_p", "Freq_N_P","minFreqNP", "maxFreqNP"),
    FREQ_N_PH ("freq_n_ph", "Freq_N_PH","minFreqNPH", "maxFreqNPH"),
    FREQ_N_OG ("freq_n_og", "Freq_N_OG","minFreqNOG", "maxFreqNOG"),
    FREQ_N_OGH ("freq_n_ogh", "Freq_N_OGH","minFreqNOGH", "maxFreqNOGH"),
    FREQ_GREATER ("freq_greater", "Freq_Greater","minFreqGreater", "maxFreqGreater"),
    FREQ_G_MEAN ("freq_g_mean", "Freq_G_Mean","minFreqGMean", "maxFreqGMean"),
    FREQ_LESS ("freq_less", "Freq_Less","minFreqLess", "maxFreqLess"),
    FREQ_L_MEAN ("freq_l_mean", "Freq_L_Mean","minFreqLMean", "maxFreqLMean"),
    FREQ_REL ("freq_rel", "Freq_Rel","minFreqRel", "maxFreqRel"),
    OLD ("old", "OLD","minOLD", "maxOLD"),
    OLDF ("oldf", "OLDF","minOLDF", "maxOLDF"),
    PLD ("pld", "PLD","minPLD", "maxPLD"),
    PLDF ("pldf", "PLDF","minPLDF", "maxPLDF"),
    CR ("cr", "CR","minCR", "maxCR"),
    ARC ("arc", "ARC","minARC", "maxARC"),
    SN ("sn", "SN","minSN", "maxSN"),
    SD ("sd", "SD","minSD", "maxSD"),
    AOA ("aoa", "AOA","minAOA", "maxAOA"),
    BOI ("boi", "boi","minBOI", "maxBOI"),
    EV ("ev", "EV","minEV", "maxEV"),
    EA ("ea", "EA","minEA", "maxEA"),
    ED ("ed", "ED","minED", "maxED"),
    ASSOC_FREQ_R1 ("assocfreqr1", "Assoc_Freq_R1","minAssocFreqR1", "maxAssocFreqR1"),
    ASSOC_TYPES_R1 ("assoctypesr1", "Assoc_Types_R1","minAssocTypesR1", "maxAssocTypesR1"),
    ASSOC_FREQ_R123 ("assocfreqr123", "Assoc_Freq_R123","minAssocFreqR123", "maxAssocFreqR123"),
    ASSOC_TYPES_R123 ("assoctypesr123", "Assoc_Types_R123","minAssocTypesR123", "maxAssocTypesR123"),
    BG_SUM ("bg_sum", "BG_Sum","minBGSum", "maxBGSum"),
    BG_MEAN ("bg_mean", "BG_Mean", "minBGMean", "maxBGMean"),
    BG_FREQ_BY_POS ("bg_freq_by_pos", "BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos"),
    PRON ("pron", "Pron",null, null),
    NPHON ("nphon", "NPhon","minNPhon", "maxNPhon"),
    NSYLL ("nsyll", "NSyll","minNSyll", "maxNSyll"),
    MORPHSP ("morphsp", "MorphSp",null, null),
    MORPHPR ("morphpr", "MorphPr",null, null),
    NMORPH ("nmorph", "NMorph","minNMorph", "maxNMorph"),
    POS ("pos", "POS",null, null),
    I_MEAN_RT ("i_mean_rt", "I_Mean_RT","minIMeanRT", "maxIMeanRT"),
    I_ZSCORE ("i_zscore", "I_Zscore","minIZscore", "maxIZscore"),
    I_SD ("i_sd", "I_SD","minISD", "maxISD"),
    OBS ("obs", "Obs","minObs", "maxObs"),
    I_MEAN_ACCURACY ("i_mean_accuracy", "I_Mean_Accuracy","minIMeanAccuracy", "maxIMeanAccuracy"),
    I_NMG_MEAN_RT ("i_nmg_mean_rt", "I_NMG_Mean_RT","minINmgMeanRT", "maxINmgMeanRT"),
    I_NMG_ZSCORE ("i_nmg_zscore", "I_NMG_Zscore","minINmgZScore", "maxINmgZScore"),
    I_NMG_SD ("i_nmg_sd", "I_NMG_SD","minINmgSD", "maxINmgSD"),
    I_NMG_OBS ("i_nmg_obs", "I_NMG_Obs","minINmgObs", "maxINmgObs"),
    I_NMG_MEAN_ACCURACY ("i_nmg_mean_accuracy", "I_NMG_Mean_Accuracy", "minINmgMeanAccuracy", "maxINmgMeanAccuracy");



    private final String columnName;

    public static ItemViewModelMapper getByMinConstraint(String minConstraintName) {
        for (ItemViewModelMapper value : ItemViewModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static ItemViewModelMapper getByMaxConstraint(String maxConstraintName) {
        for (ItemViewModelMapper value : ItemViewModelMapper.values()){
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


    ItemViewModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
