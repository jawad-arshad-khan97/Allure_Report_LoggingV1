import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class RestAssuredRequestLogger implements Filter {

    public RestAssuredRequestLogger(){

    }


    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext){
        String uri = requestSpec.getURI();
        uri = UrlDecoder.urlDecode(uri, Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
        ByteArrayOutputStream requestLog = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(requestLog);
        Set<String> hash_Set = new HashSet();
        hash_Set.add("Blacklistheader");
        String request = RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, LogDetail.ALL, hash_Set, stream, true);
        Reporter.log(request);
        Response response = filterContext.next(requestSpec, responseSpec);
        return response;
    }
}
