package com.app.titan_fit.ui.muscle;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.api.YoutubeAPI;
import com.app.titan_fit.databinding.FragmentMuscleBinding;
import com.app.titan_fit.ui.videos.VideosViewModel;
import com.app.titan_fit.video_model.VideoModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuscleFragment extends Fragment {

    private MuscleViewModel muscleViewModel;
    private VideosViewModel videosViewModel;
    private FragmentMuscleBinding binding;
    private ImageView imgBody;
    private ImageView rotate;
    private Boolean front = true;
    private Context context;

    //Male Front
    private ImageView maleFrontCalves;
    private ImageView maleFrontQuadriceps;
    private ImageView maleFrontAbs;
    private ImageView maleFrontBranchiallis;
    private ImageView maleFrontBiceps;
    private ImageView maleFrontChest;
    private ImageView maleFrontDelts;
    private ImageView maleFrontTraps;
    //Male Back
    private ImageView maleBackCalves;
    private ImageView maleBackHamstring;
    private ImageView maleBackHips;
    private ImageView maleLowerBack;
    private ImageView maleBackLats;
    private ImageView maleUpperBack;
    private ImageView maleBackBranchiallis;
    private ImageView maleBackTriceps;
    private ImageView maleBackDelts;
    private ImageView maleBackTraps;
    //Female Front
    private ImageView femaleFrontCalves;
    private ImageView femaleFrontQuadriceps;
    private ImageView femaleFrontAbs;
    private ImageView femaleFrontBranchiallis;
    private ImageView femaleFrontBiceps;
    private ImageView femaleFrontChest;
    private ImageView femaleFrontDelts;
    private ImageView femaleFrontTraps;
    //Female Back
    private ImageView femaleBackCalves;
    private ImageView femaleBackHamstring;
    private ImageView femaleBackHips;
    private ImageView femaleLowerBack;
    private ImageView femaleBackLats;
    private ImageView femaleUpperBack;
    private ImageView femaleBackBranchiallis;
    private ImageView femaleBackTriceps;
    private ImageView femaleBackDelts;
    private ImageView femaleBackTraps;
    private RequestQueue requestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        muscleViewModel =
                new ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        videosViewModel = new ViewModelProvider(requireActivity()).get(VideosViewModel.class);
        binding = FragmentMuscleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imgBody = binding.bodyImage;
        rotate = binding.rotate;
        context = container.getContext();
        requestQueue = Volley.newRequestQueue(context);
        //Male Front
        maleFrontCalves = binding.maleFrontCalves;
        maleFrontQuadriceps = binding.maleFrontQuadriceps;
        maleFrontAbs = binding.maleFrontAbs;
        maleFrontBranchiallis = binding.maleFrontBrachiallis;
        maleFrontBiceps = binding.maleFrontBiceps;
        maleFrontChest = binding.maleFrontChest;
        maleFrontDelts = binding.maleFrontDelts;
        maleFrontTraps = binding.maleFrontTraps;
        //Male Back
        maleBackCalves = binding.maleBackCalves;
        maleBackHamstring = binding.maleBackHamstring;
        maleBackHips = binding.maleBackHips;
        maleLowerBack = binding.maleLowerBack;
        maleBackLats = binding.maleBackLats;
        maleUpperBack = binding.maleUpperBack;
        maleBackBranchiallis = binding.maleBackBrachiallis;
        maleBackTriceps = binding.maleBackTriceps;
        maleBackDelts = binding.maleBackDelts;
        maleBackTraps = binding.maleBackTraps;
        //Female Front
        femaleFrontCalves = binding.femaleFrontCalves;
        femaleFrontQuadriceps = binding.femaleFrontQuadriceps;
        femaleFrontAbs = binding.femaleFrontAbs;
        femaleFrontBranchiallis = binding.femaleFrontBranchiallis;
        femaleFrontBiceps = binding.femaleFrontBiceps;
        femaleFrontChest = binding.femaleFrontChest;
        femaleFrontDelts = binding.femaleFrontDelts;
        femaleFrontTraps = binding.femaleFrontTraps;
        //Female Back
        femaleBackCalves = binding.femaleBackCalves;
        femaleBackHamstring = binding.femaleBackHamstring;
        femaleBackHips = binding.femaleBackHips;
        femaleLowerBack = binding.femaleLowerBack;
        femaleBackLats = binding.femaleBackLats;
        femaleUpperBack = binding.femaleUpperBack;
        femaleBackBranchiallis = binding.femaleBackBranchiallis;
        femaleBackTriceps = binding.femaleBackTriceps;
        femaleBackDelts = binding.femaleBackDelts;
        femaleBackTraps = binding.femaleBackTraps;


        muscleViewModel.getUserType().observe(getViewLifecycleOwner(), s -> {
            switch (s) {
                case AppConstants.MALE_USER:
                    imgBody.setImageResource(R.drawable.male_front);
                    front = true;
                    femaleBackVisibleToGone();
                    femaleFrontVisibleToGone();
                    maleBackVisibleToGone();
                    maleFrontGoneToVisible();
                    break;
                case AppConstants.FEMALE_USER:
                    imgBody.setImageResource(R.drawable.female_front);
                    front = true;
                    femaleBackVisibleToGone();
                    maleBackVisibleToGone();
                    maleFrontVisibleToGone();
                    femaleFrontGoneToVisible();
                    break;
            }
        });
        rotate.setOnClickListener(view -> {
            if (Objects.equals(muscleViewModel.getUserType().getValue(), AppConstants.MALE_USER)) {
                if (front) {
                    front = false;
                    maleFrontVisibleToGone();
                    femaleFrontVisibleToGone();
                    femaleBackVisibleToGone();
                    maleBackGoneToVisible();
                    imgBody.setImageResource(R.drawable.male_back);
                } else {
                    front = true;
                    maleBackVisibleToGone();
                    femaleFrontVisibleToGone();
                    femaleBackVisibleToGone();
                    maleFrontGoneToVisible();
                    imgBody.setImageResource(R.drawable.male_front);
                }
            } else if (Objects.equals(muscleViewModel.getUserType().getValue(), AppConstants.FEMALE_USER)) {
                if (front) {
                    front = false;
                    maleFrontVisibleToGone();
                    maleBackVisibleToGone();
                    femaleFrontVisibleToGone();
                    femaleBackGoneToVisible();
                    imgBody.setImageResource(R.drawable.female_back);
                } else {
                    front = true;
                    maleFrontVisibleToGone();
                    maleBackVisibleToGone();
                    femaleBackVisibleToGone();
                    femaleFrontGoneToVisible();
                    imgBody.setImageResource(R.drawable.female_front);
                }
            }
        });
        maleFrontCalves.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontCalves", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eS_vaiVz7UtCf6iivuLV5I", maleFrontCalves, view);
        });
        maleFrontQuadriceps.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontQuadriceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6e0ofuTFtwCKFRq0dXZ69XH", maleFrontQuadriceps, view);
        });
        maleFrontAbs.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontAbs", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dy-ARLAGWv0pgrvQrfNfUh", maleFrontAbs, view);
        });
        maleFrontBranchiallis.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontBranchiallis", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eMVrEy3w4dz1pPw0yVb2vZ", maleFrontBranchiallis, view);
        });
        maleFrontBiceps.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontBiceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cjtIJsOPt01czvo_FeCH5T", maleFrontBiceps, view);
        });
        maleFrontChest.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontChest", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fRMuVHVK_3HmRniW0TlZeP", maleFrontChest, view);
        });
        maleFrontDelts.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontDelts", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6euB3Nh39ufwA8u1XZs6Cfq", maleFrontDelts, view);
        });
        maleFrontTraps.setOnClickListener(view -> {
            Toast.makeText(context, "maleFrontTraps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fw9oDmwlPex9cIlvbREN1N", maleFrontTraps, view);
        });

        maleBackCalves.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackCalves", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eS_vaiVz7UtCf6iivuLV5I", maleBackCalves, view);
        });
        maleBackHamstring.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackHamstring", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cLFCOcZj_hweoJp9KKh-_a", maleBackHamstring, view);
        });
        maleBackHips.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackHips", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6e9IqbufYDPBRpR-Vnj9JXF", maleBackHips, view);
        });
        maleLowerBack.setOnClickListener(view -> {
            Toast.makeText(context, "maleLowerBack", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cxK5qbSZ1NkYlQwpbfgPgS", maleLowerBack, view);
        });
        maleBackLats.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackLats", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dhVdZu0toY-RqFUK-zwJA7", maleBackLats, view);
        });
        maleUpperBack.setOnClickListener(view -> {
            Toast.makeText(context, "maleUpperBack", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cjlLtbsN9wLU-Ok5yWDUJT", maleUpperBack, view);
        });
        maleBackBranchiallis.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackBranchiallis", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eMVrEy3w4dz1pPw0yVb2vZ", maleBackBranchiallis, view);
        });
        maleBackTriceps.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackTriceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dPvHs0urJvd0ED17VG1SMx", maleBackTriceps, view);
        });
        maleBackDelts.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackDelts", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6euB3Nh39ufwA8u1XZs6Cfq", maleBackDelts, view);
        });
        maleBackTraps.setOnClickListener(view -> {
            Toast.makeText(context, "maleBackTraps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fw9oDmwlPex9cIlvbREN1N", maleBackTraps, view);
        });

        femaleBackCalves.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackCalves", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eS_vaiVz7UtCf6iivuLV5I", femaleBackCalves, view);
        });
        femaleBackHamstring.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackHamstring", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fvZnAWCY_OVE5GX8KVF_Cl", femaleBackHamstring, view);
        });
        femaleBackHips.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackHips", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eX2VpWy89NK4P8qVU0KSQu", femaleBackHips, view);
        });
        femaleLowerBack.setOnClickListener(view -> {
            Toast.makeText(context, "femaleLowerBack", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6d9ciAIDX17G8i5SRoaezqN", femaleLowerBack, view);
        });
        femaleBackLats.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackLats", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fsHQ3gFoIQzNtjNITydXnf", femaleBackLats, view);
        });
        femaleUpperBack.setOnClickListener(view -> {
            Toast.makeText(context, "femaleUpperBack", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cjlLtbsN9wLU-Ok5yWDUJT", femaleUpperBack, view);
        });
        femaleBackBranchiallis.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackBranchiallis", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dpCy4LfsZN1j72YNBcZ2bV", femaleBackBranchiallis, view);
        });
        femaleBackTriceps.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackTriceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cxFVTK5paPCyw_kqYs3ERt", femaleBackTriceps, view);
        });
        femaleBackDelts.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackDelts", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6euB3Nh39ufwA8u1XZs6Cfq", femaleBackDelts, view);
        });
        femaleBackTraps.setOnClickListener(view -> {
            Toast.makeText(context, "femaleBackTraps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fw9oDmwlPex9cIlvbREN1N", femaleBackTraps, view);
        });

        femaleFrontCalves.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontCalves", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6eS_vaiVz7UtCf6iivuLV5I", femaleFrontCalves, view);
        });
        femaleFrontQuadriceps.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontQuadriceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cARCSteDBG6pdNlDIqfx__", femaleFrontQuadriceps, view);
        });
        femaleFrontAbs.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontAbs", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fTq7fcGmOvCeI4MICvgzBy", femaleFrontAbs, view);
        });
        femaleFrontBranchiallis.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontBranchiallis", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dpCy4LfsZN1j72YNBcZ2bV", femaleFrontBranchiallis, view);
        });
        femaleFrontBiceps.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontBiceps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6cr43tCAlKcd5hO6iPNVpDD", femaleFrontBiceps, view);
        });
        femaleFrontChest.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontChest", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6dhdH_0mcOd3dkLvw865zq4", femaleFrontChest, view);
        });
        femaleFrontDelts.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontDelts", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6euB3Nh39ufwA8u1XZs6Cfq", femaleFrontDelts, view);
        });
        femaleFrontTraps.setOnClickListener(view -> {
            Toast.makeText(context, "femaleFrontTraps", Toast.LENGTH_SHORT).show();
            getJson("PLrYt8ACqZS6fw9oDmwlPex9cIlvbREN1N", femaleFrontTraps, view);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void maleFrontGoneToVisible() {
        maleFrontCalves.setVisibility(View.VISIBLE);
        maleFrontQuadriceps.setVisibility(View.VISIBLE);
        maleFrontAbs.setVisibility(View.VISIBLE);
        maleFrontBranchiallis.setVisibility(View.VISIBLE);
        maleFrontBiceps.setVisibility(View.VISIBLE);
        maleFrontChest.setVisibility(View.VISIBLE);
        maleFrontDelts.setVisibility(View.VISIBLE);
        maleFrontTraps.setVisibility(View.VISIBLE);
    }

    private void maleFrontVisibleToGone() {
        maleFrontCalves.setVisibility(View.GONE);
        maleFrontQuadriceps.setVisibility(View.GONE);
        maleFrontAbs.setVisibility(View.GONE);
        maleFrontBranchiallis.setVisibility(View.GONE);
        maleFrontBiceps.setVisibility(View.GONE);
        maleFrontChest.setVisibility(View.GONE);
        maleFrontDelts.setVisibility(View.GONE);
        maleFrontTraps.setVisibility(View.GONE);
    }

    private void maleBackGoneToVisible() {
        maleBackCalves.setVisibility(View.VISIBLE);
        maleBackHamstring.setVisibility(View.VISIBLE);
        maleBackHips.setVisibility(View.VISIBLE);
        maleLowerBack.setVisibility(View.VISIBLE);
        maleBackLats.setVisibility(View.VISIBLE);
        maleUpperBack.setVisibility(View.VISIBLE);
        maleBackBranchiallis.setVisibility(View.VISIBLE);
        maleBackTriceps.setVisibility(View.VISIBLE);
        maleBackDelts.setVisibility(View.VISIBLE);
        maleBackTraps.setVisibility(View.VISIBLE);
    }

    private void maleBackVisibleToGone() {
        maleBackCalves.setVisibility(View.GONE);
        maleBackHamstring.setVisibility(View.GONE);
        maleBackHips.setVisibility(View.GONE);
        maleLowerBack.setVisibility(View.GONE);
        maleBackLats.setVisibility(View.GONE);
        maleUpperBack.setVisibility(View.GONE);
        maleBackBranchiallis.setVisibility(View.GONE);
        maleBackTriceps.setVisibility(View.GONE);
        maleBackDelts.setVisibility(View.GONE);
        maleBackTraps.setVisibility(View.GONE);
    }

    private void femaleFrontGoneToVisible() {
        femaleFrontCalves.setVisibility(View.VISIBLE);
        femaleFrontQuadriceps.setVisibility(View.VISIBLE);
        femaleFrontAbs.setVisibility(View.VISIBLE);
        femaleFrontBranchiallis.setVisibility(View.VISIBLE);
        femaleFrontBiceps.setVisibility(View.VISIBLE);
        femaleFrontChest.setVisibility(View.VISIBLE);
        femaleFrontDelts.setVisibility(View.VISIBLE);
        femaleFrontTraps.setVisibility(View.VISIBLE);
    }

    private void femaleFrontVisibleToGone() {
        femaleFrontCalves.setVisibility(View.GONE);
        femaleFrontQuadriceps.setVisibility(View.GONE);
        femaleFrontAbs.setVisibility(View.GONE);
        femaleFrontBranchiallis.setVisibility(View.GONE);
        femaleFrontBiceps.setVisibility(View.GONE);
        femaleFrontChest.setVisibility(View.GONE);
        femaleFrontDelts.setVisibility(View.GONE);
        femaleFrontTraps.setVisibility(View.GONE);
    }

    private void femaleBackGoneToVisible() {
        femaleBackCalves.setVisibility(View.VISIBLE);
        femaleBackHamstring.setVisibility(View.VISIBLE);
        femaleBackHips.setVisibility(View.VISIBLE);
        femaleLowerBack.setVisibility(View.VISIBLE);
        femaleBackLats.setVisibility(View.VISIBLE);
        femaleUpperBack.setVisibility(View.VISIBLE);
        femaleBackBranchiallis.setVisibility(View.VISIBLE);
        femaleBackTriceps.setVisibility(View.VISIBLE);
        femaleBackDelts.setVisibility(View.VISIBLE);
        femaleBackTraps.setVisibility(View.VISIBLE);
    }

    private void femaleBackVisibleToGone() {
        femaleBackCalves.setVisibility(View.GONE);
        femaleBackHamstring.setVisibility(View.GONE);
        femaleBackHips.setVisibility(View.GONE);
        femaleLowerBack.setVisibility(View.GONE);
        femaleBackLats.setVisibility(View.GONE);
        femaleUpperBack.setVisibility(View.GONE);
        femaleBackBranchiallis.setVisibility(View.GONE);
        femaleBackTriceps.setVisibility(View.GONE);
        femaleBackDelts.setVisibility(View.GONE);
        femaleBackTraps.setVisibility(View.GONE);
    }

    //    private JSONObject fetchVideos(String playlistId, ImageView imageView,View view){
//        final JSONObject[] response = {new JSONObject()};
////        imageView.setColorFilter(getResources().getColor(R.color.blue));
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//                "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+playlistId+"&key=AIzaSyCfw4VjfEyIsVOAdefPUiAOt0SDEJOsIcU",
//                null, resp -> {
//                    response[0] = resp;
//                    Log.e("myapp", "The response is" + response[0]);
//                    videosViewModel.getResp().setValue(response[0].toString());
//                    showVideos(view);
//                }, error -> {
//                    videosViewModel.getResp().setValue(error.toString());
//        });
//        requestQueue.add(jsonObjectRequest);
//        return response[0];
//    }
    private void showVideos(View view) {
        Navigation.findNavController(view).navigate(R.id.action_navigation_muscle_to_videosFragment);
    }

    private void getJson(String playListId, ImageView imageView, View view) {
        String url = YoutubeAPI.BASE_URL + YoutubeAPI.PLAY_LIST + playListId + YoutubeAPI.KEY;
        Call<VideoModel> data = YoutubeAPI.getYoutubeVideo().getYT(url);
        data.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                if (response.errorBody() != null) {
                    Log.v(TAG, "on Response" + response.errorBody());
                } else {
                    Log.v(TAG, "on Response" + response);
                    VideoModel vm = response.body();
                    videosViewModel.getVideos().setValue(vm.getItems());
                    Toast.makeText(context, "Loading Exercises", Toast.LENGTH_SHORT).show();
                    showVideos(view);
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(context, "Poor network connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}