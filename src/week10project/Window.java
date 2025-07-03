package week10project;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	Window(JPanel panel, Main main)
	{
		setResizable(false);
		setTitle("API Server");
		add(panel, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
				main.db.close();
			}
		});
	}
	
	public void replace(JPanel panel)
	{
		getContentPane().removeAll();
		add(panel);
		revalidate();
		pack();
	}
}
