package drawing.action;

import static drawing.DrawingConstants.MENU_FILE_OPEN;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import drawing.model.Shape;
import drawing.ui.DrawingBoard;

public class FileOpenAction extends AbstractDrawingAction {

	public FileOpenAction(DrawingBoard board) {
		super(MENU_FILE_OPEN, new ImageIcon("src/drawing/resource/open.PNG"),
				board);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel msg = new JLabel();
		msg.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
		msg.setForeground(Color.red);

		if (board.getModel().isModified()) {
			msg.setText("변경 내용을 저장하겠습니까?");
			// 저장하지 않고 바로 clear
			if (JOptionPane.showConfirmDialog(null, msg, "확인",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				// 저장 하고 열기
				board.saveFile();
			}
		}
		// 현재 모델이 변경되지 않았다면 바로 열기

		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				FileInputStream fileStream = new FileInputStream(fc
						.getSelectedFile());

				ObjectInputStream os = new ObjectInputStream(fileStream);
				Vector<Shape> shapeVector = (Vector<Shape>) os.readObject();

				os.close();
				board.getModel().setFileName(
						fc.getSelectedFile().getAbsolutePath());
				board.getModel().setShapeVector(shapeVector);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
