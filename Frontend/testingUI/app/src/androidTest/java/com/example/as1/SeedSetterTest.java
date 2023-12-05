package com.example.as1;
import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import android.media.MediaPlayer;
import static org.junit.Assert.assertEquals;

import screens.MusicSwipe;
import screens.SeedSetter;

@RunWith(AndroidJUnit4.class)
public class SeedSetterTest {

    @Rule
    public ActivityTestRule<SeedSetter> activityRule =
            new ActivityTestRule<>(SeedSetter.class);

    @Test
    public void testOpenMusicSwipe() {
        // Click the friends button
        onView(withId(R.id.musicSwipeSend)).perform(click());

        // Verify that the FriendInteractions activity is opened
        onView(withId(R.id.cardImage)).check(matches(isDisplayed()));
    }

}
