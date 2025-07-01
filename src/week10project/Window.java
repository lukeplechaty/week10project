package week10project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Window extends JFrame
{
	Main main;
	private JTextArea logArea = new JTextArea(20, 30);
	
	public Window(Main main)
	{
		this.main = main;
		initGUI();
		setTitle("API Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		startServer();
	}
	
	private void initGUI()
	{
		JLabel titleLabel = new JLabel("API Server");
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		titleLabel.setFont(font);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel, BorderLayout.PAGE_START);
		// listeners
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				stop();
			}
		});
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollPane);
		DefaultCaret caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void log(String message)
	{
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy, HH:mm:ss ");
		String timeStamp = dateFormat.format(time);
		logArea.append(timeStamp + message + "\n");
	}
	
	private void startServer()
	{
		new Thread(main.server).start();
	}
	
	private void stop()
	{
		main.server.server.stop(0);
		System.exit(0);
	}
}
