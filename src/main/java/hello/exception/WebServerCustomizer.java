package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//과거에는 web.xml 에서 에러페이지 처리
//Component 등록
@Component // 스프링부트 자동에러페이지 연결을 위한 주석처리
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //오류나면 was까지 올라간후 was에서 해당 에러페이지 호출
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        //RuntimeException의 자식들도 해당 페이지로 이동됨
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        //에러페이지 등록
        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }
}
