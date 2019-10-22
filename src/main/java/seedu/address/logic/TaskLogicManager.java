package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CalendarParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendar;
import seedu.address.model.commands.CommandObject;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.storage.Storage;

/**
 * The tasks LogicManager of the app.
 */
public class TaskLogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(TaskLogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CalendarParser calendarParser;

    public TaskLogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        calendarParser = new CalendarParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = calendarParser.parseCalendarCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public CommandResult executeUnknown(String commandText) throws CommandException, ParseException {
        return execute(commandText);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Earnings> getFilteredEarningsList() {
        return model.getFilteredEarningsList();
    }

    @Override
    public ObservableList<CommandObject> getFilteredCommandsList() {
        return model.getFilteredCommandsList();
    }

    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        return model.getFilteredReminderList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyCalendar getCalendar() {
        return null;
    }
}
