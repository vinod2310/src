package com.rsa.setool.logview;

import java.util.HashMap;

public class RestHandler {

    public static UserLogExportDTO fetchData(String url, String keyFile, String startDate, String endDate) throws Exception {
        LogHandler lh = new LogHandler();

        String token = lh.generateToken(keyFile, 120000);
        System.out.println(token);

        UserLogExportDTO userLogExportDTO = LogHandler.exportAllAdminLogs(url, token,
                Utils.getSevenDaysBeforeToday(), Utils.getCurrentTimeinGoodForm());
        return userLogExportDTO;

    }
    public static HashMap<String, String> fetchIdrStatus(String idrUrl){
        String url ="https://"+idrUrl+":443/status";
        try {
            return LogHandler.queryIdr(url);
        } catch (Exception e) {
            HashMap<String,String> errorMap = new HashMap<>();
            errorMap.put("IDR sStatus", "Error accessing IDR status");
            return errorMap;
        }
    }


}
