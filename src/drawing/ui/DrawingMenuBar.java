package drawing.ui;

import static drawing.DrawingConstants.ACCTION_EXIT;
import static drawing.DrawingConstants.ACTION_FILE_NEW;
import static drawing.DrawingConstants.ACTION_FILE_OPEN;
import static drawing.DrawingConstants.ACTION_FILE_SAVE;
import static drawing.DrawingConstants.ACTION_FILE_SAVEAS;
import static drawing.DrawingConstants.MENU_EXIT;
import static drawing.DrawingConstants.MENU_FILE;
import static drawing.DrawingConstants.MENU_FILE_NEW;
import static drawing.DrawingConstants.MENU_FILE_OPEN;
import static drawing.DrawingConstants.MENU_FILE_SAVE;
import static drawing.DrawingConstants.MENU_FILE_SAVEAS;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DrawingMenuBar extends JMenuBar {

	public void initMenuBar() {
		JMenu fileMenu = new JMenu(MENU_FILE);
		fileMenu.setMnemonic('F');
		add(fileMenu);

		JMenuItem newFile = new JMenuItem(MENU_FILE_NEW);
		newFile.setAction(getActionMap().get(ACTION_FILE_NEW));
		newFile.setMnemonic('N');
		fileMenu.add(newFile);

		JMenuItem openFile = new JMenuItem(MENU_FILE_OPEN);
		openFile.setAction(getActionMap().get(ACTION_FILE_OPEN));
		openFile.setMnemonic('O');
		fileMenu.add(openFile);

		JMenuItem saveFile = new JMenuItem(MENU_FILE_SAVE);
		saveFile.setAction(getActionMap().get(ACTION_FILE_SAVE));
		saveFile.setMnemonic('S');
		fileMenu.add(saveFile);

		JMenuItem anotherFile = new JMenuItem(MENU_FILE_SAVEAS);
		anotherFile.setAction(getActionMap().get(ACTION_FILE_SAVEAS));
		anotherFile.setMnemonic('A');
		fileMenu.add(anotherFile);

		fileMenu.addSeparator();

		JMenuItem exit = new JMenuItem(MENU_EXIT);
		exit.setAction(getActionMap().get(ACCTION_EXIT));
		exit.setMnemonic('X');
		fileMenu.add(exit);
	}
}