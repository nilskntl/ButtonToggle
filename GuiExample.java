package client.gui;

import java.io.IOException;

import client.ButtonToggle;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiExample extends GuiScreen
{

	public GuiExample() {}
	
    public void initGui()
    {
		this.buttonList.add(new ButtonToggle(0, this.width / 2 - 12, this.height / 2 - 5, 24, 10, I18n.format("Button1", new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
        case 0:
        	ButtonToggle.save("Button1", !ButtonToggle.load("Button1"));
        	break;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
