# Shopping list

## Description

This application is a shopping list that you can check things off complete with a menu that allows you to add and remove items from the shopping list all in a custom grid layout and a filter field on the bottom

## Classes

There's a total of 11 classes in the ShoppingList program:

### Driver (1 class)

**Main.java**: The driver class

### View (4 classes)

**ShoppingList.java**: Takes care of managing the shopping list

**FilteredJList.java**: Adds the ability to filter the shopping list to your typed words

**MenuBar.java**: Creates the Menu Bar

**FilterField.java**: Creates a filter text field for filtering items

### UI Tweaks (3 classes)

**CustomMenuItemUI.java**: Tweaks the menu to make it a translucent menu

**CustomPopupMenuUI.java**: Tweaks the menu popup to make it a translucent menu popup UI

**ShoppingListItemRenderer.java**: Renders the shopping list items with a green background and circled if purchased

### Model (2 classes)

**FilterModel.java**: Takes care of the filtering logic

**ShoppingListModel.java**: Creates a custom DefaultListModel for our ShoppingListApplication

### Controller (1 class)

**ShoppingListController.java**: Takes care of the button and mouse clicks

## Usage

In order to start the Shopping List GUI program, you need to compile it and then run it with the following commands:

1) ```javac -d out src/*.java src/model/*.java src/view/*.java src/view/ui/*.java src/controller/*.java```
2) ```java -cp out Main```
