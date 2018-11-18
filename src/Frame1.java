import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.messagesender.MessageSenderAPI;
import com.messagesender.dto.ExcelContentDTO;
import com.messagesender.utills.CommonUtills;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Frame1 {

	private JFrame frame;
	private JTextField filePathTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.err.println("Error at Main: " + e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageSenderAPI messageSenderAPI = new MessageSenderAPI();
				CommonUtills excelUtills = new CommonUtills();
				String filePath = filePathTextField.getText();
				List<ExcelContentDTO> excelContentDTOs = excelUtills.readExcel(filePath);
				ExcelContentDTO excelContentDTO ;
				for ( int i = 0 ; i < excelContentDTOs.size() ; i++ ) {
					excelContentDTO = excelContentDTOs.get(i);
					messageSenderAPI.sendSms();	
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSend.setBounds(10, 146, 106, 46);
		frame.getContentPane().add(btnSend);
		
		JLabel lblLocalFilePath = new JLabel("Local File Path:");
		lblLocalFilePath.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalFilePath.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocalFilePath.setBounds(10, 63, 143, 37);
		frame.getContentPane().add(lblLocalFilePath);
		
		filePathTextField = new JTextField();
		filePathTextField.setToolTipText("File Path here");
		filePathTextField.setBounds(163, 63, 190, 37);
		frame.getContentPane().add(filePathTextField);
		filePathTextField.setColumns(10);
	}
}
