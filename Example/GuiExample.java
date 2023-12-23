package client.gui;

import java.io.IOException;

import client.ButtonToggle;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiExample extends GuiScreen {

    public GuiExample() {}

    // Initialize the GUI elements
    public void initGui() {
        // Add a toggle button to the button list
        this.buttonList.add(new ButtonToggle(0, this.width / 2 - 12, this.height / 2 - 5, 24, 10, I18n.format("Button1", new Object[0])));
    }

    // Handle button clicks
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                // Toggle the state of the Button1 and save it
                ButtonToggle.save("Button1", !ButtonToggle.load("Button1"));
                break;
        }
    }

    // Draw the screen
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw the default background
        this.drawDefaultBackground();
        // Draw the buttons
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
