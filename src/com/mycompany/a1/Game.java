package com.mycompany.a1;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import java.lang.String;

public class Game extends Form
{
	private GameWorld gw;
	private boolean lastSeenX = false;

	/**
	 * Class constructor.
	 */
	public Game()
	{
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play()
	{
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					String sCommand=myTextField.getText().toString();
					myTextField.clear();
					if(sCommand.length() != 0)
					{
						switch (sCommand.charAt(0))
						{
							case 'a':
								if (!lastSeenX) gw.accelerate();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'b':
								if (!lastSeenX) gw.brake();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'l':
								if (!lastSeenX) gw.turnLeft();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'r':
								if (!lastSeenX) gw.turnRight();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '1':
								if (!lastSeenX) gw.hitFlag(1);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '2':
								if (!lastSeenX) gw.hitFlag(2);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '3':
								if (!lastSeenX) gw.hitFlag(3);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '4':
								if (!lastSeenX) gw.hitFlag(4);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '5':
								if (!lastSeenX) gw.hitFlag(5);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '6':
								if (!lastSeenX) gw.hitFlag(6);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '7':
								if (!lastSeenX) gw.hitFlag(7);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '8':
								if (!lastSeenX) gw.hitFlag(8);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case '9':
								if (!lastSeenX) gw.hitFlag(9);
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'f':
								if (!lastSeenX) gw.hitFood();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'g':
								if (!lastSeenX) gw.hitSpider();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 't':
								if (!lastSeenX) gw.clockTick();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'd':
								if (!lastSeenX) gw.display();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'm':
								if (!lastSeenX) gw.map();
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'x':
								if (!lastSeenX)
								{
									lastSeenX = true;
									System.out.println("exit? (y/n)");
								}
								else System.out.println("invalid entry. exit? (y/n)");
								break;
							case 'y':
								if (lastSeenX)
								{
									System.out.println("goodbye");
									System.exit(0);
								}
								else System.out.println("invalid entry");
								break;
							case 'n':
								if (lastSeenX)
								{
									System.out.println("let's continue");
									lastSeenX = false;
								}
								else System.out.println("invalid entry");
								break;
							default:
								if (!lastSeenX) System.out.println("invalid entry");
								else System.out.println("invalid entry. exit? (y/n)");
								break;
						} //switch
					}
				} //actionPerformed
			} //new ActionListener()
		); //addActionListener
	} //play
}
