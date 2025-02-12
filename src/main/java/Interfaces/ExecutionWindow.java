/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Gabriel
 */
import CPU.CPU;
import EDD.ProcessList;
import EDD.Queue;
import Process.Process;
import EDD.Queue.Node;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Settings.Settings;
import Clock.ClockManager;

public class ExecutionWindow extends JFrame {
    private Queue<Process> readyQueue;
    private Queue<Process> blockedQueue;
    private ProcessList exitList;
    private CPU[] cpus;
    private JPanel cpuPanel, infoPanel; 
    private JScrollPane readyQueuePanel, blockedQueuePanel, completedProcessesPanel;
    private Settings settings;
    private double cycles;
    private String planningAlgorithm;
    private JLabel cyclesLabel, planningAlgorithmLabel;
    private ClockManager clockManager;
    

    private ScheduledExecutorService scheduler;

    private static final Color COLOR_PRIMARY = new Color(48, 63, 159);
    private static final Color COLOR_SECONDARY = new Color(255, 87, 34);
    private static final Color COLOR_BACKGROUND = new Color(245, 245, 245);
    private static final Color COLOR_TEXT = new Color(33, 33, 33);
    private static final Color COLOR_ACCENT = new Color(0, 150, 136);

    public ExecutionWindow(Queue<Process> rq, Queue<Process> bq, ProcessList el, CPU[] c, Settings settings, ClockManager clockManager) {
        this.readyQueue = rq;
        this.blockedQueue = bq;
        this.exitList = el;
        this.cpus = c;
        this.settings =settings;
        this.clockManager=clockManager;
        this.cycles = clockManager.getClockCycles();
        this.planningAlgorithm = settings.getPlanningAlgorithm();

        setTitle("Execution Window");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COLOR_BACKGROUND);

        // Panel cycles & planningAlgorithm
        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        infoPanel.setBackground(COLOR_BACKGROUND);
        infoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_PRIMARY));

        cyclesLabel = new JLabel("Instruction cycles: " + cycles);
        planningAlgorithmLabel = new JLabel("Planning algorithm: " + planningAlgorithm);

        Font font = new Font("SansSerif", Font.BOLD, 14);
        cyclesLabel.setFont(font);
        planningAlgorithmLabel.setFont(font);

        infoPanel.add(cyclesLabel);
        infoPanel.add(Box.createHorizontalStrut(40));
        infoPanel.add(planningAlgorithmLabel);

        add(infoPanel, BorderLayout.NORTH);

        // Panel de CPUs
        cpuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        cpuPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_PRIMARY, 2), "CPUs"));
        cpuPanel.setBackground(COLOR_BACKGROUND);

        for (CPU cpu : cpus) {
            cpuPanel.add(createCPUInfoPanel(cpu));
        }

        add(cpuPanel, BorderLayout.CENTER);

        // queue panels
        JPanel queuePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        queuePanel.setBackground(COLOR_BACKGROUND);

        readyQueuePanel = createScrollablePanel("Ready Queue");
        blockedQueuePanel = createScrollablePanel("Blocked Queue");
        completedProcessesPanel = createScrollablePanel("Completed Processes");

        queuePanel.add(readyQueuePanel);
        queuePanel.add(blockedQueuePanel);
        queuePanel.add(completedProcessesPanel);

        add(queuePanel, BorderLayout.SOUTH);

        updateWindow();
        startAutoUpdate();
        setVisible(true);
    }

    // scrollbarPanels 
    private JScrollPane createScrollablePanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_PRIMARY, 2), title));
        panel.setBackground(COLOR_BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 650));
        return scrollPane;
    }

    // each CPU panel
    private JPanel createCPUInfoPanel(CPU cpu) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(150, 100));

        JLabel idLabel = new JLabel("CPU " + cpu.getID());
        JLabel processLabel = new JLabel("Process: " + cpu.getRunningProcess());
        JLabel pcLabel = new JLabel("PC: " + cpu.getPC());
        JLabel marLabel = new JLabel("MAR: " + cpu.getMAR());

        Font boldFont = new Font("SansSerif", Font.BOLD, 12);
        idLabel.setFont(boldFont);
        processLabel.setFont(boldFont);
        pcLabel.setFont(boldFont);
        marLabel.setFont(boldFont);

        panel.add(idLabel);
        panel.add(processLabel);
        panel.add(pcLabel);
        panel.add(marLabel);

        return panel;
    }

    public void updateWindow() {
        cyclesLabel.setText("Instruction cycles: " + clockManager.getClockCycles());
        planningAlgorithmLabel.setText("Planning algorithm: " + settings.getPlanningAlgorithm());

        JPanel readyPanel = (JPanel) readyQueuePanel.getViewport().getView();
        JPanel blockedPanel = (JPanel) blockedQueuePanel.getViewport().getView();
        JPanel completedPanel = (JPanel) completedProcessesPanel.getViewport().getView();

        readyPanel.removeAll();
        blockedPanel.removeAll();
        completedPanel.removeAll();
        cpuPanel.removeAll();

        for (Node<Process> node : readyQueue.getAllNodes()) {
            readyPanel.add(createProcessInfoPanel(node.getData()));
        }

        for (Node<Process> node : blockedQueue.getAllNodes()) {
            blockedPanel.add(createProcessInfoPanel(node.getData()));
        }

        for (Process process : exitList.getAllProcesses()) {
            completedPanel.add(createProcessInfoPanel(process));
        }

        for (CPU cpu : cpus) {
            cpuPanel.add(createCPUInfoPanel(cpu));
        }

        revalidate();
        repaint();
    }

    private void startAutoUpdate() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        restartAutoUpdate();
    }

    private void restartAutoUpdate() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::updateWindow, 0, 100, TimeUnit.MILLISECONDS);
    }

    // Process panel
    private JPanel createProcessInfoPanel(Process process) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_ACCENT, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("ID: " + process.getID());
        JLabel statusLabel = new JLabel("Status: " + process.getStatus());
        JLabel nameLabel = new JLabel("Name: " + process.getName());
        JLabel pcLabel = new JLabel("PC: " + process.getPC());
        JLabel marLabel = new JLabel("MAR: " + process.getMAR());

        // Styling
        Font font = new Font("SansSerif", Font.PLAIN, 12);
        idLabel.setFont(font);
        statusLabel.setFont(font);
        nameLabel.setFont(font);
        pcLabel.setFont(font);
        marLabel.setFont(font);

        panel.add(idLabel);
        panel.add(statusLabel);
        panel.add(nameLabel);
        panel.add(pcLabel);
        panel.add(marLabel);
        
        // limiting height
        panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 55)); 
        //panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); 

        return panel;
    }
}



