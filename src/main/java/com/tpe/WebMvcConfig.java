package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration//bu classta configuration ayarları yapıcam
@ComponentScan("com.tpe")//opsiyonel
@EnableWebMvc//MVC için config etkinleştirmek için
public class WebMvcConfig implements WebMvcConfigurer {

    //view name'e karşılık gelen view dosyasının çözümlenmesini: viewResolver
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);//JavaStandartTagLibrary ==> jsb dosyaları icinde daha az kod yazmamızı sağlar
        resolver.setPrefix("/WEB-INF/views/");//view dosyaları nerde(dizin), yerini belirtiyoruz
        resolver.setSuffix(".jsp");//view dosyalarının uzantısı nedir, ne kullanıyorum
        return resolver;
    }

    //css, image static olan kaynaklarımızın(resorses) dispatcher'a gönderilmesine gerek yok.
    //http://localhost:8080/SpringMVC/resources/css/style.css
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//kaynaklarımızı handle eden method
        registry.addResourceHandler("/resources/**").//bu path'teki kaynakları (image css dosyaları..) static olarak sun
                addResourceLocations("/resources/").//kaynakların yeri
                setCachePeriod(0);//cachleme için belirli bir periyot süresi verilebilir.

    }


//    //css,image statik olan kaynakların dispatchera gönderilmesine gerek yok
//    //ex:http://localhost:8080/SpringMVC/resources/css/style.css
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").//bu pathdeki kaynakları statik olarak sun
//                addResourceLocations("/resources/").//kaynakların yeri
//                setCachePeriod(0);//cacheleme için belirli bir periyod süresi verilebilir.
//    }
}