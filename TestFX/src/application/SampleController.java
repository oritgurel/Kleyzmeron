package application;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;



public class SampleController {
	
	

	
	@FXML private TextField discountPrice;
	@FXML private TextField enteredPrice;
	@FXML private Button getDiscount;
	@FXML private Button Copy;
	@FXML private Button Round;
	
	
	
	public void discountButtonPushed() {

		FloatStringConverter sc = new FloatStringConverter();
		String price = this.enteredPrice.getText();
		float enteredPriceValue = sc.fromString(price);
		float finalPrice = enteredPriceValue * 0.9f;
		this.enteredPrice.setText(sc.toString(enteredPriceValue));
		this.discountPrice.setText(sc.toString(finalPrice));
}
	
public void copyToClipboard() {
	final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    
    content.putString(this.discountPrice.getText());
    clipboard.setContent(content);
	
}

public void round() {
	FloatStringConverter sc = new FloatStringConverter();
	IntegerStringConverter ins = new IntegerStringConverter();
	String price1 = this.discountPrice.getText();
	String price2 = this.enteredPrice.getText();
	float discountPriceValue = sc.fromString(price1);
	float enteredPriceValue = sc.fromString(price2);
	Integer enteredRounded = Math.round(enteredPriceValue);
	Integer roundedDisPrice = Math.round(discountPriceValue);
	this.enteredPrice.setText(ins.toString(enteredRounded));
	
	
	int lastDigitIndex = roundedDisPrice.toString().length();
	int lastDigit = roundedDisPrice % 10;
	
	if (lastDigit == 0 || lastDigit == 5 ||
			lastDigit  == 9) {
		this.discountPrice.setText(ins.toString(roundedDisPrice));
	}
	else if (lastDigit == 1 || lastDigit == 2) {
		
		lastDigit = 0;
		this.discountPrice.setText(ins.toString(roundedDisPrice).substring(0, lastDigitIndex - 1) + lastDigit);
	}
	else if (lastDigit == 3 || lastDigit == 4 || 
			lastDigit == 6) {
		
		lastDigit = 5;
		this.discountPrice.setText(ins.toString(roundedDisPrice).substring(0, lastDigitIndex - 1) + lastDigit);
	}

		else if (lastDigit == 7 || lastDigit == 8) {
		
			lastDigit = 9;
			this.discountPrice.setText(ins.toString(roundedDisPrice).substring(0, lastDigitIndex - 1) + lastDigit);
	}
}

private void installEventHandler (final Node keyNode) {

	final EventHandler<KeyEvent> keyEventHandler = 
			new EventHandler<KeyEvent>() {
		public void handle(final KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				setPressed(keyEvent.getEventType()
						== KeyEvent.KEY_PRESSED);
				keyEvent.consume();
			}
		}

		private void setPressed(boolean b) {
			// TODO Auto-generated method stub
			
		}
	};
	
	keyNode.setOnKeyPressed(keyEventHandler);
	keyNode.setOnKeyReleased(keyEventHandler);
	
		
	}


}

	

	

		


	
	

