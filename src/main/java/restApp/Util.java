package restApp;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by vlad on 14.03.2017.
 */
public class Util {

    public static String extractRequestBody(HttpServletRequest request) {
        try {
            return  request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }catch (IOException e){
            return "";
        }
    }
}
