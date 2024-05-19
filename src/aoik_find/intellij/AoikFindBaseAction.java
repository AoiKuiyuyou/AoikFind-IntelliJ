// +++++


// -----
package aoik_find.intellij;

// -----
import com.intellij.find.editorHeaderActions.NextOccurrenceAction;
import com.intellij.find.editorHeaderActions.PrevOccurrenceAction;
import com.intellij.find.FindManager;
import com.intellij.find.SearchReplaceComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import javax.swing.JComponent;


// -----
abstract class AoikFindBaseAction extends AnAction {

  // -----
  // Action type, one of:
  // - com.intellij.openapi.actionSystem.IdeActions.ACTION_NEXT_OCCURENCE
  // - com.intellij.openapi.actionSystem.IdeActions.ACTION_PREVIOUS_OCCURENCE
  private final String actionType;

  // -----
  AoikFindBaseAction(String actionType) {
    //
    if (
      !actionType.equals(IdeActions.ACTION_NEXT_OCCURENCE)
      &&
      !actionType.equals(IdeActions.ACTION_PREVIOUS_OCCURENCE)
    ) {
      //
      throw new IllegalArgumentException("actionType");
    }

    //
    this.actionType = actionType;
  }

  // -----
  @Override
  public void actionPerformed(AnActionEvent actionEvent) {
    //
    Editor editor = actionEvent.getData(PlatformDataKeys.EDITOR_EVEN_IF_INACTIVE);

    //
    if (editor == null) {
      //
      return;
    }

    //
    Project project = actionEvent.getProject();

    //
    if (project == null) {
      //
      return;
    }

    //
    FindManager.getInstance(project).setFindWasPerformed();

    //
    boolean searchBoxHasFocus = false;

    //
    JComponent headerComponent = editor.getHeaderComponent();

    //
    if (headerComponent instanceof SearchReplaceComponent) {
      //
      SearchReplaceComponent searchReplaceComponent = (SearchReplaceComponent)headerComponent;

      //
      searchBoxHasFocus = searchReplaceComponent.getSearchTextComponent().hasFocus();
    }

    // If the search box is not shown or has no focus,
    // show the search box with the selected text entered into it.
    if (
      // Condition 1: The search box is not shown.
      headerComponent == null
      ||
      // Condition 2: The search box has no focus.
      !searchBoxHasFocus
    ) {
      // Show the search box.
      // If the text selected is not empty, it will be entered into the search box.
      // If the text selected is empty, the old text in the search box will be kept.
      actionInvoke(actionEvent, IdeActions.ACTION_FIND);

      //
      return;
    }

    // If the search box is shown and has focus,
    // continue to find the next or the previous occurrence of the text in the search box.

    //
    try {
      //
      if (this.actionType.equals(IdeActions.ACTION_NEXT_OCCURENCE)) {
        //
        NextOccurrenceAction nextOccurrenceAction = new NextOccurrenceAction();

        // Find the next occurrence of the text in the search box.
        nextOccurrenceAction.actionPerformed(actionEvent);
      }
      else {
        //
        PrevOccurrenceAction prevOccurrenceAction = new PrevOccurrenceAction();

        // Find the previous occurrence of the text in the search box.
        prevOccurrenceAction.actionPerformed(actionEvent);
      }
    }
    catch (Exception exc) {
      // Show the search box.
      actionInvoke(actionEvent, IdeActions.ACTION_FIND);
    }
  }

  // -----
  private static void actionInvoke(AnActionEvent actionEvent, String actionType) {
    //
    actionEvent.getActionManager().getAction(actionType).actionPerformed(actionEvent);
  }
}
