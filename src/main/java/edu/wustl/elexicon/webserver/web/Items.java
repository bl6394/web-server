package edu.wustl.elexicon.webserver.web;

public enum Items {

    LENGTH ("length", "Length", "minLength", "maxLength", null),
    FREQ_KF ("freq_kf", "Freq_KF", "minFreqKF", "maxFreqKF", null),
    FREQ_HAL ("freq_hal", "Freq_HAL", "minFreqHal", "maxFreqHal", null),
    LOG_FREQ_HAL ("log_freq_hal", "Log_Freq_HAL", "minLogFreqHal", "maxLogFreqHal", null),
    ORTHO_N ("ortho_n", "Ortho_N","minOrthoN", "maxOrthoN", null),
    OLD ("old", "OLD","minOLD", "maxOLD", null),
    OG_N ("og_n", "OG_N","minOGN", "maxOGN", null),
    OG_N_H ("og_n_h", "OG_N_H","minOGNH", "maxOGNH", null),
    OLDF ("oldf", "OLDF","minOLDF", "maxOLDF", null),
    FREQ_N ("freq_n", "Freq_N","minFreqN", "maxFreqN", null),
    FREQ_N_P ("freq_n_p", "Freq_N_P","minFreqNP", "maxFreqNP", null),
    FREQ_N_PH ("freq_n_ph", "Freq_N_PH","minFreqNPH", "maxFreqNPH", null),
    FREQ_N_OG ("freq_n_og", "Freq_N_OG","minFreqNOG", "maxFreqNOG", null),
    FREQ_N_OGH ("freq_n_ogh", "Freq_N_OGH","minFreqNOGH", "maxFreqNOGH", null),
    FREQ_GREATER ("freq_greater", "Freq_Greater","minFreqGreater", "maxFreqGreater", null),
    FREQ_G_MEAN ("freq_g_mean", "Freq_G_Mean","minFreqGMean", "maxFreqGMean", null),
    FREQ_LESS ("freq_less", "Freq_Less","minFreqLess", "maxFreqLess", null),
    FREQ_L_MEAN ("freq_l_mean", "Freq_L_Mean","minFreqLMean", "maxFreqLMean", null),
    FREQ_REL ("freq_rel", "Freq_Rel","minFreqRel", "maxFreqRel", null),
    BG_SUM ("bg_sum", "BG_Sum","minBGSum", "maxBGSum", null),
    BG_MEAN ("bg_mean", "BG_Mean", "minBGMean", "maxBGMean", null),
    BG_FREQ_BY_POS ("bg_freq_by_pos", "BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos", null),
    I_MEAN_RT ("i_mean_rt", "I_Mean_RT","minIMeanRT", "maxIMeanRT", null),
    I_ZSCORE ("i_zscore", "I_Zscore","minIZscore", "maxIZscore", null),
    I_SD ("i_sd", "I_SD","minISD", "maxISD", null),
    I_MEAN_ACCURACY ("i_mean_accuracy", "I_Mean_Accuracy","minIMeanAccuracy", "maxIMeanAccuracy", null),
    OBS ("obs", "Obs","minObs", "maxObs", null),
    I_NMG_MEAN_RT ("i_nmg_mean_rt", "I_NMG_Mean_RT","minINmgMeanRT", "maxINmgMeanRT", null),
    I_NMG_ZSCORE ("i_nmg_zscore", "I_NMG_Zscore","minINmgZScore", "maxINmgZScore", null),
    I_NMG_SD ("i_nmg_sd", "I_NMG_SD","minINmgSD", "maxINmgSD", null),
    I_NMG_OBS ("i_nmg_obs", "I_NMG_Obs","minINmgObs", "maxINmgObs", null),
    I_NMG_MEAN_ACCURACY ("i_nmg_mean_accuracy", "I_NMG_Mean_Accuracy", "minINmgMeanAccuracy", "maxINmgMeanAccuracy", 3),
    POS ("pos", "POS",null, null, null),
    PRON ("pron", "Pron",null, null, null),
    PHONO_N ("phono_n", "Phono_N","minPhonoN", "maxPhonoN", null),
    PLD ("pld", "PLD","minPLD", "maxPLD", null),
    PLDF ("pldf", "PLDF","minPLDF", "maxPLDF", null),
    PHONO_N_H ("phono_n_h", "Phono_N_H","minPhonoNH", "maxPhonoNH", null),
    NSYLL ("nsyll", "NSyll","minNSyll", "maxNSyll", null),
    NPHON ("nphon", "NPhon","minNPhon", "maxNPhon", null),
    NMORPH ("nmorph", "NMorph","minNMorph", "maxNMorph", null),
    MORPHSP ("morphsp", "MorphSp",null, null, null),
    MORPHPR ("morphpr", "MorphPr",null, null, null),
    SUBTLWF ("subtlwf", "SUBTLWF", "minSubtlwf", "maxSubtlwf", null),
    LGSUBTLWF ("lgsubtlwf", "LgSUBTLWF", "minLgSubtlcd", "maxLgSubtlcd", null),
    SUBTLCD ("subtlcd", "SUBTLCD", "minSubtlcd", "maxSubtlcd", null),
    LGSUBTLCD ("lgsubtlcd", "LgSUBTLCD","minLgSubtlcd", "maxLgSubtlcd", null);


    String columnName;
    String fieldName;
    String minConstraintName;
    String maxConstraintName;
    Integer formattingLength;

    Items(String columnName, String fieldName, String minConstraintName, String maxConstraintName, Integer formattingLength){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
        this.formattingLength = formattingLength;
    }



}
