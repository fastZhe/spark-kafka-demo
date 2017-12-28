package com.hz.streaming.message;


import java.io.Serializable;
import java.util.Map;

public interface Message extends Serializable{
    void setConfig(Map<String,String> config);
    void exec(Object obj);

}
