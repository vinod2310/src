package com.rsa.setool.logview;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.lgooddatepicker.components.DatePicker;
import org.jfree.chart.*;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Main {

    private static final String DEFAULT_URL = "https://access-demo.securid.com/AdminInterface/restapi/v1/usereventlog/exportlogs";
    private static final String DEFAULT_CSV_FILE = "C:\\Users\\balakv3\\IdeaProjects\\Chart1\\userlogs.csv";
    private static DefaultPieDataset pieDataSet;
    private static JFreeChart chart;
    private static JFrame frame;
    private static ChartPanel chartPanel;
    private UserEventHandler eventHandler;
    private Label messageField;
    private JScrollPane errorCodePane;
    private JPanel csvPane;
    private JPanel idrPane;
    private String url = null;
    private String keyFile = null;
    private Button downloadButton;
    private String companyName;
    private String csvFileLocation;
    JTabbedPane mainTabbedPane;
    private DownloadActionListener csvDownloadActionListener;


    public Main(String propFilePath) {
        mainTabbedPane = new JTabbedPane();
        frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());


    }
    private static DefaultPieDataset createErrorsAndSuccess(ArrayList<Integer> errorsAndNotices) {
        if (errorsAndNotices.isEmpty()) {
            return new DefaultPieDataset();
        }
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Errors", errorsAndNotices.get(0));
        dataset.setValue("Notices", errorsAndNotices.get(1));
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset, String companyName) {
        JFreeChart chart = ChartFactory.createPieChart(
                companyName,   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    private void setDateFields(JPanel csvGroup){

        GridBagConstraints datePickerHintC = new GridBagConstraints();
        datePickerHintC.gridx = 0;
        datePickerHintC.gridy = 1;
        datePickerHintC.anchor  = GridBagConstraints.NORTHWEST;
        csvGroup.add(new Label("Download CSV"), datePickerHintC);

        GridBagConstraints date1C = new GridBagConstraints();
        date1C.gridx = 1;
        date1C.gridy = 1;
        date1C.anchor  = GridBagConstraints.NORTH;
        DatePicker start = new DatePicker();
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, -7);
        LocalDate date = LocalDate.of(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH) + 1,
                today.get(Calendar.DAY_OF_MONTH));
        start.setDate(date);
        DatePicker end = new DatePicker();
        end.setDateToToday();

        csvGroup.add(start, date1C);
        GridBagConstraints date2C = new GridBagConstraints();
        date2C.gridx = 2;
        date2C.gridy = 1;
        csvGroup.add(end, date2C);
        GridBagConstraints downloadButtonC = new GridBagConstraints();
        downloadButtonC.gridx = 3;
        downloadButtonC.gridy = 1;

        downloadButton = new Button("Download");
        csvDownloadActionListener.setStart(start);
        csvDownloadActionListener.setEnd(end);

        downloadButton.addActionListener(csvDownloadActionListener);
        csvGroup.add(downloadButton, downloadButtonC);

    }

    private void addUserLookup(){
        JPanel userLookup = new JPanel();

        GridBagConstraints userLookupC = new GridBagConstraints();
        userLookupC.gridx =2 ;
        userLookupC.gridy = 2;
        if(eventHandler != null) {

            String[] ss = eventHandler.getAllUsers();
            JComboBox patternList = new JComboBox(ss);
            patternList.setEditable(true);
            patternList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HashMap<String, String> hmap = eventHandler.errorsByUser((String) patternList.getSelectedItem());
                    addErrorCodePane(hmap);
                    addCsvPane(4);
                    frame.pack();
                }
            });
            GridBagConstraints userlookupTextC = new GridBagConstraints();
            userlookupTextC.gridx = 0;
            userlookupTextC.gridy = 2;
            userLookup.add(new Label("Lookup specific users     "), userlookupTextC);
            userLookup.add(patternList, userLookupC);
            frame.add(userLookup,DisplayConstraintManager.getConstraint("userLookupFields"));
            frame.pack();
        }

    }

    private JPanel getCsvComponents(){
        JPanel csvGroup = new JPanel();
        csvGroup.setLayout(new GridBagLayout());
        setDateFields(csvGroup);


        csvGroup.setBorder( BorderFactory.createLineBorder(Color.black) );
        return csvGroup;


    }


    private JScrollPane getErrorCodePanel(HashMap<String, String> errorCodeToDesc){
        JPanel errorPanel = new JPanel(new GridLayout(10,1));

        for(String code : errorCodeToDesc.keySet()){
            JLabel l = new JLabel();
            l.setText("<html>"+code+ ":"+errorCodeToDesc.get(code));
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

        scrollFrame.setPreferredSize(new Dimension( 500,300));
        return scrollFrame;

    }
    private void addErrorCodePane(HashMap<String, String> errorCodeToDesc){
        if(errorCodePane != null){
            mainTabbedPane.remove(errorCodePane);
        }
        errorCodePane = getErrorCodePanel(errorCodeToDesc);
        errorCodePane.setPreferredSize(new Dimension(400,410));
//        frame.add(errorCodePane, DisplayConstraintManager.getConstraint("noticeArea"));
        mainTabbedPane.addTab("ErrorCodes", errorCodePane);
    }
    private  void addCsvPane(int gridWidth){
        if(csvPane != null){
            mainTabbedPane.remove(csvPane);

        }
        csvPane = getCsvComponents();
        mainTabbedPane.addTab("CVS Stuff" , csvPane);
    }

    private void addIdrStatus(){
        HashMap<String, String> idrStatusMap = RestHandler.fetchIdrStatus();
        if(idrPane != null){
            mainTabbedPane.remove(idrPane);

        }
        idrPane = new JPanel();
        idrPane.setPreferredSize(new Dimension( 500,500));
        idrPane.setLayout(new GridLayout(idrStatusMap.size(),1));
        idrPane.setBackground(Color.GREEN);

        idrPane.setAutoscrolls(true);
        idrPane.setFont(new Font("Verdana", Font.PLAIN, 10));
        if(idrStatusMap.containsKey("GlobalStatus.status") && idrStatusMap.get("GlobalStatus.status").equals("OK")){
            idrPane.setBackground(Color.white);
            idrPane.setBackground(new Color(128, 183, 148));

        }else{
            idrPane.setBackground(new Color(224, 185, 179));
        }

        idrPane.setForeground(Color.BLUE);

        for(String key : idrStatusMap.keySet()){
            JLabel l = new JLabel();
            l.setText(key+ ":"+idrStatusMap.get(key));
            l.setFont(null);
            //l.setBorder(new LineBorder(Color.black));
            idrPane.add(l);
        }
        mainTabbedPane.addTab("IDR Stuff" , idrPane);

    }


    public JPanel createDemoPanel(ArrayList<Integer> errorsAndNotices) {

        chart = createChart(createErrorsAndSuccess(errorsAndNotices), "Log View");
        chartPanel = new ChartPanel(chart);
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setSectionPaint(1, Color.red);
        piePlot.setSectionPaint(0, Color.blue);
        pieDataSet = (DefaultPieDataset) piePlot.getDataset();
        chartPanel.setLayout(null);
        JSlider dateRange = new JSlider(JSlider.HORIZONTAL,
                0, 100, 10);
        dateRange.addChangeListener(new SliderEventListener(pieDataSet));


        mainTabbedPane.addTab("Chart",chartPanel);

        frame.add(messageField, DisplayConstraintManager.getConstraint("textField"));

        //frame.add(chartPanel, DisplayConstraintManager.getConstraint("chart"));
        addCsvPane(2);
        addUserLookup();
        chartPanel.setPopupMenu(null);
        addIdrStatus();


        chartPanel.addChartMouseListener(new ChartMouseListener() {

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





                frame.repaint();
//                addCsvPane(4);
                frame.pack();

            }

            @Override
            public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

            }
        });

        frame.add(mainTabbedPane);
        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return chartPanel;
    }

    private String getCompanyName() {
        return companyName;
    }

    public static void main(String[] args) {
        String prpFile = args[0];
        Main demo = new Main(prpFile);
        demo.init(prpFile);
        //demo.createDemoPanel(new ArrayList<Integer>());



    }


    private UserEventHandler eventHandler() {
        return eventHandler;
    }

    public void init(String propFilePath) {

        Properties prop = new Properties();
        messageField = new Label("Initialising...");
        csvDownloadActionListener = new DownloadActionListener();
//        createTabbed(new ArrayList<Integer>());
        createDemoPanel(new ArrayList<Integer>());


        InputStream is = getClass().getClassLoader().getResourceAsStream(propFilePath);
        if(is != null ){
            try {
                prop.load(is);
                url = (String) prop.get("url");
                keyFile = (String) prop.get("keyfile");
                companyName = (String) prop.get("companyName");
                frame.setTitle(companyName);
                csvFileLocation = (String)prop.get("csvFile");
                URL iconFileUrl = getClass().getResource( "/"+(String)prop.get("iconfile"));
                Image image = Toolkit.getDefaultToolkit().getImage( iconFileUrl );
                frame.setIconImage(image);
                frame.repaint();

                if(url == null || keyFile == null){
                    messageField.setText("Error: Mandatory properties not supplied");
                    downloadButton.setEnabled(false);
                    return;

                }
                csvDownloadActionListener.setServerUrl(url);
                csvDownloadActionListener.setKeyFile(keyFile);
                csvDownloadActionListener.setOutputCsvFile(csvFileLocation);

            } catch (Exception e) {

                messageField.setText("Error: Initialisation failed" + ":"+e.getCause().getMessage());
                downloadButton.setEnabled(false);
                return;

            }
        }else{
            messageField.setText("Error: Could not find the property file");

        }

        messageField.setText("Fetching data...");


        try {

            eventHandler = new UserEventHandler(RestHandler.fetchData(url, keyFile, Utils.getCurrentTimeinGoodForm() ,
                    Utils.getSevenDaysBeforeToday()));
            ArrayList<Integer> newValues = eventHandler.getNumErrorsAndNotices();
            pieDataSet.setValue("Notices", newValues.get(1));
            pieDataSet.setValue("Errors", newValues.get(0));
            messageField.setText("");
            addUserLookup();
            frame.repaint();


        } catch (Exception e) {
            messageField.setText(e.getCause().getMessage());
            System.out.println(url + " " + Utils.getCurrentTimeinGoodForm() + " " + Utils.getSevenDaysBeforeToday());
        }


    }

    static class SliderEventListener implements ChangeListener {
        DefaultPieDataset dataSet;

        public SliderEventListener(DefaultPieDataset ds) {
            dataSet = ds;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            dataSet.setValue("IPhone 5s", new Double(40));

        }
    }
}