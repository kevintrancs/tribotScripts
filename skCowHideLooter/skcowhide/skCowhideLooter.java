package scripts.skcowhide;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import scripts.skcowhide.Data.Location;
import scripts.skcowhide.Task.Antiban;
import scripts.skcowhide.Task.Bank;
import scripts.skcowhide.Task.Loot;
import scripts.skcowhide.Task.ToLocation;
import scripts.skcowhide.Util.Task;

@ScriptManifest(authors = { "Stewiekidz" }, category = "Money making", name = "skCowhide Looter", version = 2, description ="Collects cowhides in lumby or falador field. Please post bugs and feedback on the forums. Thanks for using!")
public class skCowhideLooter extends Script implements Painting {

	private int cowHidePrice = getPrice(1739);
	private ArrayList<Task> tasks = new ArrayList<Task>();
	public static Location field;
	public static int hideCount = 0;
	public static RSTile[] lField = {new RSTile(3241,3297,0), new RSTile(3264,3297,0), new RSTile(3265,3255,0),new RSTile(3253,3255,0)};
	
	@Override
	public void run() {
		
		Gui gui = new Gui();
		gui.setVisible(true);
		while (gui.isVisible()) 
		{
			General.sleep(150);
		}
		
		init();
		
		loop(30,50);
	}
	
	private void loop(int min, int max)
	{
		while(true)
			{
				for(Task t: tasks)
				{
					if(t.activate())
					{
						t.execute();
						sleep(min, max);
					}
				}
			}
	}
	
	private void init()
	{
		General.useAntiBanCompliance(true);
		Mouse.setSpeed(General.random(150, 200));
		Collections.addAll(tasks, new Antiban(), new Bank(), new Loot(), new ToLocation());
	}
	//alpha get price
    public static int getPrice(final int itemId) {
        try {
            URL url = new URL("https://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + itemId);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line = reader.readLine();
                return line == null ? -1 : Integer.parseInt(line.substring(11, line.indexOf(',')));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return -1;
    }
	
	
	//START: Code generated using Enfilade's Easel
    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    
    private static final long startTime = System.currentTimeMillis();
    private final Color color1 = new Color(0, 0, 0, 170);
    private final Color color2 = new Color(0, 255, 255);
    private final BasicStroke stroke1 = new BasicStroke(1);
    private final Font font1 = new Font("Sakkal Majalla", 0, 26);
    private final Font font2 = new Font("Liberation Sans", 1, 20);
    private final Image img1 = getImage("http://img2.wikia.nocookie.net/__cb20140422155127/runescape/images/d/d6/Cow_chathead.png");

    @Override
    public void onPaint(Graphics g1) {
    	long timeRan = System.currentTimeMillis() - startTime;
		int hidePerHr = (int) (hideCount * 3600000 / timeRan);
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(7, 348, 488, 108, 16, 16);
        g.setStroke(stroke1);
        g.drawRoundRect(7, 348, 488, 108, 16, 16);
        g.setFont(font1);
        g.setColor(color2);
        g.drawString("Runtime: " + Timing.msToString(timeRan), 12, 414);
        g.setFont(font2);
        g.drawString("skCowhide Looter v2.02 by Stewiekidz", 65, 372);
        g.setFont(font1);
        g.drawString("Cowhide Looted: " + hideCount + "( " + hidePerHr + " )" , 9, 452);
        g.drawString("GP per Hr: " + (hidePerHr*cowHidePrice) , 278, 451);
        g.drawImage(img1, 415, 351, null);
    }
    //END: Code generated using Enfilade's Easel

}
