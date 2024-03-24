package top.s0uths1de.tools;

import java.net.URL;
import java.util.Objects;

public class Simplify {
    public static URL urlToString(Class clazz, String url){
        return Objects.requireNonNull(clazz.getResource(url));
    }
}
