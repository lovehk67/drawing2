package drawing.action;

import static drawing.DrawingConstants.MENU_EXIT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ExitAction extends AbstractAction {

	public ExitAction() {
		super(MENU_EXIT);
	}

	public void actionPerformed(ActionEvent e) {
		JLabel msg = new JLabel();
		msg.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
		msg.setForeground(Color.red);
		msg.setText("종료 하시겠습니까?");

		if (JOptionPane.showConfirmDialog(null, msg, "확인",
				JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
			return;

		System.exit(0);
	}
}
