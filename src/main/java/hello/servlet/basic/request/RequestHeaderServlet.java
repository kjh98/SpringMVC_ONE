package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
    }

    private void PrintStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - START ---");
        // GET
        System.out.println("request.getMethod() = " + request.getMethod());
        //HTTP/1.1
        System.out.println("request.getProtocol() = " + request.getProtocol());
        //http
        System.out.println("request.getScheme() = " + request.getScheme());
        //http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        //https 사용 유무
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("--- REQUEST-LINE - END ---");
    }

    private void printHeaders ( HttpServletRequest request){
        System.out.println("--- REQUEST-LINE - START ---");
        /* Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + headerName);
        } */
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println("headerName = " + headerName));
        System.out.println("--- REQUEST-LINE - END ---");
    }

    private void printHeaderUtils(HttpServletRequest request){
        System.out.println("-- Header 편의 조회 start --");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerName() = " + request.getServerPort());
        System.out.println();
        
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                        .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()){
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        System.out.println("-- Header 편의 조회 End --");
    }
}
