package week10project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PortWindow extends JFrame
{
	Main main;
	
	public PortWindow(Main main)
	{
		this.main = main;
		initGUI();
		setTitle("API Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initGUI()
	{
		JLabel titleLabel = new JLabel("Select Server Port");
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
				System.exit(0);
			}
		});
		// port text box
		JTextField portBox = new JTextField("8080");
		add(portBox, BorderLayout.CENTER);
		// button
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				main.start(Integer.parseInt(portBox.getText().trim()));
				setVisible(false);
				dispose();
			}
		});
		add(button, BorderLayout.PAGE_END);
	}
}
