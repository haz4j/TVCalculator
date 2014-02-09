package com.hazard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

public class CalcInterface extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 5793252792661766163L;
    Calculator calculator;
    JLabel labelMarkup;
    JTextField fieldName[];
    JTextField fieldClientSpotDuration[];
    JTextField fieldPartnerSpotDuration[];
    JTextField fieldSpotShare[];
    JXDatePicker startDatePicker[];
    JXDatePicker endDatePicker[];
    int rows = 8;
    int width = 800;
    int lastUnlockRow;

    public void setCalculator(Calculator calculator) {
	this.calculator = calculator;
    }

    public CalcInterface(String string) {
	super(string);

	JPanel myPanel = new JPanel();
	ImageIcon icon = createImageIcon("icon.png");
	this.setIconImage(icon.getImage());
	BoxLayout myPanelLayout = new BoxLayout(myPanel, BoxLayout.Y_AXIS);
	myPanel.setLayout(myPanelLayout);
	this.add(myPanel);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
	topPanel.add(Box.createHorizontalGlue());

	ImageIcon logo = createImageIcon("logo.png");
	JLabel logoLabel = new JLabel(logo);
	topPanel.add(logoLabel);
	topPanel.setMaximumSize(new Dimension(width, 60));
	myPanel.add(topPanel);

	JPanel titlePanel = new JPanel();
	titlePanel.setMaximumSize(new Dimension(width, 50));
	GridLayout titleGridLayout = new GridLayout(0, 7, 10, 15);
	titlePanel.setLayout(titleGridLayout);

	JLabel empLabel = new JLabel("");
	JLabel nameLabel = new JLabel("<HTML>Clip name <br>(Optional)");
	JLabel clientSpotDurationLabel = new JLabel("<HTML>Clip duration, sec");
	JLabel partnerSpotDurationLabel = new JLabel("<HTML>Presence of third party logo, sec");
	JLabel spotShareLabel = new JLabel("<HTML>Clip weight, % <br> Sum must be 100%");
	JLabel startDateLabel = new JLabel("Start date");
	JLabel endDateLabel = new JLabel("End date");

	empLabel.setHorizontalAlignment(SwingConstants.CENTER);
	nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	clientSpotDurationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	partnerSpotDurationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	spotShareLabel.setHorizontalAlignment(SwingConstants.CENTER);
	startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
	endDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

	titlePanel.add(empLabel);
	titlePanel.add(nameLabel);
	titlePanel.add(clientSpotDurationLabel);
	titlePanel.add(partnerSpotDurationLabel);
	titlePanel.add(spotShareLabel);
	titlePanel.add(startDateLabel);
	titlePanel.add(endDateLabel);

	myPanel.add(titlePanel);

	JPanel middlePanel = new JPanel();
	middlePanel.setMaximumSize(new Dimension(width, 300));
	GridLayout middleGridLayout = new GridLayout(0, 7, 10, 15);
	middlePanel.setLayout(middleGridLayout);

	fieldName = new JTextField[rows];
	fieldClientSpotDuration = new JTextField[rows];
	fieldPartnerSpotDuration = new JTextField[rows];
	fieldSpotShare = new JTextField[rows];
	startDatePicker = new JXDatePicker[rows];
	endDatePicker = new JXDatePicker[rows];

	for (int i = 0; i < rows; i++) {

	    fieldName[i] = new JTextField("");
	    fieldClientSpotDuration[i] = new JTextField("");
	    fieldPartnerSpotDuration[i] = new JTextField("");
	    fieldSpotShare[i] = new JTextField("");
	    startDatePicker[i] = new JXDatePicker();
	    endDatePicker[i] = new JXDatePicker();

	    startDatePicker[i].setFormats("dd-MM-yyyy");
	    endDatePicker[i].setFormats("dd-MM-yyyy");

	    fieldName[i].setEditable(false);
	    fieldClientSpotDuration[i].setEditable(false);
	    fieldPartnerSpotDuration[i].setEditable(false);
	    fieldSpotShare[i].setEditable(false);
	    startDatePicker[i].setEditable(false);
	    endDatePicker[i].setEditable(false);

	    fieldName[i].addMouseListener(new InternalMouseListener(this, i));
	    fieldClientSpotDuration[i].addMouseListener(new InternalMouseListener(this, i));
	    fieldPartnerSpotDuration[i].addMouseListener(new InternalMouseListener(this, i));
	    fieldSpotShare[i].addMouseListener(new InternalMouseListener(this, i));
	    startDatePicker[i].addMouseListener(new InternalMouseListener(this, i));
	    endDatePicker[i].addMouseListener(new InternalMouseListener(this, i));

	    middlePanel.add(new JLabel("Clip " + (i + 1), SwingConstants.CENTER));
	    middlePanel.add(fieldName[i]);
	    middlePanel.add(fieldClientSpotDuration[i]);
	    middlePanel.add(fieldPartnerSpotDuration[i]);
	    middlePanel.add(fieldSpotShare[i]);
	    middlePanel.add(startDatePicker[i]);
	    middlePanel.add(endDatePicker[i]);

	}

	this.setInitialState();

	myPanel.add(middlePanel);

	JPanel bottomPanel = new JPanel();
	bottomPanel.setMaximumSize(new Dimension(width, 32));

	JButton buttonExecute = new JButton("Get markup");
	buttonExecute.addActionListener(this);
	buttonExecute.setActionCommand("buttonExecutePressed");
	bottomPanel.add(buttonExecute);

	JButton buttonClear = new JButton("Clear");
	buttonClear.addActionListener(this);
	buttonClear.setActionCommand("buttonClearPressed");
	bottomPanel.add(buttonClear);
	myPanel.add(Box.createVerticalStrut(10));
	myPanel.add(bottomPanel);
	myPanel.add(Box.createVerticalStrut(15));

	JPanel markupPanel = new JPanel();
	markupPanel.setLayout(new BoxLayout(markupPanel, BoxLayout.PAGE_AXIS));
	markupPanel.setAlignmentX(CENTER_ALIGNMENT);
	labelMarkup = new JLabel("");
	markupPanel.add(labelMarkup);
	myPanel.add(markupPanel);

	// myPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	// titlePanel.setBorder(BorderFactory.createLineBorder(Color.green));
	// topPanel.setBorder(BorderFactory.createLineBorder(Color.green));
	// middlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	// bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	// markupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public ArrayList<Spot> getData() throws EmptyFieldsException, SpotDurationException {
	ArrayList<Spot> spotArrayList = new ArrayList<Spot>();

	for (int i = 0; i < rows; i++) {
	    if ((!fieldClientSpotDuration[i].getText().trim().isEmpty())
		    || (!fieldPartnerSpotDuration[i].getText().trim().isEmpty())
		    || (!fieldSpotShare[i].getText().trim().isEmpty())) {

		if ((!fieldClientSpotDuration[i].getText().trim().isEmpty())
			&& (!fieldPartnerSpotDuration[i].getText().trim().isEmpty())
			&& (!fieldSpotShare[i].getText().trim().isEmpty())) {

		    // System.out.println("row " + i + " is OK");
		    String name;
		    int clientSpotDuration;
		    int partnersSpotDuration;
		    int spotShare;
		    Date startDate;
		    Date endDate;

		    if (!fieldName[i].getText().isEmpty()) {
			name = fieldName[i].getText();
		    } else {
			name = new String();
		    }
		    clientSpotDuration = Integer.parseInt(fieldClientSpotDuration[i].getText().trim());
		    partnersSpotDuration = Integer.parseInt(fieldPartnerSpotDuration[i].getText().trim());
		    spotShare = Integer.parseInt(fieldSpotShare[i].getText().trim().replaceAll("%", ""));
		    startDate = startDatePicker[i].getDate();
		    endDate = endDatePicker[i].getDate();

		    if (clientSpotDuration >= partnersSpotDuration) {
			spotArrayList.add(new Spot(name, clientSpotDuration, partnersSpotDuration, spotShare,
				startDate, endDate));
		    } else {
			throw new SpotDurationException(i);
		    }

		} else {
		    throw new EmptyFieldsException(i);
		}

	    }
	}

	return spotArrayList;
    }

    public void outline(double weightedMarkup) {
	labelMarkup.setText("Total markup = "
		+ (new BigDecimal(String.valueOf(weightedMarkup - 100)).setScale(2, BigDecimal.ROUND_HALF_UP))
			.toString() + "%");
    }

    public void actionPerformed(ActionEvent e) {

	if (e.getActionCommand().equals("buttonExecutePressed")) {
	    calculator.execute();
	} else if (e.getActionCommand().equals("buttonClearPressed")) {
	    this.setInitialState();
	}

    }

    protected ImageIcon createImageIcon(String path) {
	java.net.URL imgURL = getClass().getResource(path);
	if (imgURL != null) {
	    return new ImageIcon(imgURL);
	} else {
	    // System.err.println("Couldn't find file: " + path);
	    return null;
	}
    }

    public void showWarning(int exceptionName, int rowNumber) {

	// thanks for java 6
	// EmptyFieldsException = 0
	// SpotDurationException = 1
	// NumberFormatException = 2 and we don't know the row number
	// Shares = 3 and we don't know the row number

	switch (exceptionName) {
	case 0:
	    JOptionPane.showMessageDialog(null, "Please fill out row #" + (rowNumber + 1) + " completely",
		    "Error: we can't provide a markup", 0);
	    break;

	case 1:
	    JOptionPane.showMessageDialog(null, "Partner's clip cannot be longer than client's clip: row #"
		    + (rowNumber + 1), "Error: we can't provide a markup", 0);
	    break;

	case 2:
	    JOptionPane.showMessageDialog(null, "All figures must be only whole numbers",
		    "Error: we can't provide a markup", 0);
	    break;

	case 3:
	    JOptionPane.showMessageDialog(null, "Sum of clips' shares must be 100", "Error: we can't provide a markup",
		    0);
	    break;

	default:
	    JOptionPane.showMessageDialog(null, "Unknown error", "Error: we can't provide a markup", 0);
	}
    }

    public void unlockRows(int numberOfUnlockRow) {
	// System.out.println("we need to unlock " + numberOfUnlockRow +
	// " row");

	if (numberOfUnlockRow < rows) {
	    for (int i = lastUnlockRow; i < numberOfUnlockRow; i++) {
		fieldName[i + 1].setEditable(true);
		fieldClientSpotDuration[i + 1].setEditable(true);
		fieldPartnerSpotDuration[i + 1].setEditable(true);
		fieldSpotShare[i + 1].setEditable(true);
		startDatePicker[i + 1].setEditable(true);
		endDatePicker[i + 1].setEditable(true);
		// System.out.println("we unlock " + (i + 1) + " row");
	    }
	} else {
	    for (int i = lastUnlockRow; i < rows - 1; i++) {
		fieldName[i + 1].setEditable(true);
		fieldClientSpotDuration[i + 1].setEditable(true);
		fieldPartnerSpotDuration[i + 1].setEditable(true);
		fieldSpotShare[i + 1].setEditable(true);
		startDatePicker[i + 1].setEditable(true);
		endDatePicker[i + 1].setEditable(true);
		// System.out.println("we unlock " + (i + 1) + " row");
	    }
	}
    }

    public void clearMarkup() {
	labelMarkup.setText("");
    }

    public void setInitialState() {
	lastUnlockRow = 1;
	for (int i = 0; i < rows; i++) {

	    if (i == 0) {
		fieldName[i].setText("The first clip");
		fieldClientSpotDuration[i].setText("30");
		fieldPartnerSpotDuration[i].setText("10");
		fieldSpotShare[i].setText("100");
		startDatePicker[i].setDate(new Date());
		endDatePicker[i].setDate(new Date());

		fieldName[i].setEditable(true);
		fieldClientSpotDuration[i].setEditable(true);
		fieldPartnerSpotDuration[i].setEditable(true);
		fieldSpotShare[i].setEditable(true);
		startDatePicker[i].setEditable(true);
		endDatePicker[i].setEditable(true);
	    }

	    else if (i == 1) {
		fieldName[i].setText("");
		fieldClientSpotDuration[i].setText("");
		fieldPartnerSpotDuration[i].setText("");
		fieldSpotShare[i].setText("");
		startDatePicker[i].setDate(null);
		endDatePicker[i].setDate(null);

		fieldName[i].setEditable(true);
		fieldClientSpotDuration[i].setEditable(true);
		fieldPartnerSpotDuration[i].setEditable(true);
		fieldSpotShare[i].setEditable(true);
		startDatePicker[i].setEditable(true);
		endDatePicker[i].setEditable(true);

	    } else {
		fieldName[i].setText("");
		fieldClientSpotDuration[i].setText("");
		fieldPartnerSpotDuration[i].setText("");
		fieldSpotShare[i].setText("");
		startDatePicker[i].setDate(null);
		endDatePicker[i].setDate(null);

		fieldName[i].setEditable(false);
		fieldClientSpotDuration[i].setEditable(false);
		fieldPartnerSpotDuration[i].setEditable(false);
		fieldSpotShare[i].setEditable(false);
		startDatePicker[i].setEditable(false);
		endDatePicker[i].setEditable(false);
	    }

	}

    }

}

class InternalMouseListener implements MouseListener {
    private int clickedRow;
    private CalcInterface calcInterface;

    public InternalMouseListener(CalcInterface calcInterface, int clickedRow) {
	this.calcInterface = calcInterface;
	this.clickedRow = clickedRow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// System.out.println("click on " + clickedRow + " row");
	calcInterface.unlockRows(clickedRow + 1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }
}

@SuppressWarnings("serial")
class EmptyFieldsException extends Exception {
    int lineNumber;

    public EmptyFieldsException(int lineNumber) {
	super();
	this.lineNumber = lineNumber;
    }
}

@SuppressWarnings("serial")
class SpotDurationException extends Exception {
    int lineNumber;

    public SpotDurationException(int lineNumber) {
	super();
	this.lineNumber = lineNumber;
    }
}