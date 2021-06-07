package com.example.kim_s_cafe.config.oauth.provider;

import java.util.Map;

public class facebookuserinfor implements oauth2userinfor {
    private Map<String,Object>attributes;//oauth2user의 getattribute받는다

    public facebookuserinfor(Map<String,Object>attributes){
        this.attributes=attributes;
    }
    @Override
    public String getProviderid() {
       
        return (String)attributes.get("id");
    }

    @Override
    public String getProvider() {
       
        return "facebook";
    }

    @Override
    public String getEmail() {
       
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
       
        return (String)attributes.get("name");
    }
}
