package jp.caliconography.kms.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ProcessResultTest extends AppEngineTestCase {

    private ProcessResult model = new ProcessResult();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
