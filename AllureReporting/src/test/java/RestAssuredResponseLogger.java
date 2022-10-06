import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;


public class RestAssuredResponseLogger implements Filter {



    public RestAssuredResponseLogger(){

    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext){
        ByteArrayOutputStream requestLog = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(requestLog);
        Set<String> hash_Set = new HashSet();
        hash_Set.add("Blacklistheader");
        Response response = filterContext.next(requestSpec, responseSpec);
        String responseLog = ResponsePrinter.print(response, response, stream, LogDetail.ALL, true, hash_Set);
        Reporter.log(responseLog);
        return response;
    }
}
