package week10project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ConsoleWindow extends JPanel
{
	private JTextArea logArea = new JTextArea(20, 30);
	
	public ConsoleWindow(Main main)
	{
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("API Server");
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		titleLabel.setFont(font);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel, BorderLayout.PAGE_START);
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		DefaultCaret caret = (DefaultCaret)logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setVisible(true);
	}
	
	public void log(String message)
	{
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy, HH:mm:ss ");
		String timeStamp = dateFormat.format(time);
		logArea.append(timeStamp + message + "\n");
	}
}
