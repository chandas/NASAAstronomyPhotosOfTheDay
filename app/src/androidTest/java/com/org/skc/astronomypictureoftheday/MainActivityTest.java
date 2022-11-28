package com.org.skc.astronomypictureoftheday;

import android.view.View;
import android.widget.Button;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.org.skc.astronomypictureoftheday.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureButtonIsPresent() throws Exception {
        MainActivity mainActivity = rule.getActivity();
        View viewById = mainActivity.findViewById(R.id.darkMode);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(Button.class));
    }
}
