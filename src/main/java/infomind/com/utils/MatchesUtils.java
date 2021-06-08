package infomind.com.utils;

import org.springframework.util.AntPathMatcher;

public class MatchesUtils {

    public static boolean checkAntPattern(String pattern, String inputStr) {
        return matches(pattern, inputStr);
    }

    public static boolean matches(String pattern, String inputStr) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(pattern, inputStr);
    }
}
