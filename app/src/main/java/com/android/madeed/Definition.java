package com.android.madeed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

class Word {

    private String arabicDefinition;
    private String englishDefinition;
    String example;
    String original;
    String dataSourceAr;
    String dataSourceEn;
    String synSet;
    String definition;
    List<String> synonyms;
    List<Word> related;
    String isTranslation;
    String isGloss;

    public Word(String arabicDefinition, String englishDefinition, String example, String original, String dataSourceAr, String dataSourceEn, String synSet, String definition, List<String> synonyms, List<Word> related, String isTranslation, String isGloss) {
        this.arabicDefinition = arabicDefinition;
        this.englishDefinition = englishDefinition;
        this.example = example;
        this.original = original;
        this.dataSourceAr = dataSourceAr;
        this.dataSourceEn = dataSourceEn;
        this.synSet = synSet;
        this.definition = definition;
        this.synonyms = synonyms;
        this.related = related;
        this.isTranslation = isTranslation;
        this.isGloss = isGloss;
    }

    String getDefinition() {
        return TextUtils.isEmpty(arabicDefinition) ? englishDefinition : arabicDefinition;
    }

    String getSource() {
        return TextUtils.isEmpty(dataSourceAr) ? dataSourceEn : dataSourceAr;
    }

    boolean isTranslation() {
        int num0s = 0;
        for (int i = 0; i<isTranslation.length(); i++) {
            if(isTranslation.charAt(i) == '0') {
                num0s++;
            }
        }
        if (num0s > 1) {
            return false;
        }
        return true;
    }

    boolean isDefinition() {
        Log.e("Madeed", isGloss);
        Log.e("Madeed", isTranslation);
        for (int i = 0; i<isGloss.length(); i++) {
            if(isGloss.charAt(i) == '1') {
                return true;
            }
        }
        return false;
    }


    static Word parseFrom(String original, JSONObject obj) {
        try {
            return new Word(
                    obj.getString("arabicGloss"),
                    obj.getString("englishGloss"),
                    obj.getString("example"),
                    original,
                    obj.getString("dataSourceCacheAr"),
                    obj.getString("dataSourceCacheEn"),
                    obj.getString("arabicWordsCache"),
                    obj.getString("englishWordsCache"),
                    null,
                    null,
                    obj.getString("isTranslation"),
                    obj.getString("isGloss")
                    );
        } catch (JSONException e) {}
        return null;
    }
}