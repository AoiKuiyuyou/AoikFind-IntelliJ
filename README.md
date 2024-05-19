# AoikFind-IntelliJ
An IntelliJ platform based IDE plugin to show the search box or find the next or the previous occurrence, combined into one hotkey.

The plugin provides two IDE actions `AoikFind -- Find Next` and `AoikFind -- Find Previous`, which are mapped to hotkeys `Ctrl f` and `Alt f` by default. It is recommended to remap the hotkeys to remove other actions mapped to the same hotkeys.

When the hotkey `Ctrl f` or `Alt f` is pressed, if the search box is not shown or has no focus, the IDE action shows the search box with the selected text entered into it, otherwise the IDE action finds the next or the previous occurrence of the text in the search box.

To split the scenario into steps:
- 1: Press `ESC`. This hides the search box.
- 2: Press `Ctrl f`. This shows the search box.
- 3: Select some text in the editor. This selects the text to be entered into the search box, but also makes the search box lose focus.
- 4: Press `Ctrl f`. This shows the search box with the selected text entered into it.
- 5: Press `Ctrl f`. This continues to find the next occurrence of the text in the search box.
- 6: Press `Ctrl f`. This continues to find the next occurrence of the text in the search box.
- 7: Press `Alt f`. This continues to find the previous occurrence of the text in the search box.
- 8: Press `Alt f`. This continues to find the previous occurrence of the text in the search box.

Tested working with:
- IntelliJ IDEA 15 (Build 143) to 2024 (Build 241).
- IntelliJ platform based other IDEs.

## Install the plugin
In an IntelliJ platform based IDE:
- Click menu `File`--`Settings...`.
- Click `Plugins` in the navigation area to the left.
- Click `Marketplace` in the content area to the right
- Search for `AoikFind` in the input box.
- Click `Install`.

## Remap the hotkeys
In an IntelliJ platform based IDE:
- Click menu `File`--`Settings...`.
- Click `Keymap` in the navigation area to the left
- Search for `AoikFind` in the input box in the content area to the right
- Right-click on the `AoikFind -- Find Next` or `AoikFind -- Find Previous` found.
- Click `Add Keyboard Shortcut`.
- Press the hotkey of your choice.
- Click `OK`.
- If the IDE prompted `This shortcut is already assigned to other actions.`, click `Remove` to remove other actions mapped to the same hotkey.
