package client;

import java.awt.Color;
import java.io.File;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ButtonToggle extends GuiButton {

	Color off = new Color (0xFFbdbdbd); // Off color
	Color on = new Color (0xFF00FF00); // On Color
	Color c = new Color (0xFFFFFFFF); // Circle Color
	
    public ButtonToggle(int i, int j, int k, String s)
    {
    	this(i, j, k, 120, 20, s);
    }

    public ButtonToggle(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
        this.enabled = true;
        this.visible = true;
    }

    protected int getHoverState(boolean flag)
    {
    	byte byte0 = 1;
    	if (!enabled)
    	{
    		byte0 = 0;
    		}
    	else if (flag)
    	{
    		byte0 = 2;
    	}
    	return byte0;
    }
    
    // Draw Button
    public void drawButton(Minecraft mc, int mx, int my)
    {
    	// If you want to use a hover animation
    	// boolean flag = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
    	
    	GlStateManager.color(1, 1, 1);
    	if(load(this.displayString) == true) {
    		drawRoundedRect(xPosition, yPosition, width, height, height / 2, on);
    		drawCircle(xPosition + width - height + 1, yPosition + 1, height - 2, height - 2, c);
    	} else {
    		drawRoundedRect(xPosition, yPosition, width, height, height / 2, off);
    	    drawCircle(xPosition + 1, yPosition + 1, height - 2, height - 2, c);
    	}
    	
    }
    
    // Toggle
    public static File getFolder(String toggle) {
		File file = new File(FileManager.TOGGLE_DIR, toggle);
		file.mkdirs();
		return file;
	}
	
    public static void save(String toggle, Boolean b) {
		FileManager.writeJsonToFile(new File(getFolder(toggle), "Toggle.json"), b);
	}
    public static Boolean load(String toggle) {
		Boolean b = FileManager.readFromJson(new File(getFolder(toggle), "Toggle.json"), Boolean.class);
		
		if (b == null) {
			b = false;
			save(toggle, b);
		}
		return b;
	}
    
    private void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color) {
    	this.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
    	this.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
    	this.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
    }
    
    private void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, (float) color.getAlpha() / 255);
        
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();

        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, y, 0).endVertex();

        for (int i = (int) (startAngle / 360.0 * 100); i <= (int) (endAngle / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            worldRenderer.pos(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0).endVertex();
        }
        
        Tessellator.getInstance().draw();
        
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    private void drawCircle(int x, int y, int width, int height, Color color) {
    	this.drawArc(x + width / 2, y + height / 2, width / 2, 0, 360, color);
    }
    
}
