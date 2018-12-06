package com.rsa.setool.logview;

import java.io.Serializable;
import java.util.List;
class GlobalStatus{
    private String status;
    private String timeLastUpdated;

    public String getTimeLastUpdated() {
        return timeLastUpdated;
    }

    public void setTimeLastUpdated(String timeLastUpdated) {
        this.timeLastUpdated = timeLastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
public class IdrStatusDTO implements Serializable {
    private static final long serialVersionUID = 8111440613331162759L;

    private GlobalStatus globalSttaus;


    public GlobalStatus getGlobalSttaus() {
        return globalSttaus;
    }

    public void setGlobalSttaus(GlobalStatus globalSttaus) {
        this.globalSttaus = globalSttaus;
    }
}