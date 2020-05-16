package amit.springframework.spring5recipeapp.converters;

import amit.springframework.spring5recipeapp.commands.NotesCommand;
import amit.springframework.spring5recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTES = "notes";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
     void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        //Given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //Then
        assertNotNull(notesCommand);
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}