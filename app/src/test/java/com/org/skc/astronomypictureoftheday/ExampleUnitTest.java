package com.org.skc.astronomypictureoftheday;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.junit.runner.Request.method;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.org.skc.astronomypictureoftheday.view.MainActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Mock
    private MainActivity mainActivity;

    @Mock
    private Button mockButton;

    @Before
    public void setUp() throws Exception {
        mainActivity = spy(MainActivity.class);
        doReturn(mockButton).when(mainActivity).findViewById(anyInt());
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}