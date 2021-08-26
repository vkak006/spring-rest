package ls.electric.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Value("${resource.location}")
    private String resourcesLocation;

    @Value("${resource.uri_path}")
    private String resourcesUriPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcesUriPath + "/**")
                .addResourceLocations("file://" + resourcesLocation)
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

//        //파일 종류별 디렉토리 분류 시 사용.
//        List<String> directoryList = Arrays.asList("/image","/txt");
//
//        for(String dir : directoryList) {
//            registry.addResourceHandler(resourcesUriPath + dir + "/**")
//                    .addResourceLocations("file://" + resourcesLocation + dir + "/")
//                    .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
//        }
    }

}
