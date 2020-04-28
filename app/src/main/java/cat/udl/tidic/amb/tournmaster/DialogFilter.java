package cat.udl.tidic.amb.tournmaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.File;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class DialogFilter extends AppCompatDialogFragment {
    private ImageView image_photo;
    private UserService userService;
    private SharedPreferences mPreferences;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view)
                .setTitle(getResources().getString(R.string.Selecciona_Imagen))
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(getResources().getString(R.string.Abrir), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    cargarImagen();
            }
        });
        image_photo = view.findViewById(R.id.img_imageview);

        return builder.create();

    }
    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setType("image/");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DIALOG",""+resultCode);
        if(resultCode==RESULT_OK){
            String token;
            this.mPreferences = PreferencesProvider.providePreferences();
            token = this.mPreferences.getString("token", "");


            userService = RetrofitClientInstance.
                    getRetrofitInstance().create(UserService.class);

            Uri path = data.getData();
            Log.d("TAG",path.getPath());
            File file = new File(path.getPath());

            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse("multipart/form-data"),
                            file
                    );

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("image_file", file.getName(), requestFile);
            Call<ResponseBody> call = userService.uploadImage(token, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.v("Upload", "success");

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });
            image_photo.setImageURI(path);
            System.out.println(path);


        }
    }

}
