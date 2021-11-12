import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardUI extends JPanel{

	//private JFrame frame;
	private JPanel panel = new JPanel();
	private ArrayList<Player> playersBuffer = new ArrayList<Player>();
	private ArrayList<Token> tokensBuffer = new ArrayList<Token>();
	private ArrayList<JPanel> notPartofBoard = new ArrayList<JPanel>();
	private Player player;
	private boolean cond1,cond2,cond3,cond4,cond1p1,cond2p1,cond1p2,cond2p2,cond1p3,cond2p3;
	private Stack [][] stacks;
	private Token token1,token2,token3,token4;
	private int x,y;
	ImageIcon p1Icon = new ImageIcon(getClass().getResource("/Player 1 - Token.png"));
	private static Stack clicked = null;
	


	/**
	 * Create the application.
	 */
	public BoardUI() {
		setBackground(Color.WHITE);
		
		Turn.initiatePlayerTurn();
		

		

		setLayout(new GridLayout(8, 8, 5, 5));
		
		

		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//this.setBounds(100, 100, 900, 600);
		//this.setLayout(new GridLayout(8, 8, 5, 5));
		
		
		

		playersBuffer = Driver.getPlayers();

		stacks = new Stack[8][8];
		int i = 0;//to iterate tokensBuffer
		int j = 0;//to iterate notPartofBoard
		
		for ( x = 0; x < 8; x ++)
		{
			for ( y = 0; y < 8; y ++)
			{
				//Conditions for invalid token positions
				cond1 = (x==0 && (y==0 || y==1 || y==6 || y==7));
				cond2 = (x==7 && (y==0 || y==1 || y==6 || y==7));
				cond3 = (y==7 && (x==1 || x==6));
				cond4 = (y==0 && (x==1 || x==6));
				
				//Conditions for Player 1's token placement
				cond1p1 = (y<4 && (x==0 || x==2));
				cond2p1 = (x>3 && (y==1 || y==3));
				//Conditions for Player 2's token placement
				cond1p2 = (y<4 && (x==1 || x==3));
				cond2p2 = (x<4 && (y==5 || y==7));
				//Conditions for Player 3's token placement
				cond1p3 = (y>3 && (x==5 || x==7));
				cond2p3 = (x<4 && (y==4 || y==6));
				
				//When position is invalid
				if(cond1 || cond2 || cond3 || cond4)
				{
					notPartofBoard.add(new JPanel());
					notPartofBoard.get(j).setBackground(Color.WHITE);
					stacks[x][y] = new Stack(x, y);
					stacks[x][y].setSize(20, 20);
					stacks[x][y].add(notPartofBoard.get(j), BorderLayout.CENTER);
					this.add(stacks[x][y]);
					j++;
				}
				
				//When position is valid
				else {
					
					//Switch player to generate tokens for the right amount at the right position.
					if(cond1p1 || cond2p1)
						player = playersBuffer.get(0);
					else if (cond1p2 || cond2p2)
						player = playersBuffer.get(1);
					else if (cond1p3 || cond2p3)
						player = playersBuffer.get(2);
					else
						player = playersBuffer.get(3);
					
					//Create new tokens and add it to buffer
					tokensBuffer.add(new Token(player,x,y));
					
					//Create new stacks and add token
					stacks[x][y] = new Stack(x, y);
					//stacks[x][y].setSize(20, 20);
					stacks[x][y].addMouseListener(MouseListener);
					stacks[x][y].stackToken(tokensBuffer.get(i));
					
					//add stack to frame
					this.add(stacks[x][y]);
					
					i++;
				}
				/*stacks[7][5].stackToken(token1);
				stacks[7][5].stackToken(token2);
				stacks[7][5].stackToken(token3);
				stacks[7][5].stackToken(token4);*/
				

			}
		}
		token1 = new Token(playersBuffer.get(0),4,5);
		token2 = new Token(playersBuffer.get(1),4,5);
		token3 = new Token(playersBuffer.get(2),4,5);
		token4 = new Token(playersBuffer.get(3),4,5);

		playersBuffer.get(0).reserveAToken(token1);
		playersBuffer.get(1).reserveAToken(token2);
		playersBuffer.get(2).reserveAToken(token3);
		playersBuffer.get(3).reserveAToken(token4);



		//popupMenu.show(this, 100, 100);

		
	}
	public static Stack getClicked()
	{
		return clicked;
	};
	MouseAdapter MouseListener = new MouseAdapter(){
		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent e) {
			

			clicked = (Stack) e.getSource();
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem playfromReserves = new JMenuItem("Play from reserves");
			playfromReserves.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Move.makeAReserveMove(getClicked());
				}
			});
			if(Turn.getCurrentPlayer().getReserveCount()==0)
			{
				playfromReserves.hide();
			}
			popupMenu.add(playfromReserves);
			
			JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem_2);
			
			JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem_3);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem);
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
			//
			


			
		/*				synchronized(Driver.getGameUIInstance())
			{
				Driver.getGameUIInstance().notify();
			}
			*/
		}
	};
	
//	ActionListener actionListener = new ActionListener() {
//		public void actionPerformed(ActionEvent e) 
//
//		{
//
//			if (e.getSource() == mntmNewMenuItem_1) 
//			{
//
//					
//
//				Move.makeAReserveMove(BoardUI.getClicked());
//			}
//		}
//	};



	
//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}
}
