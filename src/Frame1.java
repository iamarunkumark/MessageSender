import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.messagesender.MessageSenderAPI;
import com.messagesender.dto.DeliveryStatusDTO;
import com.messagesender.utills.CommonUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frame1 {

	private JFrame frame;
	private JTextField filePathTextField;
	private JTextArea responseTextArea;

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
		frame.setBounds(100, 100, 648, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent button) {
				JButton sendButton = (JButton) button.getSource();
				sendButton.setVisible(false);
				sendButton.setEnabled(false);
				sendButton.setText("Sending...");
				responseTextArea.setText("---Sending Messages---");
				MessageSenderAPI messageSenderAPI = new MessageSenderAPI();
				CommonUtils commonUtils = new CommonUtils();
				String filePath = filePathTextField.getText();
				DeliveryStatusDTO deliveryStatusDTO = messageSenderAPI.sendBulkSms(commonUtils.readExcel(filePath));
				StringBuilder response = new StringBuilder("");
				response.append("Transaction Status: ");
				response.append(deliveryStatusDTO.getStatus());
				response.append("\nSource File: ");
				response.append(filePath);
				response.append("\nBalance: ");
				response.append(deliveryStatusDTO.getBalance());
				response.append("\nTotal Cost: ");
				response.append(deliveryStatusDTO.getCost());
				response.append("\nNumber of Messages Delivered: ");
				response.append(deliveryStatusDTO.getNumMsgDelivered());
				response.append("\nNumber of Failed Transactions: ");
				response.append(deliveryStatusDTO.getFailedIndex().size());
				response.append("\nFailed Transaction Index: ");
				for (int i = 0 ; i < deliveryStatusDTO.getFailedIndex().size(); i++) {
					response.append(deliveryStatusDTO.getFailedIndex().get(i));
					response.append(",");
				}
				response.append("\n---");
				System.out.println("Response : " + response.toString());
				responseTextArea.setText(response.toString());
				filePathTextField.setText("");
				sendButton.setText("Send");
				sendButton.setEnabled(true);
				sendButton.setVisible(true);
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSend.setBounds(417, 63, 133, 37);
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
		
		JLabel responseLabel = new JLabel("Response:");
		responseLabel.setToolTipText("Response");
		responseLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		responseLabel.setBounds(10, 121, 133, 37);
		frame.getContentPane().add(responseLabel);
		
		responseTextArea = new JTextArea();
		responseTextArea.setToolTipText("Response");
		responseTextArea.setLineWrap(true);
		responseTextArea.setEditable(false);
		responseTextArea.setBounds(10, 180, 540, 175);
		frame.getContentPane().add(responseTextArea);
	}
}
