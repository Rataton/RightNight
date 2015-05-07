package mx.example.ruben.stir.app.UserInterface.Nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import mx.example.ruben.stir.app.UserInterface.Activities.ClubDetailsActivity;

/**
 * Created by Ruben on 5/6/15.
 */
public class NavigationHelper
{

    public static void startClubDetail(ActionBarActivity activity, Bundle bundle)
    {
        Intent detailClub = new Intent(activity, ClubDetailsActivity.class);
        detailClub.putExtras(bundle);
        activity.startActivity(detailClub);
    }
}
