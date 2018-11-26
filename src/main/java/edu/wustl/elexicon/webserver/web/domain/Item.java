package edu.wustl.elexicon.webserver.web.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "item")
final public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wid")
    private Long wid;

    @Column(name = "word", nullable = false, length = 23)
    private String word;

    //Length is defined as the number of characters composing the word
    @Column(name = "length")
    private Byte length;

    //Freq_KF is defined as the frequency of a word as reported by the Kucera & Francis (1967) Study
    @Column(name = "freq_kf")
    private Integer freqKF;

    //Freq_HAL is defined as the frequency of a word as reported by the HAL Study
    @Column(name = "freq_hal")
    private Integer freqHal;

    //TODO Definition / Use???
    @Column(name = "freq_tsa")
    private Integer freqTsa;

    //Log_Freq_HAL is defined as the Log of Frequency of a word as reported by the HAL Study
    @Column(name = "log_freq_hal")
    private Double logFreqHal;

    //Ortho_N is defined as the number of orthographic neighbors that a word has
    @Column(name = "ortho_n")
    private Integer orthoNeighborhood;

    //OLD is defined as the mean of the closest 20 LD neighbors for the orthograph, see Database News and Updates for details
    @Column(name = "old")
    private Double old;

    //OG_N is defined as the number of phonographic neighbors that a word has. Note: this statistic excludes homophones
    @Column(name = "og_n")
    private Integer ogN;

    //OG_N_H is defined as the number of phonographic neighbors that a word has. Note: this statistic includes homophones
    @Column(name = "og_n_h")
    private Integer ogNH;

    //OLDF is defined as the mean log hal lfreq of the closest 20 LD neighbors for the orthography, see Database News and Updates for details
    @Column(name = "oldf")
    private Double oldf;

    //Freq_N is defined as the average of the Frequency (Freq_HAL) of the orthographic neighborhood of a particular word
    @Column(name = "freq_n")
    private Double freqN;

    //Freq_N_P is defined as the average of the Frequency (Freq_HAL) of the phonological neighborhood of a particular word. Note: This statistic excludes homophones
    @Column(name = "freq_n_p")
    private Double freqNP;

    //Freq_N_PH is defined as the average of the Frequency (Freq_HAL) of the phonological neighborhood of a particular word. Note: This statistic includes homophones
    @Column(name = "freq_n_ph")
    private Double freqNPH;

    //Freq_N_OG is defined as the average of the log Frequency (Log_Freq_Hal) of the phonographic neighborhood of a particular word
    @Column(name = "freq_n_og")
    private Double freqNOG;

    //Freq_N_OGH is defined as the average of the Frequency (Freq_HAL) of the phonographic neighborhood of a particular word. Note: This statistic includes homophones
    @Column(name = "freq_n_ogh")
    private Double freqNOGH;

    //Freq_Greater is defined as the number of words in the orthographic neighborhood of an item with a frequency greater than the frequency of the item
    @Column(name = "freq_greater")
    private Integer freqGreater;

    //Freq_G_Mean is defined as the average frequency of the orthographic neighbors who have a frequency greater than a given item
    @Column(name = "freq_g_mean")
    private Double freqGMean;

    //Freq_Less is defined as the number of orthographic neighbors with a frequency less than that of a given item
    @Column(name = "freq_less")
    private Integer freqLess;

    //Freq_L_Mean is defined as the average frequency of the orthographic neighbors who have a frequency less than a given item
    //TODO Udate description in website - missing.
    @Column(name = "freq_l_mean")
    private Double freqLMean;

    //Freq_Rel is user defined
    //TODO Udate description in website - missing.
    @Column(name = "freq_rel")
    private Double freqRel;

    //BG_SUM is defined as the sum of the bigram count for a particular word
    @Column(name = "bg_sum")
    private Integer bgSum;

    //BG_Mean is defined as the average bigram count for a particular word
    @Column(name = "bg_mean")
    private Double bgMean;

    //BG_Freq_By_Pos is defined as the sum of the bigram count (by position) for a particular word
    @Column(name = "bg_freq_by_pos")
    private Integer bgFreqByPos;

    //I_Mean_RT is defined as the mean reaction time for a particular word in milliseconds across all participants in the ELP study
    @Column(name = "i_mean_rt")
    private Double iMeanRt;

    //TODO: i_rt_n Description?  Is this being used?
    @Column(name = "i_rt_n")
    private Double irtn;

    //I_Zscore is user defined
    //TODO Udate description in website - missing.
    @Column(name = "i_zscore")
    private Double iZscore;

    //I_SD is defined as the standard deviation of the mean reaction time (MeanRT) for a particular word across all participants in the ELP study
    @Column(name = "i_sd")
    private Double isd;

    //Obs is defined as the number of observations that were made of the mean reaction time (MeanRT) for a particular word in the ELP study
    @Column(name = "obs")
    private Integer obs;

    //I_NMG_Mean_Accuracy is defined as the average I_NMG_Mean_Accuracy for all particpants for this word.
    @Column(name = "i_mean_accuracy")
    private Double iMeanAccuracy;

    //I_NMG_Mean_RT is defined as the mean reaction time for a particular word in milliseconds across all participants in the ELP study
    @Column(name = "i_nmg_mean_rt")
    private Double iNmgMeanRt;


    //TODO: i_nmg_rt_n  Description?  Is this being used?
    @Column(name = "i_nmg_rt_n")
    private Double iNmgRtN;

    //I_NMG_Zscore is user defined
    //TODO Udate description in website - missing.
    @Column(name = "i_nmg_zscore")
    private Double iNmgZscore;

    //I_NMG_SD is defined as the standard deviation of the mean reaction time (MeanRT) for a particular word across all participants in the ELP study
    @Column(name = "i_nmg_sd")
    private Double iNmgSd;

    //I_NMG_Obs is defined as the number of Observations that were made of the mean reaction time (MeanRT) for a particular word in the ELP study
    @Column(name = "i_nmg_obs")
    private Integer iNmgObs;

    //I_NMG_Mean_Accuracy is defined as the average I_NMG_Mean_Accuracy for all particpants for this word.
    @Column(name = "i_nmg_mean_accuracy")
    private Double iNmgMeanAccuracy;

    //TODO: phonology  Description?  Is this being used?
    @Column(name = "phonology", length = 50)
    private String phonology;

    //TODO: phonemecnt  Description?  Is this being used?
    @Column(name = "phonemecnt")
    private Integer phonemecnt;

    //TODO: syntacticcode  Description?  Is this being used?
    @Column(name = "syntacticcode", length = 15)
    private String syntacticcode;

    //TODO: hyphenation  Description?  Is this being used?
    @Column(name = "hyphenation", length = 50)
    private String hyphenation;

    //TODO: hyphencnt  Description?  Is this being used?
    @Column(name = "hyphencnt")
    private Integer hyphencnt;

    //TODO: ref  Description?  Is this being used?
    @Column(name = "ref", length = 4)
    private String ref;

