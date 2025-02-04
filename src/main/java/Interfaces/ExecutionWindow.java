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

public class ExecutionWindow extends JFrame {
    private Queue<Process> readyQueue;
    private Queue<Process> blockedQueue;
    private ProcessList exitList;
    private CPU[] cpus;

    private JScrollPane readyQueuePanel, blockedQueuePanel, completedProcessesPanel;

    
    private static final Color COLOR_PRIMARY = new Color(48, 63, 159); // dark blue
    private static final Color COLOR_SECONDARY = new Color(255, 87, 34); // orange
    private static final Color COLOR_BACKGROUND = new Color(245, 245, 245); // light gray
    private static final Color COLOR_TEXT = new Color(33, 33, 33); // dark gray
    private static final Color COLOR_ACCENT = new Color(0, 150, 136); // water green

    public ExecutionWindow(Queue<Process> rq, Queue<Process> bq, ProcessList el, CPU[] c) {
        this.readyQueue = rq;
        this.blockedQueue = bq;
        this.exitList = el;
        this.cpus = c;

        setTitle("Execution Window");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COLOR_BACKGROUND);

       
        JPanel cpuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        cpuPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARY, 2), "CPUs"));
        cpuPanel.setBackground(COLOR_BACKGROUND);
        for (CPU cpu : cpus) {
            cpuPanel.add(createCPUInfoPanel(cpu));
        }
        add(cpuPanel, BorderLayout.NORTH);

        // Queue panels with scrollbars
        JPanel queuePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        queuePanel.setBackground(COLOR_BACKGROUND);

        readyQueuePanel = createScrollablePanel("Ready Queue");
        blockedQueuePanel = createScrollablePanel("Blocked Queue");
        completedProcessesPanel = createScrollablePanel("Completed Processes");

        queuePanel.add(readyQueuePanel);
        queuePanel.add(blockedQueuePanel);
        queuePanel.add(completedProcessesPanel);

        add(queuePanel, BorderLayout.CENTER);

        updateWindow(); // load initial data

        setVisible(true);
    }

    
    private JScrollPane createScrollablePanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARY, 2), title));
        panel.setBackground(COLOR_BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250)); // Tamaño fijo
        return scrollPane;
    }

    
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

        // Styling
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

    // UpdateData 
    public void updateWindow() {
        
        JPanel readyPanel = (JPanel) readyQueuePanel.getViewport().getView();
        JPanel blockedPanel = (JPanel) blockedQueuePanel.getViewport().getView();
        JPanel completedPanel = (JPanel) completedProcessesPanel.getViewport().getView();

        
        readyPanel.removeAll();
        blockedPanel.removeAll();
        completedPanel.removeAll();

        // adding process to queues
        for (Node<Process> node : readyQueue.getAllNodes()) {
            readyPanel.add(createProcessInfoPanel(node.getData()));
        }

        for (Node<Process> node : blockedQueue.getAllNodes()) {
            blockedPanel.add(createProcessInfoPanel(node.getData()));
        }

        for (Process process : exitList.getAllProcesses()) {
            completedPanel.add(createProcessInfoPanel(process));
        }

        // refresh
        revalidate();
        repaint();
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

        return panel;
    }
}



