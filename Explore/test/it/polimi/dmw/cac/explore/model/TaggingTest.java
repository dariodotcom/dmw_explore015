package it.polimi.dmw.cac.explore.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TaggingTest extends AppEngineTestCase {

    private Tagging model = new Tagging();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
