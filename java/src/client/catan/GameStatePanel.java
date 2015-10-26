package client.catan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.base.IAction;
import client.facade.CatanFacade;


@SuppressWarnings("serial")
public class GameStatePanel extends JPanel
{
	private JButton button;
	
	public GameStatePanel()
	{
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		button = new JButton();
		
		Font font = button.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		button.setFont(newFont);
		
		button.setPreferredSize(new Dimension(400, 50));
		
		this.add(button);
		
		updateGameState("Waiting for other Players", false);
	}
	
	public void updateGameState(String stateMessage, boolean enable)
	{
		button.setText(stateMessage);
		if(enable)
                {
                    button.setBackground(CatanFacade.getColorFromPlayerName(CatanFacade.getMyPlayerInfo().getName()).getJavaColor());
                    button.setContentAreaFilled(false);
                    button.setOpaque(true);
                }
                else
                {
                    button.setBackground(Color.WHITE);
                    button.setContentAreaFilled(true);
                    button.setOpaque(false);
                }
                button.setEnabled(enable);
                
	}
	
	public void setButtonAction(final IAction action)
	{
		ActionListener[] listeners = button.getActionListeners();
		for(ActionListener listener : listeners) {
			button.removeActionListener(listener);
		}
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				action.execute();
			}
		};
		button.addActionListener(actionListener);
	}
}
