package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.earnings.ClassIdContainKeywordPredicate;
import seedu.address.model.earnings.Earnings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.UpdateEarningsDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_CLASSID_BOB = "CS2103";
    public static final String VALID_CLASSID_AMY = "CS2100";
    public static final String VALID_ATTENDANCE_BOB = "10";
    public static final String VALID_ATTENDANCE_AMY = "2";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PARTICIPATION_BOB = "1";
    public static final String VALID_PARTICIPATION_AMY = "2";
    public static final String VALID_PICTURE_BOB = "bob.jpg";
    public static final String VALID_PICTURE_AMY = "amy.png";
    public static final String VALID_RESULT_BOB = "92";
    public static final String VALID_RESULT_AMY = "32";

    public static final String CLASSID_DESC_AMY = " " + PREFIX_CLASSID + VALID_CLASSID_AMY;
    public static final String CLASSID_DESC_BOB = " " + PREFIX_CLASSID + VALID_CLASSID_BOB;
    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PARTICIPATION_DESC_AMY = " " + PREFIX_PARTICIPATION + VALID_PARTICIPATION_AMY;
    public static final String PARTICIPATION_DESC_BOB = " " + PREFIX_PARTICIPATION + VALID_PARTICIPATION_BOB;
    public static final String PICTURE_DESC_AMY = " " + PREFIX_PICTURE + VALID_PICTURE_AMY;
    public static final String PICTURE_DESC_BOB = " " + PREFIX_PICTURE + VALID_PICTURE_BOB;
    public static final String RESULT_DESC_AMY = " " + PREFIX_RESULT + VALID_PICTURE_AMY;
    public static final String RESULT_DESC_BOB = " " + PREFIX_RESULT + VALID_PICTURE_BOB;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PICTURE_DESC = " " + PREFIX_PICTURE + "James.docx"; // .docx not allowed
    public static final String INVALID_ATTENDANCE_DESC = " " + PREFIX_ATTENDANCE + "Present"; // Only numbers allowed
    public static final String INVALID_RESULT_DESC = " " + PREFIX_RESULT + "Full Marks"; // Only numbers allowed
    public static final String INVALID_CLASSID_DESC = " " + PREFIX_CLASSID + " "; // Class ID should not be blank
    public static final String INVALID_PARTICIPATION_DESC = " " + PREFIX_PARTICIPATION + "Good"; // Only numbers

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALID_DATE_EARNINGS_CS2100_A01 = "03/04/2020";
    public static final String VALID_DATE_EARNINGS_CS1231_T05 = "06/04/2019";
    public static final String VALID_TYPE_EARNINGS_CS2100_A01 = "tut";
    public static final String VALID_TYPE_EARNINGS_CS1231_T05 = "c";
    public static final String VALID_AMOUNT_EARNINGS_CS2100_A01 = "50.00";
    public static final String VALID_AMOUNT_EARNINGS_CS1231_T05 = "60.00";

    public static final String DATE_DESC_CS2100 = " " + PREFIX_DATE + VALID_DATE_EARNINGS_CS2100_A01;
    public static final String DATE_DESC_CS1231 = " " + PREFIX_DATE + VALID_DATE_EARNINGS_CS1231_T05;
    public static final String TYPE_DESC_CS2100 = " " + PREFIX_TYPE + VALID_TYPE_EARNINGS_CS2100_A01;
    public static final String TYPE_DESC_CS1231 = " " + PREFIX_TYPE + VALID_TYPE_EARNINGS_CS1231_T05;
    public static final String AMOUNT_DESC_CS2100 = " " + PREFIX_AMOUNT + VALID_AMOUNT_EARNINGS_CS2100_A01;
    public static final String AMOUNT_DESC_CS1231 = " " + PREFIX_AMOUNT + VALID_AMOUNT_EARNINGS_CS1231_T05;

    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "04/593/2022"; // "593" for months not allowed
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "consulting"; // "consulting" is not allowed
    public static final String INVALID_AMOUNT_DESC =
            " " + PREFIX_AMOUNT + "323.332"; // Only 2 decimal places are allowed

    public static final UpdateEarningsCommand.EditEarningsDescriptor DESC_CS2100;
    public static final UpdateEarningsCommand.EditEarningsDescriptor DESC_CS1231;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPicture(VALID_PICTURE_AMY).withAttendance(VALID_ATTENDANCE_AMY)
                .withResult(VALID_RESULT_AMY).withClassId(VALID_CLASSID_AMY)
                .withParticipation(VALID_PARTICIPATION_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPicture(VALID_PICTURE_BOB).withAttendance(VALID_ATTENDANCE_BOB)
                .withResult(VALID_RESULT_BOB).withClassId(VALID_CLASSID_BOB)
                .withParticipation(VALID_PARTICIPATION_BOB).build();
        DESC_CS2100 = new UpdateEarningsDescriptorBuilder().withDate(VALID_DATE_EARNINGS_CS2100_A01)
                .withType(VALID_TYPE_EARNINGS_CS2100_A01).withClassId(VALID_CLASSID_AMY)
                .withAmount(VALID_AMOUNT_EARNINGS_CS2100_A01).build();
        DESC_CS1231 = new UpdateEarningsDescriptorBuilder().withDate(VALID_DATE_EARNINGS_CS1231_T05)
                .withType(VALID_TYPE_EARNINGS_CS1231_T05).withClassId(VALID_CLASSID_BOB)
                .withAmount(VALID_AMOUNT_EARNINGS_CS1231_T05).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEarningsAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEarningsList().size());

        Earnings earnings = model.getFilteredEarningsList().get(targetIndex.getZeroBased());
        model.updateFilteredEarningsList(new ClassIdContainKeywordPredicate(
            Collections.singletonList(earnings.getDate().dateNum)));

        assertEquals(1, model.getFilteredEarningsList().size());
    }
}
