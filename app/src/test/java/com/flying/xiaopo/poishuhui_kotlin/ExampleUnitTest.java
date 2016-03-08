package com.flying.xiaopo.poishuhui_kotlin;

import com.flying.xiaopo.poishuhui_kotlin.domain.data.BookDetailSource;
import com.flying.xiaopo.poishuhui_kotlin.domain.model.BookDetail;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testBookDetail(){
        BookDetail detail = new BookDetailSource().obtain("http://ishuhui.net/ComicBookInfo/45");
    }
}