package com.example.kim_s_cafe.config.oauth.provider.enummodel;



public enum enumvo{
    
  google("google");
  private String company;

  enumvo(String company){
    this.company=company;
  }
  
  public String getcompany() {
    return this.company;
  }
  


}