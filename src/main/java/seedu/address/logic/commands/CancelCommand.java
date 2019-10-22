package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * To cancel adding an unknown command to existing command list.
 */
public class CancelCommand extends Command {

    public static final String SHOWING_CANCEL_MESSAGE = "We cancelled the wrongly entered command. "
            + "Feel free to continue!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CANCEL_MESSAGE,
                false, false, false, false, false);
    }

}
