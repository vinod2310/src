package com.rsa.setool.logview;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserEventLogChartMouseListener implements ChartMouseListener {
    private UserEventHandler eventHandler;
    private DefaultPieDataset pieDataSet;
    private JFreeChart chart;
    private JScrollPane errorCodePane;
    private JFrame frame;
    public UserEventLogChartMouseListener(UserEventHandler eventHandler,
                                          DefaultPieDataset pieDataSet,
                                          JFreeChart chart, JFrame frame) {

        this.eventHandler = eventHandler;
        this.pieDataSet = pieDataSet;
        this.chart = chart;
        this.frame = frame;
    }



    @Override
    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
        HashMap<String, String> errorCodeToDesc = new HashMap<>();
        if(SwingUtilities.isRightMouseButton(chartMouseEvent.getTrigger())){
            ArrayList<Integer> newValues = eventHandler.getNumErrorsAndNotices();

            List<String> dd = pieDataSet.getKeys();
            for(String s : dd){
                pieDataSet.remove(s);

            }
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setSectionPaint(1, Color.red);
            piePlot.setSectionPaint(0, Color.blue);
            pieDataSet.setValue("Notices", newValues.get(1));
            pieDataSet.setValue("Errors", newValues.get(0));

        }else {
            PieSectionEntity pe = (PieSectionEntity) chartMouseEvent.getEntity();
            if(pe == null){
                return;
            }

            pieDataSet.remove("Errors");
            pieDataSet.remove("Notices");
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setSectionPaint(1, Color.red);
            piePlot.setSectionPaint(0, Color.blue);
            if (pe.getSectionKey().equals("Errors")) {
                HashMap<String, Integer> errorMap = eventHandler.getErrorNumbersByCatagory();
                int i = 0;Color c = new Color(100, 52, 28);
                int r = 0, g = 0, b = 0;
                for (String code : errorMap.keySet()) {
                    piePlot.setSectionPaint(i, c);
                    i++; r += 10; g += 10; b+= 10;
                    c = new Color(r,g,b);
                    pieDataSet.setValue(code, errorMap.get(code));
                    errorCodeToDesc.put(code, UserEventsDB.getEventDescreption(Integer.parseInt(code)));
                }
            } else {

                int i = 0;Color c = new Color(83, 100, 35);
                int r = 0, g = 0, b = 0;


                HashMap<String, Integer> noticesMap = eventHandler.getNoticeNumbersByCatagory();
                for (String code : noticesMap.keySet()) {
                    pieDataSet.setValue(code, noticesMap.get(code));
                    piePlot.setSectionPaint(i, c);
                    i++; r += 10; g += 10; b+= 10;
                    c = new Color(r,g,b);
                    errorCodeToDesc.put(code, UserEventsDB.getEventDescreption(Integer.parseInt(code)));
                }
            }

        }
        addErrorCodePane(errorCodeToDesc);


    }
    private void addErrorCodePane(HashMap<String, String> errorCodeToDesc){
        if(errorCodePane != null){
            frame.remove(errorCodePane);
        }
        errorCodePane = Utils.getErrorCodePanel(errorCodeToDesc);
        errorCodePane.setPreferredSize(new Dimension(400,535));
        frame.add(errorCodePane, DisplayConstraintManager.getConstraint("noticeArea"));
        frame.pack();
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

    }
}

