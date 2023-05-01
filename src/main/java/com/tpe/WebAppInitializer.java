package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml yerine bu classı kullanıyoruz.
//dispatcher servletın tanımlanması/başlatılması ve konfigrasyonu ile başlıyoruz
//AbstractAnnotationConfigDispatcherServletInitializer ==> dispatcher servletin başlatılmasını, config ayarlarının bulunduğu
//dosyanın yerinin gösterilmesini sağlar
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
/*
Dispatcher: Servlet WebAppContext -> Controller (requestleri karşılayan), Handler Mapping, View Resolver bileşenleri var
            Root WebAppContext -> services, repository bileşenleri var
 */

    @Override//db'ye erişim(hibernate/jdbc) için gerekli config ayarlarını içeren class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override//Controller (requestleri karşılayan), Handler Mapping, View Resolver(Spring Mvc) ile ilgili config ayarlarını içeren class
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
          WebMvcConfig.class
        };
    }

    @Override//hangi url ile gelen istekler(requestler) servlet tarafından karşılanacak
    protected String[] getServletMappings() {
        return new String[]{"/"};// "/" pathiyle gelen url'leri karşıla
    }



}
