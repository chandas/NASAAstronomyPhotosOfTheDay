package com.org.skc.astronomypictureoftheday;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    public void ensureWidgetsArePresent() throws Exception {
        MainActivity mainActivity = rule.getActivity();
        View viewById = mainActivity.findViewById(R.id.darkMode);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(Button.class));

        View tView = mainActivity.findViewById(R.id.textViewExplanation);
        assertThat(tView, notNullValue());
        assertThat(tView, instanceOf(TextView.class));

        View tView2 = mainActivity.findViewById(R.id.textViewDatePicker);
        assertThat(tView2, notNullValue());
        assertThat(tView2, instanceOf(TextView.class));

        View tView3 = mainActivity.findViewById(R.id.textViewSuggest);
        assertThat(tView3, notNullValue());
        assertThat(tView3, instanceOf(TextView.class));
    }
}
