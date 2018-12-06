package com.rsa.setool.logview;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DownloadActionListener implements ActionListener {
    private String keyFile;
    private String outputCsvFile;
    private DatePicker start, end;
    private String serverUrl;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("You clicked a button!");
        if(start.getDate() == null || end.getDate() == null){
            return;
        }
        try {
            UserLogExportDTO userLogExportDTO = RestHandler.fetchData(serverUrl, keyFile, Utils.getDateString(start),
                    Utils.getDateString(end));
            UserEventExportHandler eh = new UserEventExportHandler();
            eh.exportUserLog(userLogExportDTO, outputCsvFile);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return;
        }

    public void setKeyFile(String keyFile) {
        this.keyFile = keyFile;
    }

    public void setOutputCsvFile(String outputCsvFile) {
        this.outputCsvFile = outputCsvFile;
    }

    public void setStart(DatePicker start) {
        this.start = start;
    }

    public void setEnd(DatePicker end) {
        this.end = end;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }


}