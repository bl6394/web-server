package edu.wustl.elexicon.webserver.web;

public enum Items {

    WORD ("word", "Word", null, null),
    LENGTH ("length", "Length", "minLength", "maxLength"),
    FREQ_KF ("freq_kf", "Freq_KF", "minFreqKF", "maxFreqKF"),
    FREQ_HAL ("freq_hal", "Freq_HAL", "minFreqHal", "maxFreqHal"),
    LOG_FREQ_HAL ("log_freq_hal", "Log_Freq_HAL", "minLogFreqHal", "maxLogFreqHal"),
    ORTHO_N ("ortho_n", "Ortho_N","minOrthoN", "maxOrthoN"),
    OLD ("old", "OLD","minOLD", "maxOLD"),
    OG_N ("og_n", "OG_N","minOGN", "maxOGN"),
    OG_N_H ("og_n_h", "OG_N_H","minOGNH", "maxOGNH"),
    OLDF ("oldf", "OLDF","minOLDF", "maxOLDF"),
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
    BG_SUM ("bg_sum", "BG_Sum","minBGSum", "maxBGSum"),
    BG_MEAN ("bg_mean", "BG_Mean", "minBGMean", "maxBGMean"),
    BG_FREQ_BY_POS ("bg_freq_by_pos", "BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos"),
    I_MEAN_RT ("i_mean_rt", "I_Mean_RT","minIMeanRT", "maxIMeanRT"),
    I_ZSCORE ("i_zscore", "I_Zscore","minIZscore", "maxIZscore"),
    I_SD ("i_sd", "I_SD","minISD", "maxISD"),
    I_MEAN_ACCURACY ("i_mean_accuracy", "I_Mean_Accuracy","minIMeanAccuracy", "maxIMeanAccuracy"),
    OBS ("obs", "Obs","minObs", "maxObs"),
    I_NMG_MEAN_RT ("i_nmg_mean_rt", "I_NMG_Mean_RT","minINmgMeanRT", "maxINmgMeanRT"),
    I_NMG_ZSCORE ("i_nmg_zscore", "I_NMG_Zscore","minINmgZScore", "maxINmgZScore"),
    I_NMG_SD ("i_nmg_sd", "I_NMG_SD","minINmgSD", "maxINmgSD"),
    I_NMG_OBS ("i_nmg_obs", "I_NMG_Obs","minINmgObs", "maxINmgObs"),
    I_NMG_MEAN_ACCURACY ("i_nmg_mean_accuracy", "I_NMG_Mean_Accuracy", "minINmgMeanAccuracy", "maxINmgMeanAccuracy"),
    POS ("pos", "POS",null, null),
    PRON ("pron", "Pron",null, null),
    PHONO_N ("phono_n", "Phono_N","minPhonoN", "maxPhonoN"),
    PLD ("pld", "PLD","minPLD", "maxPLD"),
    PLDF ("pldf", "PLDF","minPLDF", "maxPLDF"),
    PHONO_N_H ("phono_n_h", "Phono_N_H","minPhonoNH", "maxPhonoNH"),
    NSYLL ("nsyll", "NSyll","minNSyll", "maxNSyll"),
    NPHON ("nphon", "NPhon","minNPhon", "maxNPhon"),
    NMORPH ("nmorph", "NMorph","minNMorph", "maxNMorph"),
    MORPHSP ("morphsp", "MorphSp",null, null),
    MORPHPR ("morphpr", "MorphPr",null, null),
    SUBTLWF ("subtlwf", "SUBTLWF", "minSubtlwf", "maxSubtlwf"),
    LGSUBTLWF ("lgsubtlwf", "LgSUBTLWF", "minLgSubtlcd", "maxLgSubtlcd"),
    SUBTLCD ("subtlcd", "SUBTLCD", "minSubtlcd", "maxSubtlcd"),
    LGSUBTLCD ("lgsubtlcd", "LgSUBTLCD","minLgSubtlcd", "maxLgSubtlcd");


    private final String columnName;

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


    Items(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
