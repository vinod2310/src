package com.rsa.setool.logview;

import java.util.HashMap;

public class RestHandler {
    private static final String SERVER_KEY_FILE = "C:\\Users\\balakv3\\IdeaProjects\\Chart1\\400e6021-f14b-4d3c-b4d3-ef55b72891bc.key";

    public static UserLogExportDTO fetchData(String url, String keyFile, String startDate, String endDate) throws Exception {
        LogHandler lh = new LogHandler();

        String token = lh.generateToken(keyFile, 120000);
        System.out.println(token);

        UserLogExportDTO userLogExportDTO = LogHandler.exportAllAdminLogs(url, token,
                Utils.getSevenDaysBeforeToday(), Utils.getCurrentTimeinGoodForm());
        return userLogExportDTO;

    }
    public static HashMap<String, String> fetchIdrStatus(){
        String url ="https://192.168.0.201:443/status";
        try {
            return LogHandler.queryIdr(url);
        } catch (Exception e) {
            HashMap<String,String> errorMap = new HashMap<>();
            errorMap.put("IDR Status", "Error accessing IDR status");
            return errorMap;
        }
    }
    public static void main(String[] args) {
        fetchIdrStatus();

    }

}