//    POS is the part of speech of the word.
//    JJ adjective ("beautiful")
//    NN noun ("beauty")
//    RB adverb ("beautifully")
//    VB verb ("beautify")
//    encl enclitic group ("beauty's")
//    minor all other ("the", "in", "what", "uh")
//    ? unknown
//    | separates alternatives: "can" VB|NN
    @Column(name = "pos", length = 17)
    private String pos;

    //TODO: infl  Description?  Is this being used?
    @Column(name = "infl", length = 13)
    private String infl;


//    Pron (Pronunciation):
//    A single representative pronunciation of the word. Dots mark possible syllable boundaries. Primary stress is marked by " before the stressed vowel, secondary stress by % E.g.: d%i.k"Or.4@.k%e4.@d for "decorticated". Pronunciation is based on General American standard, and uses the following codes based on SAMPA (http://www.phon.ucl.ac.uk/home/sampa):
//
//    a At; A cAr, spA; aI Ice; aU OUt; @ Above; @` buttER;
//    b Boy; d Dog; dZ baDGe; D THis; e Ape; E Ebb; f Fig;
//    g Go; h Hip;i EAt; I If; j Yes; k Kite; l Lip;
//    l= bottLe; m= deisM; n= buttoN
//    N siNG; o OAt; O AUto; OI OYster; p Pig; r Read;
//    s Sew; S SHow; t Toy; tS caTCH; T THin; u rUde; U pUt;
//    v Van; V Up; w Wind; z Zoo; Z viSion; 3` bIRd; 4 beTTer
    @Column(name = "pron", length = 32)
    private String pron;

    //Phono_N is defined as the number of phonological neighbors that a word has. Note: this statistic excludes homophones
    @Column(name = "phono_n")
    private Integer phono_n;

    //PLD is defined as the mean of the closest 20 LD neighbors for the phonology, see Database News and Updates for details
    @Column(name = "pld")
    private Double pld;

    //PLDF is defined as the mean log hal lfreq of the closest 20 LD neighbors for the phonology, see Database News and Updates for details
    @Column(name = "pldf")
    private Double pldf;

    //Phono_N_H is defined as the number of phonological neighbors that a word has. Note: this statistic includes homophones
    @Column(name = "phono_n_h")
    private Integer phono_n_h;

    //Number of syllables in the main pronunciation
    @Column(name = "nsyll")
    private Byte nsyll;

    //NPhon is defined as the number of phonemes in the main pronunciation. The diphthongs /aI/, /aU/, /OI/, and the affricates /tS/ and /dZ/, each count as single phonemes
    @Column(name = "nphon")
    private Byte nphon;

    //NMorph is defined as the number of Morphemes
    @Column(name = "nmorph")
    private Byte nmorph;

    //    MorphSp is defined as the word respelled with morpheme markers indicating the composition of the word. Markers include:
    //    {free bases}
    //    <prefixes<
    //    >suffixes>
    //    -- separates other bound morphemes
    //    Free bases are spelled as they are in the independent word, e.g.: {abhor}>ent> for abhorrent
    @Column(name = "morphsp", length = 34)
    private String morphsp;

    //MorphPr is defined as single representative pronunciation of the word.
    //Morpheme markers enclose:
    //{free bases}
    //{free bases with different pronunciation $ }
    //<prefixes<
    //>suffixes>
    //-- separates other bound morphemes
    //Dots mark possible syllable boundaries.
    //Primary stress is marked by " before the stressed vowel, secondary stress by % E.g.: <d%i<.{k"Or.4@.k%e4}.>@d> for "decorticated".
    //Pronunciation is based on General American standard, and uses the following codes based on SAMPA (http://www.phon.ucl.ac.uk/home/sampa):
    //a At; A cAr, spA; aI Ice; aU OUt; @ Above; @` buttER; b Boy;
    //d Dog; dZ baDGe; D THis; e Ape; E Ebb; f Fig; g Go; h Hip;
    //i EAt; I If; j Yes; k Kite; l Lip; l= bottLe; m= deisM; n=buttoN;
    //N siNG; o OAt; O AUto; OI OYster; p Pig; r Read;
    //s Sew; S SHow; t Toy; tS caTCH; T THin; u rUde; U pUt;
    //v Van; V Up; w Wind; z Zoo; Z viSion; 3` bIRd; 4 beTTer
    @Column(name = "morphpr", length=42)
    private String morphpr;

    //SUBTLWF is the SUBTLEX frequency per million words
    @Column(name = "subtlwf")
    private Double subtlwf;

    //Lg10WF is based upon log10(number of times the word appears in the corpus + 1)
    @Column(name = "lgsubtlwf")
    private Double lgsubtlwf;

    //SUBTLCD is the SUBTLEX contextual diversity (% of films containing the word)
    @Column(name = "subtlcd")
    private Double subtlcd;

    //Lg10CD is based upon log10(number of times the word appears in the corpus + 1)
    @Column(name = "lgsubtlcd")
    private Double lgsubtlcd;

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Byte getLength() {
        return length;
    }

    public void setLength(Byte length) {
        this.length = length;
    }

    public Integer getFreqKF() {
        return freqKF;
    }

    public void setFreqKF(Integer freqKF) {
        this.freqKF = freqKF;
    }

    public Integer getFreqHal() {
        return freqHal;
    }

    public void setFreqHal(Integer freqHal) {
        this.freqHal = freqHal;
    }

    public Integer getFreqTsa() {
        return freqTsa;
    }

    public void setFreqTsa(Integer freqTsa) {
        this.freqTsa = freqTsa;
    }

    public Double getLogFreqHal() {
        return logFreqHal;
    }

    public void setLogFreqHal(Double logFreqHal) {
        this.logFreqHal = logFreqHal;
    }

    public Integer getOrthoNeighborhood() {
        return orthoNeighborhood;
    }

    public void setOrthoNeighborhood(Integer orthoNeighborhood) {
        this.orthoNeighborhood = orthoNeighborhood;
    }

    public Double getOld() {
        return old;
    }

    public void setOld(Double old) {
        this.old = old;
    }

    public Integer getOgN() {
        return ogN;
    }

    public void setOgN(Integer ogN) {
        this.ogN = ogN;
    }

    public Integer getOgNH() {
        return ogNH;
    }

    public void setOgNH(Integer ogNH) {
        this.ogNH = ogNH;
    }

    public Double getOldf() {
        return oldf;
    }

    public void setOldf(Double oldf) {
        this.oldf = oldf;
    }

    public Double getFreqN() {
        return freqN;
    }

    public void setFreqN(Double freqN) {
        this.freqN = freqN;
    }

    public Double getFreqNP() {
        return freqNP;
    }

    public void setFreqNP(Double freqNP) {
        this.freqNP = freqNP;
    }

    public Double getFreqNPH() {
        return freqNPH;
    }

    public void setFreqNPH(Double freqNPH) {
        this.freqNPH = freqNPH;
    }

    public Double getFreqNOG() {
        return freqNOG;
    }

    public void setFreqNOG(Double freqNOG) {
        this.freqNOG = freqNOG;
    }

    public Double getFreqNOGH() {
        return freqNOGH;
    }

    public void setFreqNOGH(Double freqNOGH) {
        this.freqNOGH = freqNOGH;
    }

    public Integer getFreqGreater() {
        return freqGreater;
    }

    public void setFreqGreater(Integer freqGreater) {
        this.freqGreater = freqGreater;
    }

    public Double getFreqGMean() {
        return freqGMean;
    }

    public void setFreqGMean(Double freqGMean) {
        this.freqGMean = freqGMean;
    }

    public Integer getFreqLess() {
        return freqLess;
    }

    public void setFreqLess(Integer freqLess) {
        this.freqLess = freqLess;
    }

    public Double getFreqLMean() {
        return freqLMean;
    }

    public void setFreqLMean(Double freqLMean) {
        this.freqLMean = freqLMean;
    }

    public Double getFreqRel() {
        return freqRel;
    }

    public void setFreqRel(Double freqRel) {
        this.freqRel = freqRel;
    }

    public Integer getBgSum() {
        return bgSum;
    }

    public void setBgSum(Integer bgSum) {
        this.bgSum = bgSum;
    }

    public Double getBgMean() {
        return bgMean;
    }

    public void setBgMean(Double bgMean) {
        this.bgMean = bgMean;
    }

    public Integer getBgFreqByPos() {
        return bgFreqByPos;
    }

    public void setBgFreqByPos(Integer bgFreqByPos) {
        this.bgFreqByPos = bgFreqByPos;
    }

    public Double getiMeanRt() {
        return iMeanRt;
    }

    public void setiMeanRt(Double iMeanRt) {
        this.iMeanRt = iMeanRt;
    }

    public Double getIrtn() {
        return irtn;
    }

    public void setIrtn(Double irtn) {
        this.irtn = irtn;
    }

    public Double getiZscore() {
        return iZscore;
    }

    public void setiZscore(Double iZscore) {
        this.iZscore = iZscore;
    }

    public Double getIsd() {
        return isd;
    }

    public void setIsd(Double isd) {
        this.isd = isd;
    }

    public Integer getObs() {
        return obs;
    }

    public void setObs(Integer obs) {
        this.obs = obs;
    }

    public Double getiMeanAccuracy() {
        return iMeanAccuracy;
    }

    public void setiMeanAccuracy(Double iMeanAccuracy) {
        this.iMeanAccuracy = iMeanAccuracy;
    }

    public Double getiNmgMeanRt() {
        return iNmgMeanRt;
    }

    public void setiNmgMeanRt(Double iNmgMeanRt) {
        this.iNmgMeanRt = iNmgMeanRt;
    }

    public Double getiNmgRtN() {
        return iNmgRtN;
    }

    public void setiNmgRtN(Double iNmgRtN) {
        this.iNmgRtN = iNmgRtN;
    }

    public Double getiNmgZscore() {
        return iNmgZscore;
    }

    public void setiNmgZscore(Double iNmgZscore) {
        this.iNmgZscore = iNmgZscore;
    }

    public Double getiNmgSd() {
        return iNmgSd;
    }

    public void setiNmgSd(Double iNmgSd) {
        this.iNmgSd = iNmgSd;
    }

    public Integer getiNmgObs() {
        return iNmgObs;
    }

    public void setiNmgObs(Integer iNmgObs) {
        this.iNmgObs = iNmgObs;
    }

    public Double getiNmgMeanAccuracy() {
        return iNmgMeanAccuracy;
    }

    public void setiNmgMeanAccuracy(Double iNmgMeanAccuracy) {
        this.iNmgMeanAccuracy = iNmgMeanAccuracy;
    }

    public String getPhonology() {
        return phonology;
    }

    public void setPhonology(String phonology) {
        this.phonology = phonology;
    }

    public Integer getPhonemecnt() {
        return phonemecnt;
    }

    public void setPhonemecnt(Integer phonemecnt) {
        this.phonemecnt = phonemecnt;
    }

    public String getSyntacticcode() {
        return syntacticcode;
    }

    public void setSyntacticcode(String syntacticcode) {
        this.syntacticcode = syntacticcode;
    }

    public String getHyphenation() {
        return hyphenation;
    }

    public void setHyphenation(String hyphenation) {
        this.hyphenation = hyphenation;
    }

    public Integer getHyphencnt() {
        return hyphencnt;
    }

    public void setHyphencnt(Integer hyphencnt) {
        this.hyphencnt = hyphencnt;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getInfl() {
        return infl;
    }

    public void setInfl(String infl) {
        this.infl = infl;
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public Integer getPhono_n() {
        return phono_n;
    }

    public void setPhono_n(Integer phono_n) {
        this.phono_n = phono_n;
    }

    public Double getPld() {
        return pld;
    }

    public void setPld(Double pld) {
        this.pld = pld;
    }

    public Double getPldf() {
        return pldf;
    }

    public void setPldf(Double pldf) {
        this.pldf = pldf;
    }

    public Integer getPhono_n_h() {
        return phono_n_h;
    }

    public void setPhono_n_h(Integer phono_n_h) {
        this.phono_n_h = phono_n_h;
    }

    public Byte getNsyll() {
        return nsyll;
    }

    public void setNsyll(Byte nsyll) {
        this.nsyll = nsyll;
    }

    public Byte getNphon() {
        return nphon;
    }

    public void setNphon(Byte nphon) {
        this.nphon = nphon;
    }

    public Byte getNmorph() {
        return nmorph;
    }

    public void setNmorph(Byte nmorph) {
        this.nmorph = nmorph;
    }

    public String getMorphsp() {
        return morphsp;
    }

    public void setMorphsp(String morphsp) {
        this.morphsp = morphsp;
    }

    public String getMorphpr() {
        return morphpr;
    }

    public void setMorphpr(String morphpr) {
        this.morphpr = morphpr;
    }

    public Double getSubtlwf() {
        return subtlwf;
    }

    public void setSubtlwf(Double subtlwf) {
        this.subtlwf = subtlwf;
    }

    public Double getLgsubtlwf() {
        return lgsubtlwf;
    }

    public void setLgsubtlwf(Double lgsubtlwf) {
        this.lgsubtlwf = lgsubtlwf;
    }

    public Double getSubtlcd() {
        return subtlcd;
    }

    public void setSubtlcd(Double subtlcd) {
        this.subtlcd = subtlcd;
    }

    public Double getLgsubtlcd() {
        return lgsubtlcd;
    }

    public void setLgsubtlcd(Double lgsubtlcd) {
        this.lgsubtlcd = lgsubtlcd;
    }

    public Item(){}

    public Item(String word){
        this.word = word;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("wid=").append(wid);
        sb.append(", word='").append(word).append('\'');
        sb.append(", length=").append(length);
        sb.append(", freqKF=").append(freqKF);
        sb.append(", freqHal=").append(freqHal);
        sb.append(", freqTsa=").append(freqTsa);
        sb.append(", logFreqHal=").append(logFreqHal);
        sb.append(", orthoNeighborhood=").append(orthoNeighborhood);
        sb.append(", old=").append(old);
        sb.append(", ogN=").append(ogN);
        sb.append(", ogNH=").append(ogNH);
        sb.append(", oldf=").append(oldf);
        sb.append(", freqN=").append(freqN);
        sb.append(", freqNP=").append(freqNP);
        sb.append(", freqNPH=").append(freqNPH);
        sb.append(", freqNOG=").append(freqNOG);
        sb.append(", freqNOGH=").append(freqNOGH);
        sb.append(", freqGreater=").append(freqGreater);
        sb.append(", freqGMean=").append(freqGMean);
        sb.append(", freqLess=").append(freqLess);
        sb.append(", freqLMean=").append(freqLMean);
        sb.append(", freqRel=").append(freqRel);
        sb.append(", bgSum=").append(bgSum);
        sb.append(", bgMean=").append(bgMean);
        sb.append(", bgFreqByPos=").append(bgFreqByPos);
        sb.append(", iMeanRt=").append(iMeanRt);
        sb.append(", irtn=").append(irtn);
        sb.append(", iZscore=").append(iZscore);
        sb.append(", isd=").append(isd);
        sb.append(", obs=").append(obs);
        sb.append(", iMeanAccuracy=").append(iMeanAccuracy);
        sb.append(", iNmgMeanRt=").append(iNmgMeanRt);
        sb.append(", iNmgRtN=").append(iNmgRtN);
        sb.append(", iNmgZscore=").append(iNmgZscore);
        sb.append(", iNmgSd=").append(iNmgSd);
        sb.append(", iNmgObs=").append(iNmgObs);
        sb.append(", iNmgMeanAccuracy=").append(iNmgMeanAccuracy);
        sb.append(", phonology='").append(phonology).append('\'');
        sb.append(", phonemecnt=").append(phonemecnt);
        sb.append(", syntacticcode='").append(syntacticcode).append('\'');
        sb.append(", hyphenation='").append(hyphenation).append('\'');
        sb.append(", hyphencnt=").append(hyphencnt);
        sb.append(", ref='").append(ref).append('\'');
        sb.append(", pos='").append(pos).append('\'');
        sb.append(", infl='").append(infl).append('\'');
        sb.append(", pron='").append(pron).append('\'');
        sb.append(", phono_n=").append(phono_n);
        sb.append(", pld=").append(pld);
        sb.append(", pldf=").append(pldf);
        sb.append(", phono_n_h=").append(phono_n_h);
        sb.append(", nsyll=").append(nsyll);
        sb.append(", nphon=").append(nphon);
        sb.append(", nmorph=").append(nmorph);
        sb.append(", nmorph=").append(nmorph);
        sb.append(", morphsp='").append(morphsp).append('\'');
        sb.append(", morphpr='").append(morphpr).append('\'');
        sb.append(", subtlwf=").append(subtlwf);
        sb.append(", lgsubtlwf=").append(lgsubtlwf);
        sb.append(", subtlcd=").append(subtlcd);
        sb.append(", lgsubtlcd=").append(lgsubtlcd);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(wid, item.wid) &&
                Objects.equals(word, item.word) &&
                Objects.equals(length, item.length) &&
                Objects.equals(freqKF, item.freqKF) &&
                Objects.equals(freqHal, item.freqHal) &&
                Objects.equals(freqTsa, item.freqTsa) &&
                Objects.equals(logFreqHal, item.logFreqHal) &&
                Objects.equals(orthoNeighborhood, item.orthoNeighborhood) &&
                Objects.equals(old, item.old) &&
                Objects.equals(ogN, item.ogN) &&
                Objects.equals(ogNH, item.ogNH) &&
                Objects.equals(oldf, item.oldf) &&
                Objects.equals(freqN, item.freqN) &&
                Objects.equals(freqNP, item.freqNP) &&
                Objects.equals(freqNPH, item.freqNPH) &&
                Objects.equals(freqNOG, item.freqNOG) &&
                Objects.equals(freqNOGH, item.freqNOGH) &&
                Objects.equals(freqGreater, item.freqGreater) &&
                Objects.equals(freqGMean, item.freqGMean) &&
                Objects.equals(freqLess, item.freqLess) &&
                Objects.equals(freqLMean, item.freqLMean) &&
                Objects.equals(freqRel, item.freqRel) &&
                Objects.equals(bgSum, item.bgSum) &&
                Objects.equals(bgMean, item.bgMean) &&
                Objects.equals(bgFreqByPos, item.bgFreqByPos) &&
                Objects.equals(iMeanRt, item.iMeanRt) &&
                Objects.equals(irtn, item.irtn) &&
                Objects.equals(iZscore, item.iZscore) &&
                Objects.equals(isd, item.isd) &&
                Objects.equals(obs, item.obs) &&
                Objects.equals(iMeanAccuracy, item.iMeanAccuracy) &&
                Objects.equals(iNmgMeanRt, item.iNmgMeanRt) &&
                Objects.equals(iNmgRtN, item.iNmgRtN) &&
                Objects.equals(iNmgZscore, item.iNmgZscore) &&
                Objects.equals(iNmgSd, item.iNmgSd) &&
                Objects.equals(iNmgObs, item.iNmgObs) &&
                Objects.equals(iNmgMeanAccuracy, item.iNmgMeanAccuracy) &&
                Objects.equals(phonology, item.phonology) &&
                Objects.equals(phonemecnt, item.phonemecnt) &&
                Objects.equals(syntacticcode, item.syntacticcode) &&
                Objects.equals(hyphenation, item.hyphenation) &&
                Objects.equals(hyphencnt, item.hyphencnt) &&
                Objects.equals(ref, item.ref) &&
                Objects.equals(pos, item.pos) &&
                Objects.equals(infl, item.infl) &&
                Objects.equals(pron, item.pron) &&
                Objects.equals(phono_n, item.phono_n) &&
                Objects.equals(pld, item.pld) &&
                Objects.equals(pldf, item.pldf) &&
                Objects.equals(phono_n_h, item.phono_n_h) &&
                Objects.equals(nsyll, item.nsyll) &&
                Objects.equals(nphon, item.nphon) &&
                Objects.equals(nmorph, item.nmorph) &&
                Objects.equals(nmorph, item.nmorph) &&
                Objects.equals(morphsp, item.morphsp) &&
                Objects.equals(morphpr, item.morphpr) &&
                Objects.equals(subtlwf, item.subtlwf) &&
                Objects.equals(lgsubtlwf, item.lgsubtlwf) &&
                Objects.equals(subtlcd, item.subtlcd) &&
                Objects.equals(lgsubtlcd, item.lgsubtlcd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wid, word, length, freqKF, freqHal, freqTsa, logFreqHal, orthoNeighborhood, old, ogN, ogNH, oldf, freqN, freqNP, freqNPH, freqNOG, freqNOGH, freqGreater, freqGMean, freqLess, freqLMean, freqRel, bgSum, bgMean, bgFreqByPos, iMeanRt, irtn, iZscore, isd, obs, iMeanAccuracy, iNmgMeanRt, iNmgRtN, iNmgZscore, iNmgSd, iNmgObs, iNmgMeanAccuracy, phonology, phonemecnt, syntacticcode, hyphenation, hyphencnt, ref, pos, infl, pron, phono_n, pld, pldf, phono_n_h, nsyll, nphon, nmorph, nmorph, morphsp, morphpr, subtlwf, lgsubtlwf, subtlcd, lgsubtlcd);
    }
}
