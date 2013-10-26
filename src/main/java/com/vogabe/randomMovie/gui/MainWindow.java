package com.vogabe.randomMovie.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneLayout;

import java.awt.FlowLayout;

import javax.swing.JList;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.vogabe.randomMovie.controller.ListController;
import com.vogabe.randomMovie.controller.MediaPlayerController;
import com.vogabe.randomMovie.controller.SettingsController;
import com.vogabe.randomMovie.model.ListEntry;

public class MainWindow {

	private JFrame frame;
	private JTextField vlcPathField;
	private JTable folderTable;
	private TableColumn pathColumn;
	private TableColumn removeColumn;
	private DefaultListModel<ListEntry> folderListModel;
	private JList<ListEntry> folderList;
	private ListController listController;
	private MediaPlayerController mediaPlayerController;
	private SettingsController settingsController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		settingsController = new SettingsController();
		listController = new ListController();
		mediaPlayerController = new MediaPlayerController(settingsController);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setMinimumSize(new Dimension(500, 450));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout mainLayout = new GridBagLayout();
		mainLayout.layoutContainer(frame);
		frame.getContentPane().setLayout(mainLayout);
		
		JPanel mediaPlayerPanel = new JPanel();
		GridBagLayout gbl_mediaPlayerPanel = new GridBagLayout();
		mediaPlayerPanel.setLayout(gbl_mediaPlayerPanel);
		
		GridBagConstraints gbc_mediaPlayerPanel = new GridBagConstraints();
		gbc_mediaPlayerPanel.fill = GridBagConstraints.BOTH;
		gbc_mediaPlayerPanel.insets = new Insets(5, 5, 5, 5);
		gbc_mediaPlayerPanel.gridx = 0;
		gbc_mediaPlayerPanel.gridy = 0;
		frame.getContentPane().add(mediaPlayerPanel, gbc_mediaPlayerPanel);
		
		JLabel lblVlc = new JLabel("Media Player");
		lblVlc.setAlignmentY(0.0f);
		GridBagConstraints gbc_lblVlc = new GridBagConstraints();
		gbc_lblVlc.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblVlc.insets = new Insets(0, 0, 5, 0);
		gbc_lblVlc.gridx = 0;
		gbc_lblVlc.gridy = 0;
		gbc_lblVlc.gridwidth = 2;
		mediaPlayerPanel.add(lblVlc, gbc_lblVlc);
		
		vlcPathField = new JTextField();
		String vlcHistory = settingsController.getStoredMediaPlayerPath();
		if(vlcHistory != null)
			vlcPathField.setText(vlcHistory);
		GridBagConstraints gbc_vlcPathField = new GridBagConstraints();
		gbc_vlcPathField.anchor = GridBagConstraints.LINE_START;
		gbc_vlcPathField.fill = GridBagConstraints.BOTH;
		gbc_vlcPathField.insets = new Insets(0, 0, 0, 5);
		gbc_vlcPathField.gridx = 0;
		gbc_vlcPathField.gridy = 1;
		gbc_vlcPathField.weightx = 1;
		gbc_vlcPathField.weighty = 1;
		mediaPlayerPanel.add(vlcPathField, gbc_vlcPathField);
		vlcPathField.setEditable(false);
		vlcPathField.setColumns(10);
		
