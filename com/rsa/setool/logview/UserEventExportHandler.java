package com.rsa.setool.logview;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;


public class UserEventExportHandler  {
    public static final String[] HEADER_LIST = new String[]{"eventId", "eventLogDate", "eventType",
            "serverURL", "Application", "customerName", "SourceIP", "activityDetail", "userId"};
    private CSVPrinter csvPrinter;

    public UserEventExportHandler() {
    }

    public void exportUserLog(UserLogExportDTO adminLogExportDTO, String csvFilename) throws Exception {
        System.out.println("The admin logs are exported to file: " + csvFilename);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(csvFilename), "UTF-8");
        outputStreamWriter.flush();
        Writer writer = new BufferedWriter(outputStreamWriter);
        this.csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        if (adminLogExportDTO != null) {
            this.csvPrinter.printRecord(new Object[]{"Total Pages:", adminLogExportDTO.getTotalPages()});
            this.csvPrinter.printRecord(new Object[]{"Total Elements:", adminLogExportDTO.getTotalElements()});
            this.csvPrinter.printRecord(new Object[]{"Page Size:", adminLogExportDTO.getPageSize()});
            String[] line = new String[HEADER_LIST.length];
            this.csvPrinter.printRecord((Object[])line);
            this.csvPrinter.printRecord((Object[])HEADER_LIST);
            this.csvPrinter.printRecord((Object[])line);
            List<UserLogExportEntryDTO> elements = adminLogExportDTO.getUserEventLogExportEntries();
            Iterator var7 = elements.iterator();

            while(var7.hasNext()) {
                UserLogExportEntryDTO e = (UserLogExportEntryDTO)var7.next();
                line = new String[HEADER_LIST.length];
                line[0] = e.getEventId().toString();
                line[1] = e.getEventLogDate();
                line[2] = e.getEventType();
                line[3] = e.getServerIPAddress();
                line[4] = e.getApplication();
                line[5] = e.getCustomerName();
                line[6] = e.getSourceIPAddress();
                line[7] = e.getEventDescription();
                line[8] = e.getUserId();

                this.csvPrinter.printRecord((Object[])line);
                this.csvPrinter.flush();
            }
            csvPrinter.close();
            Desktop.getDesktop().open(new File(csvFilename));

        }

    }
}
