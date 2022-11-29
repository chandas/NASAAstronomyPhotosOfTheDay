package com.org.skc.astronomypictureoftheday;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.org.skc.astronomypictureoftheday.view.DetailActivity;
import com.org.skc.astronomypictureoftheday.view.FavItemsListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> rule = new ActivityTestRule<DetailActivity>(DetailActivity.class){

        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("title", "Photo of the Day");
            intent.putExtra("date", "11-28-2022");
            return intent;
        }
    };

    /**
     * To validate the launch if new activity with the bundle
     * @throws Exception
     */
    @Test
    public void ensureIntentDataIsDisplayedAfterActivityLaunch() throws Exception {
        DetailActivity detailActivity = rule.getActivity();

        View viewById  = detailActivity.findViewById(R.id.textViewTitleDetail);

        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(TextView.class));

        TextView textView = (TextView) viewById;
        assertThat(textView.getText().toString(), is("Photo of the Day"));

        View viewById2  = detailActivity.findViewById(R.id.textViewDateTakenDetail);

        assertThat(viewById2, notNullValue());
        assertThat(viewById2, instanceOf(TextView.class));

        TextView textView2 = (TextView) viewById2;
        assertThat(textView2.getText().toString(), is("11-28-2022"));
    }

    @Test
    public void ensureWidgetsArePresent() throws Exception {
        DetailActivity DetailActivity = rule.getActivity();
        View iView = DetailActivity.findViewById(R.id.imageViewDetail);
        assertThat(iView, notNullValue());
        assertThat(iView, instanceOf(ImageView.class));
        ImageView imageView = (ImageView)iView;
        assertThat(imageView.isEnabled(), is(true));

        View tView1 = DetailActivity.findViewById(R.id.textViewTitleDetail);
        assertThat(tView1, notNullValue());
        assertThat(tView1, instanceOf(ImageView.class));

        View tView2 = DetailActivity.findViewById(R.id.textViewDateTakenDetail);
        assertThat(tView2, notNullValue());
        assertThat(tView2, instanceOf(ImageView.class));
    }

}
