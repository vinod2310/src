package com.rsa.setool.logview;

import java.io.Serializable;

public class UserLogExportEntryDTO implements Serializable {
    private static final long serialVersionUID = 1391764869109214819L;
    private String eventId;
    private String eventLogDate;
    private String eventType = "user";
    private String eventLevel;
    private String eventCategory;
    private String serverIPAddress;
    private String application;
    private String tenantId;
    private String customerName;
    private String userId;
    private String sourceIPAddress = "";
    private String eventCode;
    private String eventDescription;
    private String method;
    private String deviceName;
    private String deviceId;
    private String policyId;
    private String policyName;
    private String authenticationDetails;
    private String assuranceLevel;

    private String activityKey;
    private int activityCode;
    private String result;
    private String reasonKey;
    private String message;
    private boolean requiresPublish;
    private Long targetObject1Id;
    private String targetObject1Name;
    private String targetObject1Type;
    private Long targetObject2Id;
    private String targetObject2Name;
    private String targetObject2Type;

    public UserLogExportEntryDTO() {
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventLogDate() {
        return this.eventLogDate;
    }

    public void setEventLogDate(String eventLogDate) {
        this.eventLogDate = eventLogDate;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    public String getServerIPAddress() {
        return this.serverIPAddress;
    }

    public void setServerIPAddress(String serverIPAddress) {
        this.serverIPAddress = serverIPAddress;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }



    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSourceIPAddress() {
        return this.sourceIPAddress;
    }

    public void setSourceIPAddress(String sourceIPAddress) {
        this.sourceIPAddress = sourceIPAddress;
    }



    public String getActivityKey() {
        return this.activityKey;
    }

    public void setActivityKey(String activityKey) {
        this.activityKey = activityKey;
    }

    public int getActivityCode() {
        return this.activityCode;
    }

    public void setActivityCode(int activityCode) {
        this.activityCode = activityCode;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReasonKey() {
        return this.reasonKey;
    }

    public void setReasonKey(String reasonKey) {
        this.reasonKey = reasonKey;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRequiresPublish() {
        return this.requiresPublish;
    }

    public void setRequiresPublish(boolean requiresPublish) {
        this.requiresPublish = requiresPublish;
    }

    public Long getTargetObject1Id() {
        return this.targetObject1Id;
    }

    public void setTargetObject1Id(Long targetObject1Id) {
        this.targetObject1Id = targetObject1Id;
    }

    public String getTargetObject1Name() {
        return this.targetObject1Name;
    }

    public void setTargetObject1Name(String targetObject1Name) {
        this.targetObject1Name = targetObject1Name;
    }

    public String getTargetObject1Type() {
        return this.targetObject1Type;
    }

    public void setTargetObject1Type(String targetObject1Type) {
        this.targetObject1Type = targetObject1Type;
    }

    public Long getTargetObject2Id() {
        return this.targetObject2Id;
    }

    public void setTargetObject2Id(Long targetObject2Id) {
        this.targetObject2Id = targetObject2Id;
    }

    public String getTargetObject2Name() {
        return this.targetObject2Name;
    }

    public void setTargetObject2Name(String targetObject2Name) {
        this.targetObject2Name = targetObject2Name;
    }

    public String getTargetObject2Type() {
        return this.targetObject2Type;
    }

    public void setTargetObject2Type(String targetObject2Type) {
        this.targetObject2Type = targetObject2Type;
    }

    public String toString() {
        return "UserLogExportEntryDTO{eventId=" + this.eventId + ", eventLogDate='" + this.eventLogDate + '\'' + ", eventType='" +
                this.eventType + '\'' + ", serverURL='" +  ", serverIPAddress='" + this.serverIPAddress +
                '\'' + ", application='" + this.application + '\'' + ", customerId=" +  ", customerName='"
                + this.customerName + '\'' + ", sourceIPAddress='" + this.sourceIPAddress + '\'' + ", adminUserName='" +
                this.userId + '\''  + ", activityKey='" +
                this.activityKey + '\'' + ", activityCode=" + this.activityCode + ", result=" + this.result + ", reasonKey=" +
                this.reasonKey + ", message='" + this.message + '\'' + ", requiresPublish=" + this.requiresPublish + ", " +
                "targetObject1Id=" + this.targetObject1Id + ", targetObject1Name='" + this.targetObject1Name + '\'' + ", " +
                "targetObject1Type='" + this.targetObject1Type + '\'' + ", targetObject2Id=" + this.targetObject2Id + ", " +
                "targetObject2Name='" + this.targetObject2Name + '\'' + ", targetObject2Type='" + this.targetObject2Type + '\'' + '}';
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }


    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getAuthenticationDetails() {
        return authenticationDetails;
    }

    public void setAuthenticationDetails(String authenticationDetails) {
        this.authenticationDetails = authenticationDetails;
    }

    public String getAssuranceLevel() {
        return assuranceLevel;
    }

    public void setAssuranceLevel(String assuranceLevel) {
        this.assuranceLevel = assuranceLevel;
    }
}
