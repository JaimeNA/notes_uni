package wrappers;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class LevenshteinWrap {

    private LevenshteinDistance levenstein;

    public LevenshteinWrap() {
        levenstein = LevenshteinDistance.getDefaultInstance();
    }

    public float getDistanceNormalized(String str1, String str2) {

        int max = str1.length();
        if (max < str2.length())
            max = str2.length();

        return 1.0f - (float)levenstein.apply(str1, str2) / max;
    }

}
