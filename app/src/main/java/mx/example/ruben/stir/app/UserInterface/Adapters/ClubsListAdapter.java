package mx.example.ruben.stir.app.UserInterface.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mx.example.ruben.stir.R;
import mx.example.ruben.stir.app.Model.Club;
import mx.example.ruben.stir.app.Restfull.Constants;
import mx.example.ruben.stir.app.UserInterface.Activities.ClubDetailsActivity;
import mx.example.ruben.stir.app.UserInterface.Nav.NavigationHelper;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.util.Collections.EMPTY_LIST;

public class ClubsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private String noneContent = "None";

    List<Club> clubs = EMPTY_LIST;
    Context context;
    private int DETAIL_FRAGMENT_ID = 0;

    public ClubsListAdapter(Context context)
    {
        this.context = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_club, viewGroup, false);
        return new ClubViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        if (viewHolder instanceof ClubViewHolder)
        {
            Club currentClub = clubs.get(position);
            //((CharacterViewHolder) viewHolder).setImg(currentCharacter.getUrlImage());
            ((ClubViewHolder) viewHolder).setName(currentClub.getName());

            final Bundle bundle = new Bundle();

            //bundle.putString(Constants.CLUB_URL_IMAGE_URL_IMAGE, String.valueOf(currentClub.getUrlImage()));
            //bundle.putString(Constants.ID_KEY, String.valueOf(currentClub.getId()));

            bundle.putString(Constants.CLUB_NAME, currentClub.getName());
            bundle.putInt(ClubDetailsActivity.CLUB_DETAIL_FRAGMENT_TAG, DETAIL_FRAGMENT_ID);
            bundle.putString(Constants.CLUB_DESCRIPTION, noneContent);

            ((ClubViewHolder) viewHolder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    NavigationHelper.startClubDetail(((ActionBarActivity) context), bundle);
                }
            });


        }
    }


    @Override
    public int getItemCount()
    {
        if (clubs == null)
            return 0;

        return clubs.size();
    }

    public void updateList(List<Club> clubs)
    {
        this.clubs = clubs;
        notifyDataSetChanged();
    }
    public void DummyContent()
    {
        clubs = new ArrayList<>();

        Club temp;
        for (int i = 0; i < 100; i++)
        {
            String name = "Club "+i;
            temp = new Club(2,name,name,Uri.EMPTY);
            clubs.add(temp);
        }
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder
    {
        @InjectView(R.id.itemMain)
        RelativeLayout item;

        @InjectView(R.id.img_club)
        SimpleDraweeView imgCharacter;

        @InjectView(R.id.txt_club_name)
        TextView txtName;

        public void setImg(Uri urlImage)
        {
            if (!urlImage.equals(Uri.EMPTY))
                imgCharacter.setImageURI(urlImage);
        }


        public void setName(String name)
        {
            txtName.setText(name);
        }

        public ClubViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


}
