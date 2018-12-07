package com.rsa.setool.logview;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Utils {
    public static String getCurrentTimeinGoodForm() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Calendar today = Calendar.getInstance();
        today.add(Calendar.MINUTE, 10);

        Date tDate = today.getTime();
        return format.format(tDate);

    }

    public static String getSevenDaysBeforeToday() {
        Date toDate = Calendar.getInstance().getTime();
        return subtractDays(toDate, 100);


    }

    public static String subtractDays(Date fistDate, int numDays) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Calendar cal = Calendar.getInstance();
        cal.setTime(fistDate);
        cal.add(Calendar.DATE, -7);
        Date subtractedDate = cal.getTime();
        return (format.format(subtractedDate));

    }

    public static String getDateString(DatePicker date) {
        String dateString = date.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(dateString);

        return dateString + "T01:00:00.303Z";
    }

    public static JScrollPane getErrorCodePanel(HashMap<String, String> errorCodeToDesc) {
        JPanel errorPanel = new JPanel(new GridLayout(20, 1));

        for (String code : errorCodeToDesc.keySet()) {
            JLabel l = new JLabel();
            l.setText("<html>" + code + ":" + errorCodeToDesc.get(code));
            l.setFont(null);
            l.setBorder(new LineBorder(Color.black));
            errorPanel.add(l);

        }
        errorPanel.setAutoscrolls(true);
        errorPanel.setFont(new Font("Verdana", Font.PLAIN, 10));
        errorPanel.setBackground(Color.white);
        errorPanel.setForeground(Color.BLUE);


        JScrollPane scrollFrame = new JScrollPane(errorPanel);
        scrollFrame.setBorder(BorderFactory.createTitledBorder("Error details"));

        scrollFrame.setPreferredSize(new Dimension(300, 500));
        return scrollFrame;
    }
}