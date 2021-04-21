Snippet for a toggle button that saves its status by creating a json file named like the button. Good to use for mod menus and to save Booleans of all kinds.

Before you can use the toggle button, you have to add a few things to your file manager or simply copy the code from the file manager.

Add to your file Manager:

`static File TOGGLE_DIR = new File(ROOT_DIR, "Toggle");`
and
`public static void init() {
    if(!TOGGLE_DIR.exists()) { TOGGLE_DIR.mkdirs(); }
}`

Now you can add your button to your GUI like a normal button
`this.buttonList.add(new ButtonToggle(id, x, y, width, height, I18n.format(buttonname, new Object[0])));`

In order for the status of the button to change, you have to change the status of the Boolean.
`if(button.id == id) {
  ButtonToggle.save(buttonname, !ButtonToggle.load(buttonname));
}`

I also added a small example GUI so you can see how to add the button to the GUI.
