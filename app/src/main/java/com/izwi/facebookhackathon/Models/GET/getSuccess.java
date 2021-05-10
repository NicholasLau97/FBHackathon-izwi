package com.izwi.facebookhackathon.Models.GET;
/*
 * Lau Hui Sheng CONFIDENTIAL
 * __________________
 *  Date : 8/5/2021 20:00 MYT
 *  [2021] - [2021] Lau Hui Sheng email: huisheng97.lhs.business@gmail.com
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Lau Hui Sheng and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Lau Hui Sheng
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Lau Hui Sheng.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getSuccess {
    private String text;
    private IntentsData[] intents;
    private Map<String, List<MetricData>> entities = new HashMap<String, List<MetricData>>();



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public IntentsData[] getIntents() {
        return intents;
    }

    public void setIntents(IntentsData[] intents) {
        this.intents = intents;
    }

    public Map<String, List<MetricData>> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, List<MetricData>> entities) {
        this.entities = entities;
    }

    public boolean getintentNameEqual(String name){
        return intents[0].getName().equals(name);
    }
    public MetricData getMetricByName(String name){
        for(int i=0;i<entities.size();i++){
            if(entities.get(name)!=null){
                return entities.get(name).get(0);
            }
        }
        return null;
    }
    public boolean isMetricExists(String name){

        for(int i=0;i<entities.size();i++){
            if(entities.get(name)!=null){
                return true;
            }
        }
        return false;
    }
}
