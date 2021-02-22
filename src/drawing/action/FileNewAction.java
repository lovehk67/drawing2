package drawing.action;

import static drawing.DrawingConstants.MENU_FILE_NEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import drawing.ui.DrawingBoard;

public class FileNewAction extends AbstractDrawingAction {

	public FileNewAction(DrawingBoard board) {
		super(MENU_FILE_NEW, new ImageIcon("src/drawing/resource/new.PNG"),
				board);
	}

	public void actionPerformed(ActionEvent e) {
		JLabel msg = new JLabel();
		msg.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
		msg.setForeground(Color.red);

		if (board.getModel().isModified()) {
			msg.setText("변경 내용을 저장하겠습니까?");
			// 저장하지 않고 바로 clear
			if (JOptionPane.showConfirmDialog(null, msg, "확인",
					JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
				board.getModel().clear();
			} else {
				// 파일 저장
				board.saveFile();
				board.getModel().clear();
			}
		} else {
			board.getModel().clear();
		}

	}
}
