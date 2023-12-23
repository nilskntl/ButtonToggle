# Toggle Button with Persistent State

This repository provides a customizable toggle button implementation for Minecraft mod menus. The toggle button is capable of persisting its state by creating a JSON file named after the button, making it a convenient choice for saving Boolean values.

## Getting Started

Before using the toggle button, follow these steps to integrate it into your project:

1. Add the following lines to your file manager:

   ```java
   static File TOGGLE_DIR = new File(ROOT_DIR, "Toggle");

   public static void init() {
       if(!TOGGLE_DIR.exists()) {
           TOGGLE_DIR.mkdirs();
       }
   }
   ```

   This initializes the necessary directory for storing toggle state files.

2. Now, you can add the toggle button to your GUI like any other button:

   ```java
   this.buttonList.add(new ButtonToggle(id, x, y, width, height, I18n.format(buttonName, new Object[0])));
   ```

## Usage

To enable toggling functionality and save the state of the button, follow these steps:

1. Change the status of the Boolean associated with the button ID:

   ```java
   if(button.id == id) {
       ButtonToggle.save(buttonName, !ButtonToggle.load(buttonName));
   }
   ```

   This snippet toggles the Boolean state and saves it to the corresponding JSON file.

## Example

An example GUI is included in the repository to demonstrate how to integrate the toggle button into your graphical user interface.

## ButtonToggle Class

The `ButtonToggle` class provides the implementation for the toggle button. It includes methods for drawing the button, handling toggle state persistence, and drawing rounded rectangles and circles.

### Draw Button

The `drawButton` method in the `ButtonToggle` class is responsible for visually rendering the button based on its state. It uses rounded rectangles and circles to create a visually appealing toggle button.

### Toggle

The `save` and `load` methods in the `ButtonToggle` class manage the persistence of the button's state. The `drawRoundedRect`, `drawArc`, and `drawCircle` methods handle the drawing of various shapes used in the button's appearance.

Feel free to customize the code according to your specific requirements and integrate it seamlessly into your Minecraft mod menu project.
