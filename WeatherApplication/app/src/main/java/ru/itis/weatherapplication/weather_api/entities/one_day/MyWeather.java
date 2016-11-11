
package ru.itis.weatherapplication.weather_api.entities.one_day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

import ru.itis.weatherapplication.weather_api.exceptions.NotLoadedException;

@Generated("org.jsonschema2pojo")
public class MyWeather {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = new ArrayList<List>();
    private NotLoadedException notLoadedException;

    /**
     * 
     * @return
     *     The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * 
     * @param cnt
     *     The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<List> getList() throws NotLoadedException {
        if (notLoadedException != null){
            throw notLoadedException;
        }
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public MyWeather(java.util.List<List> list) {
        this.list = list;
    }

    public MyWeather(NotLoadedException notLoadedException) {
        this.notLoadedException = notLoadedException;
    }

    public boolean isIncludeError(){
        return notLoadedException != null;
    }

    public NotLoadedException getNotLoadedException() {
        return notLoadedException;
    }

}
