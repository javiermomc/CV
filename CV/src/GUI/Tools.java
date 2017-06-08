package GUI;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tools extends JPanel implements ChangeListener{
	
	boolean selected = false;
	JSlider hMin, sMin, vMin, hMax, sMax, vMax;
	double hMinValue=0, sMinValue=0, vMinValue=0, hMaxValue=180, sMaxValue=255, vMaxValue=255;
	
	public Tools(int closeOperation){
		hMin = new JSlider(JSlider.HORIZONTAL, 0, 180, 0);
		sMin = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		vMin = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		hMax = new JSlider(JSlider.HORIZONTAL, 0, 180, 180);
		sMax = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		vMax = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		
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
		
		JLabel minTxt = new JLabel("Minimum", JLabel.CENTER); minTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel maxTxt = new JLabel("Maximum", JLabel.CENTER); maxTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel hTxt = new JLabel("H", JLabel.CENTER); hTxt.setAlignmentX(Component.CENTER_ALIGNMENT); 
		JLabel sTxt = new JLabel("S", JLabel.CENTER); sTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel vTxt = new JLabel("V", JLabel.CENTER); vTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel h2Txt = new JLabel("H", JLabel.CENTER); hTxt.setAlignmentX(Component.CENTER_ALIGNMENT); 
		JLabel s2Txt = new JLabel("S", JLabel.CENTER); sTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel v2Txt = new JLabel("V", JLabel.CENTER); vTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		add(minTxt); add(hTxt); add(hMin); add(sTxt); add(sMin); add(vTxt); add(vMin); add(maxTxt); add(h2Txt); add(hMax); add(s2Txt); add(sMax); add(v2Txt); add(vMax);
		JFrame frameTools = new JFrame("Tools");
		frameTools.add(this);
		frameTools.setDefaultCloseOperation(closeOperation);
		frameTools.pack();
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

}
