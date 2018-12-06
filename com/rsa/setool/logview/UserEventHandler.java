package com.rsa.setool.logview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UserEventHandler {
    private final UserLogExportDTO userlogExportDto;

    public UserEventHandler(UserLogExportDTO dto) {
        userlogExportDto = dto;
    }

    public ArrayList<Integer> getNumErrorsAndNotices() {
        ArrayList<Integer> errorCodes = UserEventsDB.getAllErrors();
        int numErrors = 0, numNotices = 0;
        for (UserLogExportEntryDTO entry : userlogExportDto.getUserEventLogExportEntries()) {
            if (errorCodes.contains(Integer.parseInt(entry.getEventCode()))) {
                numErrors++;
            } else {
                numNotices++;
            }


        }
        ArrayList<Integer> errorsAndNotices = new ArrayList<Integer>();
        errorsAndNotices.add(numErrors);
        errorsAndNotices.add(numNotices);
        return errorsAndNotices;
    }

    public HashMap<String, Integer> getErrorNumbersByCatagory() {
        ArrayList<Integer> errorCodes = UserEventsDB.getAllErrors();
        HashMap<String, Integer> errorsMap = new HashMap<>();
        for (UserLogExportEntryDTO entry : userlogExportDto.getUserEventLogExportEntries()) {
            if(errorCodes.contains(Integer.parseInt(entry.getEventCode()))) {
                errorsMap = addToMap(errorsMap, entry.getEventCode());
            }

        }
        return errorsMap;
    }

    public HashMap<String, Integer> getNoticeNumbersByCatagory() {
        ArrayList<Integer> noticeCodes = UserEventsDB.getAllNotices();
        HashMap<String, Integer> noticesMap = new HashMap<>();
        for (UserLogExportEntryDTO entry : userlogExportDto.getUserEventLogExportEntries()) {
            if(noticeCodes.contains(Integer.parseInt(entry.getEventCode()))) {
                noticesMap = addToMap(noticesMap, entry.getEventCode());
            }

        }
        return noticesMap;
    }

    public HashMap<String, String> errorsByUser(String userName) {
        HashMap<String,String> userErrors = new HashMap<>();
        for (UserLogExportEntryDTO entry : userlogExportDto.getUserEventLogExportEntries()) {
            if (entry.getUserId().equals(userName)) {
                userErrors.put(entry.getEventCode(), entry.getEventDescription());
            }
        }
        return userErrors;

    }


    private HashMap<String, Integer> addToMap(HashMap<String, Integer> errorsMap, String eventCode) {
        errorsMap.putIfAbsent(eventCode, 0);
        errorsMap.computeIfPresent(eventCode, (k, v) -> v + 1);
        return errorsMap;
    }

    public String[] getAllUsers() {
        Set<String> users = new HashSet<String>();
        for (UserLogExportEntryDTO entry : userlogExportDto.getUserEventLogExportEntries()) {
            users.add(entry.getUserId());
        }
        return users.toArray(new String[0]);
    }
}
