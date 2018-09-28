package GUI;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tools extends JPanel implements ChangeListener{
	
	boolean selected = false;
	JSlider hMin, sMin, vMin, hMax, sMax, vMax;
	JLabel hMinL, sMinL, vMinL, hMaxL, sMaxL, vMaxL;
	double hMinValue=0, sMinValue=0, vMinValue=0, hMaxValue=180, sMaxValue=255, vMaxValue=255;
	
	public Tools(){
		
		hMin = new JSlider(JSlider.HORIZONTAL, 0, 180, 0);
		sMin = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		vMin = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		hMax = new JSlider(JSlider.HORIZONTAL, 0, 180, 180);
		sMax = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		vMax = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		
		hMinL = new JLabel("Min H: 0");
		sMinL = new JLabel(" S: 0");
		vMinL = new JLabel(" V: 0");
		
		hMaxL = new JLabel("Max H: 0");
		sMaxL = new JLabel(" S: 0");
		vMaxL = new JLabel(" V: 0");
		
		hMin.addChangeListener(this);
		sMin.addChangeListener(this);
		vMin.addChangeListener(this);
		hMax.addChangeListener(this);
		sMax.addChangeListener(this);
		vMax.addChangeListener(this);
		
		hMin.setName("hMin");
		sMin.setName("sMin");
		vMin.setName("vMin");
		hMax.setName("hMax");
		sMax.setName("sMax");
		vMax.setName("vMax");
		
		JLabel minTxt = new JLabel("Min "); 
		JLabel maxTxt = new JLabel("Max ");
		JLabel hTxt = new JLabel("H "); 
		JLabel sTxt = new JLabel("S ");
		JLabel vTxt = new JLabel("V ");
		JLabel h2Txt = new JLabel("H "); 
		JLabel s2Txt = new JLabel("S ");
		JLabel v2Txt = new JLabel("V ");
		
		add(minTxt); add(hTxt); add(hMin); add(sTxt); add(sMin); add(vTxt); add(vMin); 	
		add(maxTxt); add(h2Txt); add(hMax); add(s2Txt); add(sMax); add(v2Txt); add(vMax);
		add(hMinL); add(sMinL); add(vMinL);	
		add(hMaxL); add(sMaxL); add(vMaxL);
		JFrame frameTools = new JFrame("Tools");
		FlowLayout layout = new FlowLayout();
		setLayout(layout);
		frameTools.add(this);
		frameTools.setDefaultCloseOperation(3);
		frameTools.pack();
		frameTools.setSize(720, 100);
		frameTools.setResizable(false);
		frameTools.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		if(!slider.getValueIsAdjusting()){
			if(slider.getName().equals("hMin"))hMinValue = slider.getValue();
			else if(slider.getName().equals("sMin"))sMinValue = slider.getValue();
			else if(slider.getName().equals("vMin"))vMinValue = slider.getValue();
			else if(slider.getName().equals("hMax"))hMaxValue = slider.getValue();
			else if(slider.getName().equals("sMax"))sMaxValue = slider.getValue();
			else if(slider.getName().equals("vMax"))vMaxValue = slider.getValue();
			updateLabels();
		}
		
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public Double[] getMinValues(){
		return new Double[]{hMinValue, sMinValue, vMinValue};
	}
	public Double[] getMaxValues(){
		return new Double[]{hMaxValue, sMaxValue, vMaxValue};
	}
	
	void updateLabels(){
		hMinL.setText(" H: "+hMinValue);
		sMinL.setText(" S: "+sMinValue);
		vMinL.setText(" V: "+vMinValue);
		hMaxL.setText(" H: "+hMaxValue);
		sMaxL.setText(" S: "+sMaxValue);
		vMaxL.setText(" V: "+vMaxValue);
	}

}
