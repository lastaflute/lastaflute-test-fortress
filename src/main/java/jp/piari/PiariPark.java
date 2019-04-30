package jp.piari;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.lastaflute.core.security.PrimaryCipher;
import org.lastaflute.di.util.LdiArrayUtil;

public class PiariPark implements Depart {

    public void addIntArray(int[] intAry) {
        System.out.println("intAry: " + LdiArrayUtil.toList(intAry));
    }

    public void addStringArray(String[] StrAry) {
        System.out.println("strAry: " + Arrays.asList(StrAry));
    }

    public void addList(List<String> strList) {
        System.out.println("strList: " + strList);
    }

    public void addMap(Map<String, String> strMap) {
        System.out.println("strMap: " + strMap);
    }

    public void addString(String str) {
        System.out.println("str: " + str);
    }

    public void addConfigProp(String configProp) {
        System.out.println("configProp: " + configProp);
    }

    public void addCipher(PrimaryCipher cipher) {
        System.out.println("cipher: " + cipher);
    }
}
