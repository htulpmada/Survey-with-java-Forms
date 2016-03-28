import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Survey extends JFrame implements ActionListener{
	JLabel[] myLabel= new JLabel[5];
	String[] labels={"Record #","Zip Code","Social Media","Age Group","Avg Time"};
	ArrayList<CSample> surveyArray;
	private DefaultListModel surveys;
	JList surveyList;
	JScrollPane scrollPane;
	JButton bnAdd,bnDel,bnMod,bnDelAll;
	int recNum=0;
	
	public Survey() {
		super("Survey on Social Media");
		
		Container c = getContentPane();
		c.setLayout(null);
		
		for (int i=0; i<=myLabel.length-1;i++){
			myLabel[i] = new JLabel(labels[i]);
			myLabel[i].setSize(200,50);
			myLabel[i].setLocation(75+i*150,10);
			myLabel[i].setForeground(Color.blue);
			c.add(myLabel[i]);
		}
		surveyArray = new ArrayList<CSample>();
		surveys = new DefaultListModel();
		surveyList = new JList(surveys);
		surveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		surveyList.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane=new JScrollPane(surveyList);
		scrollPane.setSize(655,200);
		scrollPane.setLocation(75,50);
		c.add(scrollPane);
		//buttons
		bnAdd=new JButton("Add");
		bnAdd.setSize(100,30);
		bnAdd.setLocation(100,260);
		bnAdd.addActionListener(this);
		c.add(bnAdd);
		
		bnMod=new JButton("Modify");
		bnMod.setSize(100,30);
		bnMod.setLocation(250,260);
		bnMod.addActionListener(this);
		c.add(bnMod);
		
		bnDel=new JButton("Delete");
		bnDel.setSize(100,30);
		bnDel.setLocation(400,260);
		bnDel.addActionListener(this);
		c.add(bnDel);
		
		
		bnDelAll=new JButton("Delete All");
		bnDelAll.setSize(100,30);
		bnDelAll.setLocation(550,260);
		bnDelAll.addActionListener(this);
		c.add(bnDelAll);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setSize(800,350);
		setLocation(100,100);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {		
	if (e.getSource()==bnAdd){
		recNum++;
		CSample dialogWnd=new CSample(this,"Add a Survey","",recNum,0,0,0);
		if(!dialogWnd.isCancelled()){
			surveyArray.add(dialogWnd);
			System.out.print(dialogWnd.getAnswer());
			surveys.addElement(dialogWnd.getAnswer());
			surveyList.setSelectedIndex(surveys.size()-1);
			surveyList.ensureIndexIsVisible(surveys.size()-1);
		}
		if(dialogWnd.isCancelled()){recNum--;}
	}
	else if(e.getSource()==bnMod){
		int index=surveyList.getSelectedIndex();
		if (index>=0){
			CSample dialogWnd=new CSample(this,"Add a Survey",surveyArray.get(index).getzip(),(int)surveyArray.get(index).R,(int)surveyArray.get(index).socs,(int)surveyArray.get(index).age,(int)surveyArray.get(index).hrs);
			if(!dialogWnd.isCancelled()){
				surveyArray.set(index,dialogWnd);
				surveys.set(index, dialogWnd.getAnswer());
			}
		}
	}
	else if(e.getSource()==bnDel){
		int index=surveyList.getSelectedIndex();
		if(index>=0){
			surveyArray.remove(index);
			surveys.remove(index);
			if(index==surveys.size()){
				index--;
			}
			surveyList.setSelectedIndex(index);
			surveyList.ensureIndexIsVisible(index);
		}
	}
	else if(e.getSource()==bnDelAll){
		for(int i=0;i<=surveys.size()-1;i++){
			int index=surveyList.getSelectedIndex();
			if(index>=0){
				surveyArray.clear();
				surveys.clear();
				if(index==surveys.size()){
					index--;
				}
			}
		}
	}
}
	public static void main(String[] args){
		Survey mainWnd=new Survey();
	}
}
