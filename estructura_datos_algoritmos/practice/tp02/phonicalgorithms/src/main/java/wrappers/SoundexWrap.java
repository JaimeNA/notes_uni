package wrappers;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public class SoundexWrap {

    private Soundex soundex;

    public SoundexWrap(){
        soundex = new Soundex();
    }

    public String getWordCode(String in_str) {

        return soundex.encode(in_str);

    }

    public float getLikeness(String s1, String s2) {

        int sim = 0;

        try {
            sim = soundex.difference(s1, s2);
        } catch(EncoderException e){
            throw new RuntimeException();
        }

        return (float)sim / 4.0f;
    }

}
