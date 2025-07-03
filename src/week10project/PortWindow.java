package week10project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PortWindow extends JPanel
{
	public PortWindow(Main main)
	{
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("Select Server Port");
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		titleLabel.setFont(font);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel, BorderLayout.PAGE_START);
		// port text box
		JTextField portBox = new JTextField("8080");
		add(portBox, BorderLayout.CENTER);
		// button
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				main.guiStart(Integer.parseInt(portBox.getText().trim()));
				setVisible(false);
			}
		});
		add(button, BorderLayout.PAGE_END);
	}
}
