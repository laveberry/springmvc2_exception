package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.intercepter.LoginIntercepter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //인터셉터
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //경로로 중복제거
        registry.addInterceptor(new LoginIntercepter())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "*/ico", "/templates/error", "/error-page/**"); //오류 페이지 경로
    }

    //필터
    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        //필터 2개 설정 dafault : request
        // type으로 중복제거
        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterFilterRegistrationBean;
    }


}
