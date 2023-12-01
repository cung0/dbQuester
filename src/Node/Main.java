package Node.src.Node;

import Node.Validate.*;
import Quests.SheepShearer;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Logger;
import org.dreambot.core.Instance;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@ScriptManifest(name = "Wet Quests", description = "It Quests!", author = "wettofu",
        version = 1.0, category = Category.WOODCUTTING, image = "")

public class Main extends AbstractScript {
    private ArrayList<String> compiledQuestList = new ArrayList<>();
    private Node[] nodes;
    private boolean started;
    private int questStep = 0;

    @Override
    public void onStart() {
        SwingUtilities.invokeLater(this::createGUI);
        super.onStart();
        nodes = new Node[]{
                new SheepShearer(this)
        };
    }

    @Override
    public int onLoop() {
        if (started){
            for (Node node : nodes){
                if (node.validate()){
                    return node.execute();
                } else if (questStep < compiledQuestList.size()){
                        Validate.activate_quest(compiledQuestList.get(questStep));
                        questStep++;
                }
            }
        }
        return 500;
    }


    private void createGUI(){
        JFrame main_frame = new JFrame();
        main_frame.setTitle("Wet Quests - Version 2.2");
        main_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main_frame.setLocationRelativeTo(Instance.getCanvas());
        main_frame.setPreferredSize(new Dimension(700,500));
        main_frame.getContentPane().setLayout(new BorderLayout());
        Border border = BorderFactory.createLineBorder(new Color(102,102,102));

        JButton menu_button = null;
        try {
            menu_button = new JButton(new ImageIcon(((new ImageIcon(new URL("https://i.imgur.com/bty2VDK.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)))));
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        menu_button.setFocusPainted(false);
        menu_button.setBorder(BorderFactory.createEmptyBorder());
        menu_button.setContentAreaFilled(false);

        JButton minigame_button = null;
        try {
            minigame_button = new JButton(new ImageIcon(((new ImageIcon(new URL("https://i.imgur.com/0wWN5nb.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        minigame_button.setFocusPainted(false);
        minigame_button.setBorder(BorderFactory.createEmptyBorder());
        minigame_button.setContentAreaFilled(false);

        JButton quest_button = null;
        try {
            quest_button = new JButton(new ImageIcon(((new ImageIcon(new URL("https://i.imgur.com/8ASOrAN.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        quest_button.setFocusPainted(false);
        quest_button.setBorder(BorderFactory.createEmptyBorder());
        quest_button.setContentAreaFilled(false);

        JButton skill_button = null;
        try {
            skill_button = new JButton(new ImageIcon(((new ImageIcon(new URL("https://i.imgur.com/vPk0pVa.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        skill_button.setFocusPainted(false);
        skill_button.setBorder(BorderFactory.createEmptyBorder());
        skill_button.setContentAreaFilled(false);

        JButton error_button = null;
        try {
            error_button = new JButton(new ImageIcon(((new ImageIcon(new URL("https://i.imgur.com/rzWBloN.png")).getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        error_button.setFocusPainted(false);
        error_button.setBorder(BorderFactory.createEmptyBorder());
        error_button.setContentAreaFilled(false);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.add(menu_button);
        menuPanel.add(quest_button);
        menuPanel.add(minigame_button);
        menuPanel.add(skill_button);
        menuPanel.add(error_button);
        main_frame.getContentPane().add(menuPanel, BorderLayout.WEST);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(1,0));
        JButton start = new JButton();
        start.setBackground(Color.BLACK);
        start.setText("Start");
        startPanel.add(start);
        main_frame.getContentPane().add(startPanel, BorderLayout.SOUTH);


        DefaultListModel<String> listModel = new DefaultListModel<String>();
        listModel.addElement("Sheep Shearer");
//        listModel.addElement("Black Fortress");
//        listModel.addElement("Cook's Assistant");
//        listModel.addElement("Death To Dorgeshuun");
//        listModel.addElement("Demon Slayer");
//        listModel.addElement("Doric's Quest");
//        listModel.addElement("Ernest the Chicken");
//        listModel.addElement("Goblin Diplomacy");
//        listModel.addElement("Imp Catcher");
//        listModel.addElement("Knight's Sword");
//        listModel.addElement("Lost Tribe");
//        listModel.addElement("Restless Ghost");
//        listModel.addElement("Romeo & Juliet");
//        listModel.addElement("Rune Mysteries");
//        listModel.addElement("Sea Slug");
//        listModel.addElement("Sheep Shearer");
//        listModel.addElement("Tourist Trap");
//        listModel.addElement("Vampyre Slayer");
//        listModel.addElement("Witch's Potion");
//        listModel.addElement("X Marks the Spot");

        JList<String> quest_list = new JList<>(listModel);
        quest_list.setLayoutOrientation(JList.VERTICAL);
        quest_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane finished_quests = new JScrollPane(quest_list);
        finished_quests.setPreferredSize(new Dimension(115,80));
        finished_quests.setBorder(border);

        DefaultListModel<String> final_list = new DefaultListModel<String>();
        JList<String> questing_list = new JList<>(final_list);
        questing_list.setLayoutOrientation(JList.VERTICAL);
        JScrollPane questPane = new JScrollPane(questing_list);
        questPane.setPreferredSize(new Dimension(115,80));
        questPane.setBorder(border);
        questPane.setBackground(Color.BLACK);


        JButton add_quest = new JButton();
        add_quest.setText("Add Quest");
        add_quest.setPreferredSize(new Dimension(50,50));
        add_quest.setBackground(Color.BLACK);
        add_quest.setBorder(border);
        add_quest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = quest_list.getSelectedIndex();
                if (selectedIndex != -1){
                    String final_quest = quest_list.getSelectedValue();
                    listModel.remove(selectedIndex);
                    final_list.addElement(final_quest);
                }
            }
        });


        JButton remove_quest = new JButton();
        remove_quest.setText("Remove Quest");
        remove_quest.setPreferredSize(new Dimension(50,50));
        remove_quest.setBackground(Color.BLACK);
        remove_quest.setBorder(border);
        remove_quest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = questing_list.getSelectedIndex();
                if (selectedIndex != -1){
                    String final_quest = questing_list.getSelectedValue();
                    final_list.remove(selectedIndex);
                    listModel.addElement(final_quest);
                }
            }
        });

        JPanel questsPanel = new JPanel();
        questsPanel.setLayout(new GridLayout(2,2));
        questsPanel.add(finished_quests);
        questsPanel.add(questPane);
        questsPanel.add(add_quest);
        questsPanel.add(remove_quest);
        main_frame.getContentPane().add(questsPanel, BorderLayout.CENTER);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < final_list.size(); i++) {
                    compiledQuestList.add(final_list.get(i));
                    Logger.log("Quests : " + final_list.get(i));
                }
                started = true;
                main_frame.dispose();
            }
        });

        main_frame.pack();
        main_frame.setResizable(false);
        main_frame.setVisible(true);
    }
}