		JButton vlcButton = new JButton("Select");
		GridBagConstraints gbc_vlcButton = new GridBagConstraints();
		gbc_vlcButton.anchor = GridBagConstraints.WEST;
		gbc_vlcButton.gridx = 1;
		gbc_vlcButton.gridy = 1;
		mediaPlayerPanel.add(vlcButton, gbc_vlcButton);
		vlcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVlcPath();
			}
		});
		
		JPanel folderPanel = new JPanel();
		GridBagLayout gbl_folderPanel = new GridBagLayout();
		folderPanel.setLayout(gbl_folderPanel);
		GridBagConstraints gbc_folderPanel = new GridBagConstraints();
		gbc_folderPanel.weightx = 1.0;
		gbc_folderPanel.weighty = 1.0;
		gbc_folderPanel.fill = GridBagConstraints.BOTH;
		gbc_folderPanel.gridx = 0;
		gbc_folderPanel.gridy = 1;
		frame.getContentPane().add(folderPanel, gbc_folderPanel);
		
		folderListModel = new DefaultListModel<ListEntry>();
		folderList = new JList<ListEntry>(folderListModel);
		
		JScrollPane scrollPane = new JScrollPane(folderList);
		GridBagConstraints gbc_scrollPanel = new GridBagConstraints();
		gbc_scrollPanel.gridx = 0;
		gbc_scrollPanel.gridy = 0;
		gbc_scrollPanel.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPanel.anchor = GridBagConstraints.CENTER;
		gbc_scrollPanel.fill = GridBagConstraints.BOTH;
		gbc_scrollPanel.weightx = 1.0;
		gbc_scrollPanel.weighty = 1.0;
		folderPanel.add(scrollPane, gbc_scrollPanel);
		
		JPanel listButtonPanel = new JPanel();
		GridBagConstraints gbc_listButtonPanel = new GridBagConstraints();
		gbc_listButtonPanel.gridx = 0;
		gbc_listButtonPanel.gridy = 1;
		gbc_listButtonPanel.anchor = GridBagConstraints.FIRST_LINE_START;
		folderPanel.add(listButtonPanel, gbc_listButtonPanel);
		
		JButton addFolderButton = new JButton("+");
		listButtonPanel.add(addFolderButton);
		addFolderButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				addToList();
			}
		});
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				removeFromList();
			}
		});
		listButtonPanel.add(removeButton);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				refreshList();
			}

		});
		listButtonPanel.add(refreshButton);
		
		JButton goButton = new JButton("Go");
		GridBagConstraints gbc_GoButton = new GridBagConstraints();
		gbc_GoButton.fill = GridBagConstraints.BOTH;
		gbc_GoButton.gridx = 0;
		gbc_GoButton.gridy = 2;
		gbc_GoButton.insets = new Insets(5, 5, 5, 5);
		frame.getContentPane().add(goButton,gbc_GoButton);
		goButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				playRandomFile();
			}
		});
		frame.getRootPane().setDefaultButton(goButton);
	}
	
	private void playRandomFile() {
		String file = listController.getRandomFile();
		if(file != null)
			mediaPlayerController.play(file);
		else {
			JOptionPane.showMessageDialog(frame, "No files to choose from");
		}
	}
	
	private void refreshList() {
		listController.updateActualList();		
	}
	
	private void removeFromList() {
		List<ListEntry> selectedValues = folderList.getSelectedValuesList();
		for(ListEntry selectedValue: selectedValues){
			folderListModel.removeElement(selectedValue);
			listController.removeEntryFromList(selectedValue);
		}
		
	}
	
	private void addToList() {
		ArrayList<String> files = buildFileChooser();
		if(files == null)
			return;
		for(String currentPath : files){
			ListEntry currentEntry = new ListEntry(currentPath);
			folderListModel.addElement(currentEntry);
			listController.addEntryToList(currentEntry);
		}
	}
	
	private void setVlcPath(){
		String vlcPath = buildVlcSelector();
		if(vlcPath != ""){
			vlcPathField.setText(vlcPath);
			mediaPlayerController.setVlcPath(vlcPath);
		}
	}
	
	private String buildVlcSelector() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose a media player");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return "";
		}
		File vlcLink = chooser.getSelectedFile();
		return vlcLink.getAbsolutePath();
	}
	
	private ArrayList<String> buildFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose your movie folder");
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		String folderHistory = settingsController.getLastOpened();
		if(folderHistory != null)
			chooser.setCurrentDirectory(new File(folderHistory));
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File[] files = chooser.getSelectedFiles();
		ArrayList<String> result = new ArrayList<String>();
		for(File current : files){
			result.add(current.getAbsolutePath());
		}
		if(result.size() > 0){
			File firstFile = new File(result.get(0));
			File parent = firstFile.getParentFile();
			if(parent != null)
				settingsController.storeLastOpened(parent.getAbsolutePath());
		}
		return result;
	}
}
