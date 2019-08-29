package edu.wustl.elexicon.webserver.web;

public enum SummaryModelMapper {

    LENGTH ("mean_length", "Mean_Length", "minLength", "maxLength"),
    FREQ_KF ("mean_freq_kf", "Mean_Freq_KF", "minFreqKF", "maxFreqKF"),
    FREQ_HAL ("mean_freq_hal", "Mean_Freq_HAL", "minFreqHal", "maxFreqHal"),
    LOG_FREQ_HAL ("mean_log_freq_hal", "Mean_Log_Freq_HAL", "minLogFreqHal", "maxLogFreqHal"),
    SUBTLWF ("mean_subtlwf", "Mean_SUBTLWF", "minSubtlwf", "maxSubtlwf"),
    LGSUBTLWF ("mean_lgsubtlwf", "Mean_LgSUBTLWF", "minLgSubtlcd", "maxLgSubtlcd"),
    SUBTLCD ("mean_subtlcd", "Mean_SUBTLCD", "minSubtlcd", "maxSubtlcd"),
    LGSUBTLCD ("mean_lgsubtlcd", "Mean_LgSUBTLCD","minLgSubtlcd", "maxLgSubtlcd"),
    ORTHO_N ("mean_ortho_n", "Mean_Ortho_N","minOrthoN", "maxOrthoN"),
    PHONO_N ("mean_phono_n", "Mean_Phono_N","minPhonoN", "maxPhonoN"),
    PHONO_N_H ("mean_phono_n_h", "Mean_Phono_N_H","minPhonoNH", "maxPhonoNH"),
    OG_N ("mean_og_n", "Mean_OG_N","minOGN", "maxOGN"),
    OG_N_H ("mean_og_n_h", "Mean_OG_N_H","minOGNH", "maxOGNH"),
    FREQ_N ("mean_freq_n", "Mean_Freq_N","minFreqN", "maxFreqN"),
    FREQ_N_P ("mean_freq_n_p", "Mean_Freq_N_P","minFreqNP", "maxFreqNP"),
    FREQ_N_PH ("mean_freq_n_ph", "Mean_Freq_N_PH","minFreqNPH", "maxFreqNPH"),
    FREQ_N_OG ("mean_freq_n_og", "Mean_Freq_N_OG","minFreqNOG", "maxFreqNOG"),
    FREQ_N_OGH ("mean_freq_n_ogh", "Mean_Freq_N_OGH","minFreqNOGH", "maxFreqNOGH"),
    FREQ_GREATER ("mean_freq_greater", "Mean_Freq_Greater","minFreqGreater", "maxFreqGreater"),
    FREQ_G_MEAN ("mean_freq_g_mean", "Mean_Freq_G_Mean","minFreqGMean", "maxFreqGMean"),
    FREQ_LESS ("mean_freq_less", "Mean_Freq_Less","minFreqLess", "maxFreqLess"),
    FREQ_L_MEAN ("mean_freq_l_mean", "Mean_Freq_L_Mean","minFreqLMean", "maxFreqLMean"),
    FREQ_REL ("mean_freq_rel", "Mean_Freq_Rel","minFreqRel", "maxFreqRel"),
    OLD ("mean_old", "Mean_OLD","minOLD", "maxOLD"),
    OLDF ("mean_oldf", "Mean_OLDF","minOLDF", "maxOLDF"),
    PLD ("mean_pld", "Mean_PLD","minPLD", "maxPLD"),
    PLDF ("mean_pldf", "Mean_PLDF","minPLDF", "maxPLDF"),
    BG_SUM ("mean_mean_bg_sum", "Mean_BG_Sum","minBGSum", "maxBGSum"),
    BG_MEAN ("mean_bg_mean", "Mean_BG_Mean", "minBGMean", "maxBGMean"),
    BG_FREQ_BY_POS ("mean_bg_freq_by_pos", "Mean_BG_Freq_By_Pos","minBGFreqByPos", "maxBGFreqByPos"),
    NPHON ("mean_nphon", "Mean_NPhon","minNPhon", "maxNPhon"),
    NSYLL ("mean_nsyll", "Mean_NSyll","minNSyll", "maxNSyll"),
    NMORPH ("mean_nmorph", "Mean_NMorph","minNMorph", "maxNMorph"),
    I_MEAN_RT ("mean_i_mean_rt", "Mean_I_Mean_RT","minIMeanRT", "maxIMeanRT"),
    I_ZSCORE ("mean_i_zscore", "Mean_I_Zscore","minIZscore", "maxIZscore"),
    I_SD ("mean_i_sd", "Mean_I_SD","minISD", "maxISD"),
    OBS ("mean_obs", "Mean_Obs","minObs", "maxObs"),
    I_MEAN_ACCURACY ("mean_i_mean_accuracy", "Mean_I_Mean_Accuracy","minIMeanAccuracy", "maxIMeanAccuracy"),
    I_NMG_MEAN_RT ("mean_i_nmg_mean_rt", "Mean_I_NMG_Mean_RT","minINmgMeanRT", "maxINmgMeanRT"),
    I_NMG_ZSCORE ("mean_i_nmg_zscore", "Mean_I_NMG_Zscore","minINmgZScore", "maxINmgZScore"),
    I_NMG_SD ("mean_i_nmg_sd", "Mean_I_NMG_SD","minINmgSD", "maxINmgSD"),
    I_NMG_OBS ("mean_i_nmg_obs", "Mean_I_NMG_Obs","minINmgObs", "maxINmgObs"),
    I_NMG_MEAN_ACCURACY ("mean_i_nmg_mean_accuracy", "Mean_I_NMG_Mean_Accuracy", "minINmgMeanAccuracy", "maxINmgMeanAccuracy"),
    CR ("mean_cr", "Mean_CR","minCR", "maxCR"),
    ARC ("mean_arc", "Mean_ARC","minARC", "maxARC"),
    SN ("mean_sn", "Mean_SN","minSN", "maxSN"),
    SD ("mean_sd", "Mean_SD","minSD", "maxSD"),
    AOA ("mean_aoa", "Mean_AOA","minAOA", "maxAOA"),
    BOI ("mean_boi", "Mean_BOI","minBOI", "maxBOI"),
    EV ("mean_ev", "Mean_EV","minEV", "maxEV"),
    EA ("mean_ea", "Mean_EA","minEA", "maxEA"),
    ED ("mean_ed", "Mean_ED","minED", "maxED"),
    ASSOC_FREQ_R1 ("mean_assocfreqr1", "Mean_Assoc_Freq_R1","minAssocFreqR1", "maxAssocFreqR1"),
    ASSOC_TYPES_R1 ("mean_assoctypesr1", "Mean_Assoc_Types_R1","minAssocTypesR1", "maxAssocTypesR1"),
    ASSOC_FREQ_R123 ("mean_assocfreqr123", "Mean_Assoc_Freq_R123","minAssocFreqR123", "maxAssocFreqR123"),
    ASSOC_TYPES_R123 ("mean_assoctypesr123", "Mean_Assoc_Types_R123","minAssocTypesR123", "maxAssocTypesR123");



    private final String columnName;

    public static SummaryModelMapper getByMinConstraint(String minConstraintName) {
        for (SummaryModelMapper value : SummaryModelMapper.values()){
            if (value.minConstraintName != null && value.minConstraintName.equals(minConstraintName)){
                return value;
            }
        }
        return null;
    }

    public static SummaryModelMapper getByMaxConstraint(String maxConstraintName) {
        for (SummaryModelMapper value : SummaryModelMapper.values()){
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


    SummaryModelMapper(String columnName, String fieldName, String minConstraintName, String maxConstraintName){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.minConstraintName = minConstraintName;
        this.maxConstraintName = maxConstraintName;
    }



}
