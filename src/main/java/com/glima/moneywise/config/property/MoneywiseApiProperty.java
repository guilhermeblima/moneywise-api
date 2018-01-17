package com.glima.moneywise.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Guilherme on 17/01/2018.
 */
@ConfigurationProperties("moneywise")
public class MoneywiseApiProperty {

    private Security security = new Security();

    public Security getSecurity(){
        return  security;
    }

    public static class Security{

        private boolean enableHttps;
        private String originAllowed;

        public String getOriginAllowed() {
            return originAllowed;
        }

        public void setOriginAllowed(String originAllowed) {
            this.originAllowed = originAllowed;
        }

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }


}
