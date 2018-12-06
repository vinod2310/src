package com.rsa.setool.logview;

import com.rsa.securidaccess.admin.client.AdminApiClientUtil;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class LogHandler {
    public String generateToken(String adminApiKeyJsonFile, int validPeriodInMin) {
        try {
            String accessToken = AdminApiClientUtil.generateAdminApiJwtString(adminApiKeyJsonFile, validPeriodInMin, (Date) null, (Date) null);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getLogsFromUrl(String token, String url, String startTime, String endTime) {
        try {
            AdminApiClientUtil.exportAllAdminLogs(url, token, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String, String> queryIdr(String url) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        try {
            SSLUtil.turnOffSslChecking();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        try {


            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, String.class, new Object[0]);
            String idrStatusString = responseEntity.getBody();
            return parseIdrStatus(idrStatusString);
        }catch (Exception ex){
            throw ex;
        }


    }
    private static HashMap<String, String> parseIdrStatus(String idrStatus){
        String[] firstSplit = idrStatus.split("\n", 100);
        HashMap<String,String>idrStatusMap = new HashMap<>();
        for(String s: firstSplit){
            if(s.startsWith("#") || s.startsWith("\n") || s.equals("") || s.contains("ServiceList")) continue;
            System.out.println(s);
            idrStatusMap.put(s.split("=")[0], s.split("=")[1]);

        }
        return idrStatusMap;
    }

    public static UserLogExportDTO exportAllAdminLogs(String queryUrl, String accessToken, String startTimeAfter,
                                                      String endTimeOnOrBefore) throws Exception {
        HashMap<String, String> params = new HashMap();
        params.put("startTimeAfter", startTimeAfter);
        params.put("endTimeOnOrBefore", endTimeOnOrBefore);

        System.out.println("startTimeAfter " + startTimeAfter);
        System.out.println("endTimeOnOrBefore" + endTimeOnOrBefore);

        UserLogExportDTO userLogExportDTO = exportAdminLogs(queryUrl, accessToken, params);
        long totalElements = userLogExportDTO.getTotalElements();
        int totalPages = userLogExportDTO.getTotalPages();
        int pageSize = userLogExportDTO.getPageSize();
        UserLogExportDTO totalOutputs = new UserLogExportDTO();
        totalOutputs.setTotalPages(totalPages);
        totalOutputs.setTotalElements(totalElements);
        totalOutputs.setPageSize(pageSize);
        List<UserLogExportEntryDTO> allElements = new ArrayList();
        allElements.addAll(userLogExportDTO.getUserEventLogExportEntries());

        for (int currentPage = 1; currentPage < totalPages; ++currentPage) {
            params.put("pageNumber", (new Integer(currentPage)).toString());
            userLogExportDTO = exportAdminLogs(queryUrl, accessToken, params);
            allElements.addAll(userLogExportDTO.getUserEventLogExportEntries());
        }
        totalOutputs.setUserEventLogExportEntries(allElements);
        return totalOutputs;
    }

    public static UserLogExportDTO exportAdminLogs(String queryUrl, String accessToken, Map<String, String> params) {
        if (queryUrl == null) {
            throw new RuntimeException("adminApiBaseUrl is required");
        } else {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity(getHeaders(accessToken));
            if (params != null && !params.isEmpty()) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(queryUrl);
                Iterator var7 = params.keySet().iterator();

                while (var7.hasNext()) {
                    String key = (String) var7.next();
                    builder.queryParam(key, new Object[]{params.get(key)});
                }

                queryUrl = builder.build().toUriString();
            }


            ResponseEntity<UserLogExportDTO> responseEntity = restTemplate.exchange(queryUrl, HttpMethod.GET, requestEntity, UserLogExportDTO.class, new Object[0]);
            UserLogExportDTO userLogExportDTO = responseEntity.getBody();
            return userLogExportDTO;
        }
    }

    private static HttpHeaders getHeaders(String accessToken) {
        String credential = "Bearer " + accessToken;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", credential);
        return headers;
    }
}
