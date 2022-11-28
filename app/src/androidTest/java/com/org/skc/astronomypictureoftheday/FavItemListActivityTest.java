package com.org.skc.astronomypictureoftheday;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import android.view.View;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.org.skc.astronomypictureoftheday.view.FavItemsListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class FavItemListActivityTest {

    @Rule
    public ActivityTestRule<FavItemsListActivity> rule2 = new ActivityTestRule<>(FavItemsListActivity.class);

    @Test
    public void ensureWidgetsArePresent() throws Exception {
        FavItemsListActivity favItemsListActivity = rule2.getActivity();
        View rView = favItemsListActivity.findViewById(R.id.recyclerView);
        assertThat(rView, notNullValue());
        assertThat(rView, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) rView;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter, instanceOf(ArrayAdapter.class));
        assertThat(adapter.getItemCount(), greaterThan(1));
    }
}
